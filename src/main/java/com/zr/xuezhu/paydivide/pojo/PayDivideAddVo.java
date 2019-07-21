package com.zr.xuezhu.paydivide.pojo;

import lombok.Data;

@Data
public class PayDivideAddVo {
    private Integer id;

    private Integer loginId;

    private Integer amountInstallments;   //结款金额

    private String paymentAccount;  //收款账户

    private Integer paymentCycle; // 还款周期拼接

    private Integer myCourse;   //我的课程

    private String startTime; //起止时间

    private String firstPayMentDate;  //首次还款日         //paydivide

    private String name;//借款人的姓名

    private String idCode;//借款人身份证          //userinfo

    private String payMentDate;//还款日           //paydivide

    private String backBank;   //默认还款银行

    private String agreement;   //授权协议

    private String orderNum;//订单号

    private String createName;
    private String updateName;
    private Integer status;
}
