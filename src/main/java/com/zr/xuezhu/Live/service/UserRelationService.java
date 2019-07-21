package com.zr.xuezhu.Live.service;

import com.zr.xuezhu.Live.bean.UserRelationAddVo;
import com.zr.xuezhu.util.ResultVo;

import javax.servlet.http.HttpServletRequest;

public interface UserRelationService {


    ResultVo add(UserRelationAddVo userRelationAddVo, HttpServletRequest request);

    ResultVo getContacts(HttpServletRequest request);
}
