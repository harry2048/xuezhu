package com.zr.xuezhu.jigou.mapper;

import com.zr.xuezhu.jigou.pojo.HouTaiSelectVo;
import com.zr.xuezhu.paydivide.pojo.PayDivide;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Wjerry on 2019/7/9.
 * Created by henghengjiji on 2019/7/9.

 */
@Mapper
public interface HouTaiMapper {
    List<PayDivide> queryPage(HouTaiSelectVo houTaiSelectVo);

    int queryCount(HouTaiSelectVo houTaiSelectVo);

    PayDivide queryById(@Param("id") Integer id);

    int updateStatus(PayDivide payDivide);

    int updateStatus2(PayDivide payDivide);
}
