package com.zr.xuezhu.userinfo.mapper;

import com.zr.xuezhu.userinfo.pojo.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Wjerry on 2019/7/3.
 */
@Mapper
public interface UserInfoMapper {
    @Select("select * from userinfo where phone=#{phone}")
    List<UserInfo> queryByPhone(String iphone);

    @Insert("insert into userinfo(loginId,name,idCode,phone,qqCode,createTime,createName,updateTime,updateName) " +
            "values(#{loginId},#{name},#{idCode},#{phone},#{qqCode},#{createTime},#{createName},#{updateTime},#{updateName})")
    int essentialInformation(UserInfo userInfo);

    @Update("update userinfo set name=#{name},idCode=#{idCode},phone=#{phone},qqCode=#{qqCode} where loginId=#{loginId}")
    int updateUserInfo(UserInfo userInfo);

    @Select("select * from userinfo where loginId = #{loginId} and payOffStatus=0")
    List<UserInfo> queryByLoginId(@Param("loginId") Integer loginId);
}
