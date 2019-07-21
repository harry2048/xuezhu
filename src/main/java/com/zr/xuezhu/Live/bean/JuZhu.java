package com.zr.xuezhu.Live.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
//居住表
@Data
public class JuZhu {
   private Integer id;
   //用户表外键
    private Integer loginId;

    private Integer maritalStatus;

    private Integer liveStatus;

    private String LiveStatusName;

    private Integer liveYear;

    private String province;

    private String city;

    private String area;

    private String detailedAddress;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    private String createName;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

    private String updateName;



}
