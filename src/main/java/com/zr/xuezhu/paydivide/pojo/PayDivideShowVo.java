package com.zr.xuezhu.paydivide.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by ZLFamily on 2019/7/3.
 */
@Data
public class PayDivideShowVo {

    @NotNull(message = "结款金额不能为空！")
    private Integer amountInstallments;   //结款金额

    @NotBlank(message = "收款账户不能为空！")
    private String paymentAccount;  //收款账户

    @NotBlank(message = "还款周期不能为空！")
    private String paymentCycle; // 还款周期拼接

    @NotBlank(message = "用途不能为空！")
    private String myCourse;   //我的课程

    @NotBlank(message = "起止日期不能为空！")
    private String startTime; //起止时间

    @NotBlank(message = "首次还款日不能为空！")
    private String firstPayMentDate;  //首次还款日         //paydivide

    @NotBlank(message = "借款人不能为空！")
    private String name;//借款人的姓名

    @NotBlank(message = "借款人身份证号码不能为空！")
    private String idCode;//借款人身份证          //userinfo

    @NotBlank(message = "还款日不能为空！")
    private String payMentDate;//还款日           //paydivide

    @NotBlank(message = "还款银行不能为空！")
    private String backBank;   //默认还款银行

    @NotBlank(message = "授权协议不能为空！")
    private String agreement;   //授权协议

    private Integer payOffStatus;


}
