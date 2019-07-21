package com.zr.xuezhu.userpicture.mapper;

import com.zr.xuezhu.userpicture.bean.UserPicture;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ImportMapper {
    @Select("select * from userpicture where loginId = #{loginId}")
    UserPicture queryByLoginId(@Param("loginId") Integer loginId);

    @Update("update userpicture set idPositive=#{remotePath} where id = #{id}")
    void updatePicIdPostiveById(@Param("id") Integer id, @Param("remotePath") String remotePath);

    @Insert("insert into userpicture(loginId,idPositive) values(#{loginId},#{remotePath})")
    void insertPicIdPostiveById(@Param("loginId") Integer loginId, @Param("remotePath") String remotePath);

}
