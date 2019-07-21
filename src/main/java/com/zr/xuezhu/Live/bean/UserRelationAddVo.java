package com.zr.xuezhu.Live.bean;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

//关系情况表
@Data
public class UserRelationAddVo  {

    private Integer id;
    //家庭联系人
    @NotBlank(message = "家庭联系人不能为空")
    private String familyContacts;

    @NotNull(message = "和我的关系不能为空")
    private Integer relationsWithMe;

    @NotBlank(message = "联系人电话不能为空")
    private String contactTelephone;

    @NotNull(message = "常用家庭联系人不能为空")
    private String commonFamilyContacts;

    @NotNull(message = "常用和我的关系不能为空")
    private Integer commonRelationsWithMe;

    @NotBlank(message = "常用联系人电话不能为空")
    private String commonPhone;



}
