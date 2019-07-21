package com.zr.xuezhu.util;

import lombok.Getter;

@Getter
public enum StatusEnum {
    ZHENGCHANG("正常状态",0),QUEDING("确定状态",1),
    QUXIAO("取消状态",2),SHENGHETONGGUO("审核通过",3),
    SHENHEBUTONGGUO("审核不通过状态",4),ZUOFEI("打回",5),QIANYUE("签约",6);

    private final String statusName;
    private final Integer statusValue;
    // 通过值取名称
    public static String getName(int statusValue) {
        for (StatusEnum c : StatusEnum.values()) {
            if (c.getStatusValue() == statusValue) {
                return c.statusName;
            }
        }
        return null;
    }
    // 通过名称取值
    public static Integer getValue(String statusName) {
        for (StatusEnum c : StatusEnum.values()) {
            if (c.getStatusName().equals(statusName)) {
                return c.getStatusValue();
            }
        }
        return null;
    }

    StatusEnum(String statusName, Integer statusValue)
    {
        this.statusName = statusName;
        this.statusValue = statusValue;
    }

}