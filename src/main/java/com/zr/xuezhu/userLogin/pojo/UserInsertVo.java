package com.zr.xuezhu.userLogin.pojo;

import lombok.Data;
import lombok.extern.java.Log;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
public class UserInsertVo {

    @NotBlank(message = "手机号不能为空！")
    @Length(max = 11,min = 7,message = "电话号码长度应该是7或者11")
    private String iphone;


    @NotBlank(message = "验证码不能为空！")
    private String verificationCode;

    @NotBlank(message = "密码不能为空！")
    private String userPwd;

    @NotBlank(message = "确认密码不能为空！")
    private String confirmPwd;

}
