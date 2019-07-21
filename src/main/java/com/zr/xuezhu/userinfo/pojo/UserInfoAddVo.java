package com.zr.xuezhu.userinfo.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Wjerry on 2019/7/3.
 */
@Data
public class UserInfoAddVo implements Serializable{

//    private Integer id;
    private Integer loginId;
    @NotBlank(message = "姓名不能为空")
    private String name;
    @NotBlank(message = "身份证号不能为空")
    private String idCode;
    @NotNull(message = "手机号不能为空")
    private String phone;
    @NotNull(message = "QQ号不能为空")
    private String qqCode;

}
