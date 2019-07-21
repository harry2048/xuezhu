package com.zr.xuezhu.userLogin.controller;

import com.zr.xuezhu.userLogin.pojo.UserInsertVo;
import com.zr.xuezhu.userLogin.pojo.UserLogin;
import com.zr.xuezhu.userLogin.pojo.UserLoginVo;
import com.zr.xuezhu.userLogin.service.UserLoginService;
import com.zr.xuezhu.util.ConsUtil;
import com.zr.xuezhu.util.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@CrossOrigin
public class UserLoginController {
    @Autowired
    private UserLoginService userLoginService;

    /**
     * @author 耿威
     * @date 2019-07-04 23:42
     * @param userInsertVo
     * @param bindingResult
     * @return com.zr.xuezhu.util.ResultVo
     * @throws
     * @since
     * @description: 注册方法
     */
    @PostMapping("/register")
    public ResultVo register(@RequestBody @Valid UserInsertVo userInsertVo, BindingResult bindingResult,HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return ResultVo.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return userLoginService.register(userInsertVo,request);
    }


    /**
     * @author gengwei
     * @date 2019-07-04 23:41
     * @param userLoginVo
     * @param bindingResult
     * @param request
     * @return com.zr.xuezhu.util.ResultVo
     * @throws
     * @since
     * @description: 登录
    */
    @PostMapping("/login")
    public ResultVo login(@RequestBody @Valid UserLoginVo userLoginVo,  BindingResult bindingResult,HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return ResultVo.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return userLoginService.login(request,userLoginVo);
    }


    /**
     * @author 耿威
     * @date 2019-07-06 20:47
     * @param iphone
     * @return com.zr.xuezhu.util.ResultVo
     * @throws
     * @since
     * @description: 获取验证码
    */
    @GetMapping("/getVerificationCode")
    public ResultVo getVerificationCode(@RequestParam("iphone") String iphone, HttpServletRequest request) {
        return userLoginService.getVerificationCode(iphone,request);
    }

    //注册时的验证码只能为post,前端不好改因此添加了这个post接口
    @PostMapping("/postVerificationCode")
    public ResultVo getVerificationCode2(String iphone, HttpServletRequest request) {
        return userLoginService.getVerificationCode(iphone,request);
    }


    /**
     * @author 耿威
     * @date 2019-07-08 21:02
     * @param multipartFile
     * @param request
     * @return com.zr.xuezhu.util.ResultVo
     * @throws
     * @since
     * @description: 身份证上传
    */
    @RequestMapping(value = "/import/idPositive")
    public ResultVo idPositive(@RequestParam("file")String type ,MultipartFile multipartFile, HttpServletRequest request) throws IOException{
        return userLoginService.idPositive(multipartFile, request);
    }

    /**
     * 我的订单接口
     */
    @RequestMapping("/myOrder")
    public ResultVo myOrder(HttpServletRequest request){
      return userLoginService.myOrder(request);
    }
    /**
     * 取消申请
     */
    @RequestMapping("/quXiaoShenQing")
    public ResultVo quXiaoShenQing(HttpServletRequest request){
      return userLoginService.quXiaoShenQing(request);
    }
    /**
     * 签约
     */
    @RequestMapping("/qianyue")
    public ResultVo qianyue(HttpServletRequest request){
      return userLoginService.qianyue(request);
    }
}
