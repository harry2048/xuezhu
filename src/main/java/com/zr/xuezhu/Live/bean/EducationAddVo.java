package com.zr.xuezhu.Live.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

//教育情况表
@Data
public class EducationAddVo {

    private Integer id;

    private Integer payOffStatus;

    @NotNull(message = "职业不能为空")
    private Integer occupation;

    @NotNull(message = "学历不能为空")
    private Integer education;

    @NotBlank(message = "毕业学校")
    private String graduationSchool;

    @NotBlank(message = "毕业专业不能为空")
    private String major;

    @NotNull(message = "毕业年份")
    @JsonFormat(pattern="yyyy",timezone="GMT+8")
    private Date graduationYear;





}
