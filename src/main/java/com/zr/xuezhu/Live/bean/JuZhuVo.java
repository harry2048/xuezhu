package com.zr.xuezhu.Live.bean;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Auther: gengwei
 * @Date: 2019-07-07 21:56
 * @Description:
 */
@Data
public class JuZhuVo implements Serializable {
    private String maritalStatus;

    private String liveStatus;

    private String liveYear;

    private String detailedAddress;

    private String province;
}
