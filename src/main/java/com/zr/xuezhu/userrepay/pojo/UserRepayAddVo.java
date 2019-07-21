package com.zr.xuezhu.userrepay.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by ZLFamily on 2019/7/3.
 */
@Data
public class UserRepayAddVo {
    @NotBlank(message = "银行卡账号不能为空！")
    private String bankCardAccount;//银行卡账号

    @NotNull(message = "开户行不能为空！")
    private Integer openingBank;//开户行

    @NotBlank(message = "手机号码不能为空！")
    private String bankPhone;//银行手机号

    @NotBlank(message = "验证码不能为空！")
    private String verificationCode;// 验证码
}
