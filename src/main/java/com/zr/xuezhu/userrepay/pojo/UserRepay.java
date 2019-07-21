package com.zr.xuezhu.userrepay.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by ZLFamily on 2019/7/3.
 */
@Data
public class UserRepay {

    private Integer id;//主键id

    private Integer loginId;//注册表外键

    private String bankCardAccount;//银行卡账号

    private Integer openingBank;//开户行

    private String bankPhone;//银行手机号

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;//创建时间

    private String createName;//创建人

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;//修改时间

    private String updateName;//修改人

}
