package com.zr.xuezhu.userLogin.service.impl;

import com.zr.xuezhu.duanxin.DuanXinUtil;
import com.zr.xuezhu.paydivide.pojo.PayDivide;
import com.zr.xuezhu.userLogin.mapper.UserLoginMapper;
import com.zr.xuezhu.userLogin.pojo.UserInsertVo;
import com.zr.xuezhu.userLogin.pojo.UserLogin;
import com.zr.xuezhu.userLogin.pojo.UserLoginVo;
import com.zr.xuezhu.userLogin.service.UserLoginService;
import com.zr.xuezhu.userLogin.util.UploadUtil;
import com.zr.xuezhu.util.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    private UserLoginMapper userLoginMapper;


    @Override
    @Transactional
    /**
     * @author gengwei
     * @date 2019-07-04 23:39
     * @param userInsertVo
     * @return com.zr.xuezhu.util.ResultVo
     * @throws 
     * @since
     * @description: 注册方法
    */
    public ResultVo register(UserInsertVo userInsertVo,HttpServletRequest request) {
        /*
            1、验证密码和确认密码是否相等
            2、验证手机号码是否有重复
            3、验证是否发送了验证码
            4、验证是否已经注册
            5、验证码是否正确
         */

        // 1.验证密码和确认密码是否相等
        if (!userInsertVo.getUserPwd().equals(userInsertVo.getConfirmPwd())) {
            return ResultVo.error(ConsUtil.PWDNOTEQUAL);
        }
        // 2.验证手机号码是否有重复
        List<UserLogin> queryByIphoneList = userLoginMapper.queryByIphone(userInsertVo.getIphone());
        UserLogin queryByIphone = null;
        if (queryByIphoneList.size() > 1) {
            return ResultVo.error(ConsUtil.IPHONEUNIQU);

            // 3.说明没有验证码这条信息
        } else if (CollectionUtils.isEmpty(queryByIphoneList)) {
            return ResultVo.error(ConsUtil.RESENTCODE);
        }else if (queryByIphoneList.size() == 1) {
            queryByIphone = queryByIphoneList.get(0);
            // 4. 注册需要输入密码，查询的数据中有密码，即已经注册过
            if (queryByIphone.getUserPwd() != null) {
                return ResultVo.error(ConsUtil.IPHONEUNIQU);
            }
        }
        /*if (queryByIphone.getVerificationCode() == null || queryByIphone.getVerificationCode().trim().isEmpty()) {
            return ResultVo.error(ConsUtil.RESENTCODE);
        }
        // 5.校验验证码是否相同
        if (!queryByIphone.getVerificationCode().equals(userInsertVo.getVerificationCode())) {
            return ResultVo.error(ConsUtil.CODEERROR);
        }*/
        // 销毁验证码
        userLoginMapper.updateVerifyCodeByIphone(userInsertVo.getIphone());

        UserLogin userLogin = new UserLogin();
        BeanUtils.copyProperties(userInsertVo,userLogin);
//        request.getSession().setAttribute(ConsUtil.USER,queryByIphoneAndVericode);
        // 入库
      userLoginMapper.updateByIphone(userLogin);
        return ResultVo.success();
    }

    @Override
    /**
     * @author 耿威
     * @date 2019-07-04 23:40
     * @param request
     * @param userLoginVo
     * @return com.zr.xuezhu.util.ResultVo
     * @throws 
     * @since
     * @description: 登录方法
    */
    public ResultVo login(HttpServletRequest request, UserLoginVo userLoginVo) {

        /**
         * 1、根据手机号码查询是否存在
         * 2、根据验证码和手机号码查询数据
         * 3、验证是否已经注册（查看是否有密码）
         * 4、保存数据到session
         */
        UserLogin userLogin = new UserLogin();
        BeanUtils.copyProperties(userLoginVo,userLogin);

        // 1.查询手机号是否已经存在
        List<UserLogin> userLogins = userLoginMapper.queryByIphone(userLogin.getIphone());
        if (CollectionUtils.isEmpty(userLogins)) {
            return ResultVo.error(ConsUtil.NOTREGIST);
        }

        // 2.根据手机号和验证码查询
        UserLogin queryByIphoneAndVericode = userLoginMapper.queryByIphoneAndVericode(userLogin);
        if (queryByIphoneAndVericode == null) {
            return ResultVo.error(ConsUtil.RESENTCODE);

          //3.
        } else if (StringUtils.isEmpty(queryByIphoneAndVericode.getUserPwd())) {
            return ResultVo.error(ConsUtil.NOTREGIST);
        }
        // 销毁验证码
        userLoginMapper.updateVerifyCodeByIphone(userLogin.getIphone());
        // 4.
            request.getSession().setAttribute(ConsUtil.USER,queryByIphoneAndVericode);
        //如果此用户的单子已经审核了，直接跳转页面到详情页面
        List<PayDivide> payDivides = userLoginMapper.queryPaydivideStatus(userLogins.get(0).getId());
        if (!CollectionUtils.isEmpty(payDivides)){
            if (payDivides.get(0).getStatus()==1){
                ResultVo resultVo = new ResultVo();
                resultVo.setIsShenHe(1);
                resultVo.setSuccess(true);
                return resultVo;
            }else if (payDivides.get(0).getStatus()==3){
                ResultVo resultVo = new ResultVo();
                resultVo.setIsShenHe(3);
                resultVo.setSuccess(true);
                return resultVo;
            }else if (payDivides.get(0).getStatus()==6){
                ResultVo resultVo = new ResultVo();
                resultVo.setIsShenHe(6);
                resultVo.setSuccess(true);
                return resultVo;
            }
        }
        return ResultVo.success();
    }

    @Override
    public ResultVo myOrder(HttpServletRequest request) {
        UserLogin userLogin = (UserLogin) request.getSession().getAttribute(ConsUtil.USER);
        if (userLogin==null){
            return ResultVo.notLogin(ConsUtil.USERNOTLOGIN);
        }
        //确认之前都重新填 确认了的显示单子信息  审核了的显示签约信息
        List<PayDivide> payDivides = userLoginMapper.queryPaydivideStatus(userLogin.getId());
        if (!CollectionUtils.isEmpty(payDivides)){
            if (payDivides.get(0).getStatus()==1){
                ResultVo resultVo = new ResultVo();
                resultVo.setIsShenHe(1);
                resultVo.setSuccess(true);
                return resultVo;
            }else if (payDivides.get(0).getStatus()==3){
                ResultVo resultVo = new ResultVo();
                resultVo.setIsShenHe(3);
                resultVo.setSuccess(true);
                return resultVo;
            }else if (payDivides.get(0).getStatus()==6){
                ResultVo resultVo = new ResultVo();
                resultVo.setIsShenHe(6);
                resultVo.setSuccess(true);
                return resultVo;
            }else {
                ResultVo resultVo = new ResultVo();
                resultVo.setIsShenHe(0);
                resultVo.setSuccess(true);
                return resultVo;
            }
        }
        return ResultVo.success();
    }
    @Override
    @Transactional
    public ResultVo quXiaoShenQing(HttpServletRequest request) {
        UserLogin userLogin = (UserLogin) request.getSession().getAttribute(ConsUtil.USER);
        if (userLogin==null){
            return ResultVo.notLogin(ConsUtil.USERNOTLOGIN);
        }
        //根据loginId修改单子状态为取消
        userLoginMapper.quXiaoShenQing(userLogin.getId());
        return ResultVo.success();
    }
    @Override
    @Transactional
    public ResultVo qianyue(HttpServletRequest request) {
        UserLogin userLogin = (UserLogin) request.getSession().getAttribute(ConsUtil.USER);
        if (userLogin==null){
            return ResultVo.notLogin(ConsUtil.USERNOTLOGIN);
        }
        //根据loginId修改单子状态为取消
        userLoginMapper.qianyue(userLogin.getId());
        return ResultVo.success();
    }

    @Override
    @Transactional
    /**
     * @author 耿威
     * @date 2019-07-08 21:53
     * @param iphone
     * @param req
     * @return com.zr.xuezhu.util.ResultVo
     * @throws 
     * @description: 生成验证码
    */
    public ResultVo getVerificationCode(String iphone,HttpServletRequest req) {
        // 验证手机号码是否为空
        if (iphone == null || iphone.trim().isEmpty()) {
            return ResultVo.error(ConsUtil.IPHONEEMPTY);
        }
        // 生成验证码
        String code = UUIDUtil.getCode();
        String msg = "【创蓝253】你好,你的验证码是"+code;
        // 给该手机号码发送验证码的短信
       /* ResultVo duanxin = DuanXinUtil.duanxin(msg, iphone);
        if (!duanxin.getSuccess()){
            return duanxin;
        }*/
        /**
         * 1.判断是否登录
         * 2.如果已登录，按照session的id修改验证码
         * 3.如果未登录，判断是否存在，如果存在就修改，不存在就新增
         * 4.设置定时任务，5分钟后清除验证码
         */

        // 从session中获取userLogin
        UserLogin userLogin = (UserLogin) req.getSession().getAttribute(ConsUtil.USER);

        // 1.
        if (userLogin != null) {
            // 2.
            userLogin.setIphone(iphone);
            userLogin.setVerificationCode(code);
            userLoginMapper.updateVerifyCodeById(userLogin);
            return ResultVo.success();

        } else {
            // 3.
            userLogin = new UserLogin();
            userLogin.setIphone(iphone);
            userLogin.setVerificationCode(code);
            List<UserLogin> userLogins = userLoginMapper.queryByIphone(iphone);
            if (!CollectionUtils.isEmpty(userLogins)) {
                int count = userLoginMapper.updateCodeByIphone(userLogin);
                if (count < 1) {
                    return ResultVo.error(ConsUtil.RESENTCODE);
                }
                return ResultVo.success();
            }else {
                int count = userLoginMapper.insertIphoneAndCode(userLogin);
                if (count < 1) {
                    return ResultVo.error(ConsUtil.RESENTCODE);
                }
                return ResultVo.success();
            }
        }
    }

    @Override
   /**
    * @author 耿威
    * @date 2019-07-08 21:14
    * @param multipartFile
    * @param request
    * @return com.zr.xuezhu.util.ResultVo
    * @throws IOException
    * @description: 上传
   */
    public ResultVo idPositive(MultipartFile multipartFile, HttpServletRequest request) throws IOException {
        String filename = multipartFile.getOriginalFilename();
        String end = filename.substring(filename.lastIndexOf("."));
        filename = IDUtils.genImageName()+end;
        String lujin = IDUtils.genLuJing();
        InputStream in = multipartFile.getInputStream();
        boolean flag = UploadUtil.uploadFile(UploadUtil.host, UploadUtil.port, UploadUtil.username, UploadUtil.password, UploadUtil.basePath, lujin, filename, in);
        return null;
    }

    // 4.定时清理验证码
//    @Scheduled(fixedDelay = 5 * 60 * 1000)
    public void testTimer() {
        userLoginMapper.deleteVerifyCode();
        // System.out.println("5分钟清除一次验证码："+new Date());
    }
}
