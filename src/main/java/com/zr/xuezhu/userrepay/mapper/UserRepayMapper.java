package com.zr.xuezhu.userrepay.mapper;

import com.zr.xuezhu.userrepay.pojo.UserRepay;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by ZLFamily on 2019/7/3.
 */
@Mapper
public interface UserRepayMapper {

    //添加数据
    @Insert("insert into userrepay (loginId,bankCardAccount,openingBank,bankPhone,createTime,createName,updateTime,updateName) value (#{loginId},#{bankCardAccount},#{openingBank},#{bankPhone},#{createTime},#{createName},#{updateTime},#{updateName})")
    int addUserRepay(UserRepay userRepay);

    //查询预留手机号
    @Select("select * from userrepay where loginId = #{id} and payOffStatus=0")
    UserRepay queryBackPhone(@Param("id") Integer id);

    @Select("select verificationCode from userlogin where id = #{id}")
    String queryCodeByLoginId(@Param("id") Integer id);

    int updateUserRepayByLoginId(UserRepay userRepay);
}
