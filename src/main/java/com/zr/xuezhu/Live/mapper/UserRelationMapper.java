package com.zr.xuezhu.Live.mapper;

import com.zr.xuezhu.Live.bean.UserRelation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserRelationMapper {

    @Select("select * from userrelation where loginId=#{loginId} and payOffStatus=0")
    List<UserRelation> queryById(@Param("loginId") Integer loginId);

    int add(UserRelation userRelation);

    int updateRelationByLoginId(UserRelation userRelation);
}
