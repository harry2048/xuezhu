package com.zr.xuezhu.jigou.pojo;

import com.zr.xuezhu.jigou.util.PageVo;
import lombok.Data;

@Data
public class HouTaiSelectVo extends PageVo {

    private String startTime;//起止时间

    private String name;//姓名

    private String orderNum;//订单号

    private Integer status;//状态
}
