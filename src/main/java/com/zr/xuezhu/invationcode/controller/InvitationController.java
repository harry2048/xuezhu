package com.zr.xuezhu.invationcode.controller;

import com.zr.xuezhu.invationcode.service.InvitationService;
import com.zr.xuezhu.userLogin.pojo.UserLogin;
import com.zr.xuezhu.util.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 邀请码方法
 */
@RestController
@CrossOrigin
public class InvitationController {

    @Autowired
    private InvitationService invitationService;

    /**
     * 获取邀请码
     * @param invitationCode
     * @param request
     * @return
     */
    @GetMapping("/inputInvitationCode")
    public ResultVo inputInvitationCode(@RequestParam("inputInvitationCode") String invitationCode, HttpServletRequest request){
        //实际就是修改:根据id进行修改 invitationCode
        //需要登录后才能进行修改，所以要去判断，从session中获取
       return invitationService.inputInvitationCode(invitationCode,request);
    }
}
