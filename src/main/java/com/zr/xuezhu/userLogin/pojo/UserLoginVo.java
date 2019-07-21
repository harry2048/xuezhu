package com.zr.xuezhu.userLogin.pojo;

import lombok.Data;
import lombok.extern.java.Log;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
public class UserLoginVo {

    @NotBlank(message = "手机号不能为空！")
    private String iphone;

    @NotBlank(message = "验证码不能为空！")
    private String verificationCode;
}
