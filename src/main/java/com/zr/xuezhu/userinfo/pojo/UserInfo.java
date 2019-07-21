package com.zr.xuezhu.userinfo.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Created by Wjerry on 2019/7/3.
 */
@Data
public class UserInfo {

    private Integer id;
    private Integer loginId;
    private String name;
    private String idCode;
    private String phone;
    private String qqCode;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private String createName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    private String updateName;

}
