package com.zr.xuezhu.paydivide.mapper;

import com.zr.xuezhu.paydivide.pojo.PayDivide;
import com.zr.xuezhu.paydivide.pojo.PayDivideAddVo;
import com.zr.xuezhu.paydivide.pojo.PayDivideDTO;
import com.zr.xuezhu.paydivide.pojo.PayDivideShowVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by ZLFamily on 2019/7/3.
 */
@Mapper
public interface PayDivideMapper {


    List<PayDivideDTO> queryPage(@Param("id") Integer id);

//    List<PayDivideAddVo> queryByLoginId(@Param("loginId")Integer loginId);

    int insert(PayDivideAddVo payDivideAddVo);

    int updateFenQi(PayDivideAddVo payDivideAddVo);

    PayDivide queryStatusByLoginId(@Param("id") Integer loginId);

    @Update("update paydivide set status=0 where id=#{id}")
    int updateStatus(Integer id);

}
