package com.zr.xuezhu.Live.bean;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;


@Data
public class JuZhuAddVo  {

    private  Integer id;

    @NotNull(message = "婚姻不能为空")
    private Integer maritalStatus;

    @NotNull(message = "居住状态不能为空")
    private Integer liveStatus;

    @NotNull(message = "居住年限不能为空")
    private Integer liveYear;

    @NotBlank(message = "详细地址不能为空")
    private String detailedAddress;

    @NotBlank(message = "省不能为空")
    private String province;
    /*@NotBlank(message = "市不能为空")
    private String city;
    @NotBlank(message = "地区不能为空")
    private String area;*/


}
