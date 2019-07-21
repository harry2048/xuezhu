package com.zr.xuezhu.paydivide.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class PayDivide {
    private Integer id;//主键id

    private Integer loginId;//注册表外键

    private Integer amountSettlements;//结款金额

    private String paymentAccount;//收款账户

    private String startTime;//起止时间

    private String firstPayMentDate;//首次还款日

    private String payMentDate;//还款日

    private String paymentCycle; // 还款周期

    private String backBank;// 默认还款银行

    private String name;//还款人

    private String idCode;// 身份证号码

    private String agreement;// 授权协议

    private String myCourse;// 我的课程

    private String orderNum;//订单号

    private Integer status;//状态

    private String statusName;

    private String phone;

    private Date createTime;//创建时间

    private String createName;//创建人

    private Date updateTime;//修改时间

    private String updateName;//修改人

}
