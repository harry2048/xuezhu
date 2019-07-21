package com.zr.xuezhu.userLogin.mapper;

import com.zr.xuezhu.paydivide.pojo.PayDivide;
import com.zr.xuezhu.userLogin.pojo.UserLogin;
import com.zr.xuezhu.userLogin.pojo.UserLoginVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserLoginMapper {

    List<UserLogin> queryByIphone(String iphone);

    int updateByIphone(UserLogin userLogin);

    UserLogin queryByIphoneAndVericode(UserLogin userLogin);

    List<PayDivide>queryPaydivideStatus(Integer loginId);

    int insertIphoneAndCode(UserLogin userLogin);

    int updateCodeByIphone(UserLogin userLogin);

    @Update("update userlogin set userName = #{userName},createName=#{userName},updateName=#{userName} where id=#{id}")
    int updateUserNameByIphone(@Param("userName") String userName,@Param("id") Integer id);

    int updateVerifyCodeById(UserLogin userLogin);

    void deleteVerifyCode();

    int updateVerifyCodeByIphone(@Param("iphone") String iphone);

    int quXiaoShenQing(Integer loginId);
    int qianyue(Integer loginId);
}
