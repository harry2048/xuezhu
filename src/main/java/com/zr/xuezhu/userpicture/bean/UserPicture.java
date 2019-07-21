package com.zr.xuezhu.userpicture.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UserPicture {

    private Integer id;
    private Integer loginId;//'注册表外键
    private String idPositive;//'身份证正面
    private String idOtherSide;//'身份证反面
    private String phoneIdPicture;//'手机身份证照片
    private String videoAuthentication;//'视频认证
    private String trainingContract;//'培训合同照片
    private String diploma;//'毕业证照片
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;//'创建时间
    private String createName;//创建人
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime; //修改时间
    private String updateName;//修改人


}

