package com.zr.xuezhu.invationcode.service.impl;

import com.zr.xuezhu.invationcode.mapper.InvitationMapper;
import com.zr.xuezhu.invationcode.service.InvitationService;
import com.zr.xuezhu.userLogin.pojo.UserLogin;
import com.zr.xuezhu.util.ConsUtil;
import com.zr.xuezhu.util.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by Wjerry on 2019/7/3.
 */
@Service
public class InvitationServiceImpl implements InvitationService {
    @Autowired
    private InvitationMapper invitationMapper;
    @Override
    public ResultVo inputInvitationCode(String invitationCode, HttpServletRequest request) {
        if (invitationCode == null) {
            return ResultVo.error(ConsUtil.INVITACODEEMPTY);
        }

        UserLogin userLogin = (UserLogin) request.getSession().getAttribute(ConsUtil.USER);
        if (userLogin == null) {
            return ResultVo.notLogin(ConsUtil.USERNOTLOGIN);
        }

        List<UserLogin> phoneList = invitationMapper.queryByPhone(userLogin.getIphone());
        if (CollectionUtils.isEmpty(phoneList)){
           return ResultVo.error(ConsUtil.NOTREGIST);
        }



        Date nowDate = new Date();
        userLogin.setInvitationCode(invitationCode);
        userLogin.setUpdateTime(nowDate);
        invitationMapper.inputInvitationCode(userLogin);
        return ResultVo.success(userLogin);
    }
}
