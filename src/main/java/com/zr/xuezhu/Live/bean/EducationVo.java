package com.zr.xuezhu.Live.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: gengwei
 * @Date: 2019-07-07 21:32
 * @Description:
 */
@Data
public class EducationVo implements Serializable{

    private Integer payOffStatus;

    private String occupation;

    private String education;

    private String graduationSchool;

    private String major;

    @JsonFormat(pattern="yyyy",timezone="GMT+8")
    private Date graduationYear;
}
