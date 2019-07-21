package com.zr.xuezhu.Live.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

//教育情况表
@Data
public class UserRelation {

    private Integer id;
    //用户表外键
    private Integer loginId;
    //家庭联系人
    private String familyContacts;

    private Integer relationsWithMe;

    private String contactTelephone;

    private String commonFamilyContacts;

    private Integer commonRelationsWithMe;

    private String commonPhone;


    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    private String createName;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

    private String updateName;


}
