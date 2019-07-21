package com.zr.xuezhu.Live.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
//教育情况表
@Data
public class Education {
    private Integer id;
    //用户表外键
    private Integer uid;

    private Integer payOffStatus;

    private Integer occupation;

    private Integer education;

    private String graduationSchool;

    private String major;

    @JsonFormat(pattern="yyyy",timezone="GMT+8")
    private Date graduationYear;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    private String createName;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

    private String updateName;

    private Integer status;

}
