package com.zr.xuezhu.paymentinformation.mapper;

import com.zr.xuezhu.paymentinformation.pojo.PaymentInformation;
import com.zr.xuezhu.userLogin.pojo.UserLogin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by Wjerry on 2019/7/3.
 */
@Mapper
public interface PaymentInformationMapper {
    @Select("select * from paymentinformation where loginId=#{loginId} and payOffStatus=0")
    List<PaymentInformation> queryByLoginId(Integer loginId);

    @Insert("insert into paymentinformation(loginId,myCourse,amountInstallments,paymentCycle," +
            "classHours,createTime,createName,updateTime,updateName) values(#{loginId}," +
            "#{myCourse},#{amountInstallments},#{paymentCycle},#{classHours},#{createTime},#{createName},#{updateTime},#{updateName})")
    int installmentApply(PaymentInformation paymentInformation);

    @Update("update paymentinformation set createName=#{userName} where loginId=#{id}")
    int updateCreateName(UserLogin userLogin);

    int updatePaymentByLoginId(PaymentInformation paymentInformation);
}
