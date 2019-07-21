package com.zr.xuezhu.userrepay.service;

import com.zr.xuezhu.userrepay.pojo.UserRepayAddVo;
import com.zr.xuezhu.util.ResultVo;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ZLFamily on 2019/7/3.
 */
public interface UserRepayService {

    ResultVo addUserRepay(UserRepayAddVo userRepayAddVo, HttpServletRequest request);

    ResultVo getHuankuan_dengji(HttpServletRequest request);

}
