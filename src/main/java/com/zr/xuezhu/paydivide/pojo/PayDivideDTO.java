package com.zr.xuezhu.paydivide.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class PayDivideDTO {

    private Integer amountInstallments;   //结款金额

    private Integer paymentCycle;   //还款周期

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date classHours;    //上课时间

    private Integer myCourse;   //我的课程

    private String name;//借款人的姓名

    private String idCode;//借款人身份证          //userinfo

    private String bankCardAccount; //银行卡账号

    private Integer openingBank;    //开户行

}
