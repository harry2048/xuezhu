package com.zr.xuezhu.userinfo.controller;

import com.zr.xuezhu.userinfo.pojo.UserInfoAddVo;
import com.zr.xuezhu.userinfo.service.UserInfoService;
import com.zr.xuezhu.util.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
*用户基本信息
 */
@RestController
@CrossOrigin
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/essentialInformation")
    public ResultVo essentialInformation(@RequestBody@Valid UserInfoAddVo userInfoAddVo, HttpServletRequest request, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResultVo.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return userInfoService.essentialInformation(userInfoAddVo,request);
    }

    @GetMapping("/getNews")
    public ResultVo getNews(HttpServletRequest request) {
        return userInfoService.getNews(request);
    }
}
