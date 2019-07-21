package com.zr.xuezhu.paymentinformation.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Wjerry on 2019/7/3.
 */
@Data
public class PaymentInformationAddVo {

//    private Integer id;
    private Integer loginId;
    @NotNull(message = "课程不能为空")
    private Integer myCourse;
    @NotNull(message = "分期金额不能为空")
    private Integer amountInstallments;
    @NotNull(message = "还款周期不能为空")
    private Integer paymentCycle;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @NotNull(message = "上课时间不能为空")
    private Date classHours;

}
