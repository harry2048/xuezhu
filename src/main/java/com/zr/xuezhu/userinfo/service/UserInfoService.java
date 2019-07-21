package com.zr.xuezhu.userinfo.service;

import com.zr.xuezhu.userinfo.pojo.UserInfoAddVo;
import com.zr.xuezhu.util.ResultVo;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Wjerry on 2019/7/3.
 */
public interface UserInfoService {
    ResultVo essentialInformation(UserInfoAddVo userInfoAddVo, HttpServletRequest request);

    ResultVo getNews(HttpServletRequest request);
}
