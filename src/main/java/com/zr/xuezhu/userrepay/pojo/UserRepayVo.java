package com.zr.xuezhu.userrepay.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Auther: gengwei
 * @Date: 2019-07-07 22:11
 * @Description:
 */
@Data
public class UserRepayVo implements Serializable {
    private String bankCardAccount;//银行卡账号

    private String openingBank;//开户行

    private String bankPhone;//银行手机号

    private String verificationCode;// 验证码
}
