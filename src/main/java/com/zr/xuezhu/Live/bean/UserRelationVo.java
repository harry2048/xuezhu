package com.zr.xuezhu.Live.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: gengwei
 * @Date: 2019-07-07 21:28
 * @Description:
 */
@Data
public class UserRelationVo implements Serializable{
    private String familyContacts;

    private String relationsWithMe;

    private String contactTelephone;

    private String commonFamilyContacts;

    private String commonRelationsWithMe;

    private String commonPhone;
}
