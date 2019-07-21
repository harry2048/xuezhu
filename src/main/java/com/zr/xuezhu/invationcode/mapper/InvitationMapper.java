package com.zr.xuezhu.invationcode.mapper;

import com.zr.xuezhu.userLogin.pojo.UserLogin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by Wjerry on 2019/7/3.
 */
@Mapper
public interface InvitationMapper {
    @Select("select * from userlogin where iphone=#{iphone}")
    List<UserLogin> queryByPhone(String iphone);

    @Update("update userlogin set invitationCode=#{invitationCode},updateTime=#{updateTime} where id=#{id}")
    int inputInvitationCode(UserLogin userLogin);
}
