package com.zr.xuezhu.userrepay.service.Impl;

import com.zr.xuezhu.paydivide.mapper.PayDivideMapper;
import com.zr.xuezhu.paydivide.pojo.PayDivide;
import com.zr.xuezhu.paydivide.service.PayDivideService;
import com.zr.xuezhu.paymentinformation.pojo.PaymentInformationAddVo;
import com.zr.xuezhu.userLogin.mapper.UserLoginMapper;
import com.zr.xuezhu.userLogin.pojo.UserLogin;
import com.zr.xuezhu.userrepay.mapper.UserRepayMapper;
import com.zr.xuezhu.userrepay.meiju.StatusEnum;
import com.zr.xuezhu.userrepay.pojo.UserRepay;
import com.zr.xuezhu.userrepay.pojo.UserRepayAddVo;
import com.zr.xuezhu.userrepay.pojo.UserRepayVo;
import com.zr.xuezhu.userrepay.service.UserRepayService;
import com.zr.xuezhu.util.ConsUtil;
import com.zr.xuezhu.util.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by ZLFamily on 2019/7/3.
 */
@Service
public class UserRepayServiceImpl implements UserRepayService {
    @Autowired
    private UserRepayMapper userRepayMapper;

    @Autowired
    private UserLoginMapper userLoginMapper;

    @Autowired
    private PayDivideMapper payDivideMapper;
    @Autowired
    private PayDivideService payDivideService;

    @Override
    @Transactional
    public ResultVo addUserRepay(UserRepayAddVo userRepayAddVo, HttpServletRequest request) {
        //登录提醒
        HttpSession session = request.getSession();
        UserLogin userLogin = (UserLogin) session.getAttribute(ConsUtil.USER);
        if (userLogin == null){
            return ResultVo.notLogin(ConsUtil.USERNOTLOGIN);
        }

        // 根据用户id查询验证码
      /*  String verificode = userRepayMapper.queryCodeByLoginId(userLogin.getId());
        if (StringUtils.isEmpty(verificode)) {
            // 如果验证码已经失效，提示重新发送
            return ResultVo.error(ConsUtil.RESENTCODE);
        }*/

        // 判断验证码是否正确
        /*if (!userRepayAddVo.getVerificationCode().equals(verificode)) {
            return ResultVo.error(ConsUtil.RESENTCODE);
        }*/
        // 销毁验证码
        userLoginMapper.updateVerifyCodeByIphone(userLogin.getIphone());

        // 将前台传来的数据赋值到userRepay中
        UserRepay userRepay = copyProperties(userRepayAddVo,userLogin);

        /*
         * 1.逻辑验证
         * 2.如果存在就修改
         * 3.如果不存在就新增
         */
        UserRepay userRepays = userRepayMapper.queryBackPhone(userLogin.getId());
        if(userRepays==null){
            userRepayMapper.addUserRepay(userRepay);
        }else {
            PayDivide payDivide = payDivideMapper.queryStatusByLoginId(userLogin.getId());
            if (payDivide == null){
                userRepayMapper.updateUserRepayByLoginId(userRepay);
            }else{
                if (payDivide.getStatus()== 0){
                    userRepayMapper.updateUserRepayByLoginId(userRepay);
                }else if (payDivide.getStatus()==5 || payDivide.getStatus()== 2){
                    userRepayMapper.updateUserRepayByLoginId(userRepay);
                    payDivideMapper.updateStatus(userLogin.getId());
                }else{
                    return ResultVo.error("当前状态不允许修改！");
                }
            }
        }
        /*if (userRepays.size()>1){
            return ResultVo.error(ConsUtil.USEREXITED);
        } else if (userRepays.size() == 1) {
            // 修改操作
            userRepayMapper.updateUserRepayByLoginId(userRepay);
        } else {
             // 数据入库
            userRepayMapper.addUserRepay(userRepay);
        }*/

        session.setAttribute(ConsUtil.USERREPAYADDVO,userRepayAddVo);
        // 查询分期信息并展示给前台
        return payDivideService.queryPage(request);
    }

    @Override
    public ResultVo getHuankuan_dengji(HttpServletRequest request) {
        UserRepayAddVo userRepayAddVo = (UserRepayAddVo) request.getSession().getAttribute(ConsUtil.USERREPAYADDVO);
        if (userRepayAddVo != null) {
            return ResultVo.success(userRepayAddVo);
        }
        return ResultVo.error();
    }

    private UserRepay copyProperties(UserRepayAddVo userRepayAddVo, UserLogin userLogin) {
        UserRepay userRepay = new UserRepay();
        BeanUtils.copyProperties(userRepayAddVo,userRepay);

        Date nowDate = new Date();
        userRepay.setLoginId(userLogin.getId());
        userRepay.setCreateName(userLogin.getUserName());
        userRepay.setCreateTime(nowDate);
        userRepay.setUpdateName(userLogin.getUserName());
        userRepay.setUpdateTime(nowDate);
        return userRepay;
    }

}
