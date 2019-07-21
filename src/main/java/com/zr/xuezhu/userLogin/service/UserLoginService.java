package com.zr.xuezhu.userLogin.service;

import com.zr.xuezhu.userLogin.pojo.UserInsertVo;
import com.zr.xuezhu.userLogin.pojo.UserLogin;
import com.zr.xuezhu.userLogin.pojo.UserLoginVo;
import com.zr.xuezhu.util.ResultVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface UserLoginService {
    ResultVo register(UserInsertVo userInsertVo,HttpServletRequest request);

    ResultVo login(HttpServletRequest request, UserLoginVo userLoginVo);
    ResultVo myOrder(HttpServletRequest request);
    ResultVo quXiaoShenQing(HttpServletRequest request);
    ResultVo qianyue(HttpServletRequest request);

    ResultVo getVerificationCode(String iphone, HttpServletRequest request);

    ResultVo idPositive(MultipartFile multipartFile, HttpServletRequest request) throws IOException;
}
