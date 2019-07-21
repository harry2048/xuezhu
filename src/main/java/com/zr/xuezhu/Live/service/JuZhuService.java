package com.zr.xuezhu.Live.service;


import com.zr.xuezhu.Live.bean.JuZhuAddVo;
import com.zr.xuezhu.util.ResultVo;

import javax.servlet.http.HttpServletRequest;

public interface JuZhuService {

    ResultVo add(JuZhuAddVo juZhuAddVo, HttpServletRequest request);

    ResultVo getLive(HttpServletRequest request);
}
