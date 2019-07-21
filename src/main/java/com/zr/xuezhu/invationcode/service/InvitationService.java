package com.zr.xuezhu.invationcode.service;

import com.zr.xuezhu.util.ResultVo;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Wjerry on 2019/7/3.
 */
public interface InvitationService {
    ResultVo inputInvitationCode(String invitationCode, HttpServletRequest req);
}
