package com.zr.xuezhu.userinfo.service.impl;


import com.zr.xuezhu.paydivide.mapper.PayDivideMapper;
import com.zr.xuezhu.paydivide.pojo.PayDivide;
import com.zr.xuezhu.paymentinformation.mapper.PaymentInformationMapper;
import com.zr.xuezhu.userLogin.mapper.UserLoginMapper;
import com.zr.xuezhu.userLogin.pojo.UserLogin;
import com.zr.xuezhu.userinfo.mapper.UserInfoMapper;
import com.zr.xuezhu.userinfo.pojo.UserInfo;
import com.zr.xuezhu.userinfo.pojo.UserInfoAddVo;
import com.zr.xuezhu.userinfo.service.UserInfoService;
import com.zr.xuezhu.util.ConsUtil;
import com.zr.xuezhu.util.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by Wjerry on 2019/7/3.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserLoginMapper userLoginMapper;

    @Autowired
    private PaymentInformationMapper paymentInformationMapper;

    @Autowired
    private PayDivideMapper payDivideMapper;

    /**
     * 基本信息
     * @param userInfoAddVo
     * @param request
     * @return
     */
    @Override
    public ResultVo essentialInformation(UserInfoAddVo userInfoAddVo,HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserLogin userLogin = (UserLogin) session.getAttribute(ConsUtil.USER);
        if (userLogin == null) {
            return ResultVo.notLogin(ConsUtil.USERNOTLOGIN);
        }
        //逻辑验证
        List<UserInfo> byLoginIds = userInfoMapper.queryByLoginId(userLogin.getId());
        UserInfo userInfo = copyProperties(userLogin,userInfoAddVo);
        if(byLoginIds.isEmpty()){
            userInfoMapper.essentialInformation(userInfo);
        }else if (byLoginIds.size() == 1){
            PayDivide payDivide = payDivideMapper.queryStatusByLoginId(userLogin.getId());
            if (payDivide == null){
                userInfoMapper.updateUserInfo(userInfo);
            }else{
                if (payDivide.getStatus()== 0){
                    userInfoMapper.updateUserInfo(userInfo);
                }else if (payDivide.getStatus()==5 || payDivide.getStatus()== 2){
                    userInfoMapper.updateUserInfo(userInfo);
                    payDivideMapper.updateStatus(userLogin.getId());
                }else{
                    return ResultVo.error(ConsUtil.NOTEXIST);
                }
            }
        }else{
            return ResultVo.error(ConsUtil.USEREXITED);
        }
        /*if (byLoginIds.size()>1){
            return ResultVo.error("该用户信息已经存在");
        } else if (byLoginIds.size() == 1) {
            // 修改
            userInfoMapper.updateUserInfo(userInfo);
        } else {
            // 新增
            userInfoMapper.essentialInformation(userInfo);
        }*/

        // 跟新userLogin表中的用户名信息
        userLoginMapper.updateUserNameByIphone(userInfoAddVo.getName(),userLogin.getId());

        // 更新分期支付表的创建人
        paymentInformationMapper.updateCreateName(userLogin);

        // 添加用户名，放回session
        userLogin.setUserName(userInfoAddVo.getName());
        session.setAttribute(ConsUtil.USER, userLogin);

        // 使用session完成上一步
        //session.setAttribute(ConsUtil.USERINFOADDVO,userInfoAddVo);
        // 使用redis完成
        session.setAttribute(ConsUtil.USERINFOADDVO,userInfoAddVo);

        return ResultVo.success();
    }

    // 获取刚刚存入的信息  使用session
    @Override
    public ResultVo getNews(HttpServletRequest request) {
        UserInfoAddVo userInfoAddVo = (UserInfoAddVo) request.getSession().getAttribute(ConsUtil.USERINFOADDVO);
        if (userInfoAddVo != null) {
            return ResultVo.success(userInfoAddVo);
        }
        return ResultVo.error();
    }

    private UserInfo copyProperties(UserLogin userLogin, UserInfoAddVo userInfoAddVo) {
        UserInfo userInfo =new UserInfo();
        BeanUtils.copyProperties(userInfoAddVo,userInfo);
        userInfo.setLoginId(userLogin.getId());
        Date nowDate = new Date();
        userInfo.setCreateTime(nowDate);
        userInfo.setCreateName(userInfoAddVo.getName());
        userInfo.setUpdateTime(nowDate);
        userInfo.setUpdateName(userInfoAddVo.getName());
        return userInfo;
    }
}
