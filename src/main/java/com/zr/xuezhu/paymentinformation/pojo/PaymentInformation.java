package com.zr.xuezhu.paymentinformation.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Created by Wjerry on 2019/7/3.
 */
@Data
public class PaymentInformation {

    private Integer id;
    private Integer loginId;
    private Integer myCourse;
    private Integer status;
//    private String myCourseName;
    private Integer amountInstallments;
    private Integer paymentCycle;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date classHours;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private String createName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    private String updateName;

}
