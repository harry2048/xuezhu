package com.zr.xuezhu.Live.mapper;

import com.zr.xuezhu.Live.bean.JuZhu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface JuZhuMapper {
    int add(JuZhu juZhu);


    @Select("select * from userlive where loginId=#{loginId} and payOffStatus=0")
    List<JuZhu> queryById(@Param("loginId") Integer loginId);

    int updateJuzhuByLoginId(JuZhu juZhu);
}
