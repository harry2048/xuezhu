package com.zr.xuezhu.paymentinformation.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: gengwei
 * @Date: 2019-07-07 21:44
 * @Description:
 */
@Data
public class PaymentInformationVo implements Serializable{
    private String myCourse;
    private Integer amountInstallments;
    private String paymentCycle;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date classHours;
}
