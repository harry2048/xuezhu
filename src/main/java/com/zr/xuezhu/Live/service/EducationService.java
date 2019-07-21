package com.zr.xuezhu.Live.service;

import com.zr.xuezhu.Live.bean.EducationAddVo;
import com.zr.xuezhu.util.ResultVo;

import javax.servlet.http.HttpServletRequest;

public interface EducationService {


    ResultVo add(EducationAddVo educationAddVo, HttpServletRequest request);

    ResultVo getEducation(HttpServletRequest request);
}
