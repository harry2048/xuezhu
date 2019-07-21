package com.zr.xuezhu.Live.mapper;

import com.zr.xuezhu.Live.bean.Education;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface EducationMapper {

    @Select("select * from useredu where loginId=#{uid} and payOffStatus=0")
    List<Education> queryById(@Param("uid") Integer uid);

    @Insert("insert into useredu(loginId,occupation,education,major,graduationSchool,graduationYear,createTime,createName,updateTime,updateName)" +
            " value(#{uid},#{occupation},#{education},#{major},#{graduationSchool},#{graduationYear},#{createTime},#{createName},#{updateTime},#{updateName})")
    int add(Education education);

    int updateEduByLoginId(Education education);
}
