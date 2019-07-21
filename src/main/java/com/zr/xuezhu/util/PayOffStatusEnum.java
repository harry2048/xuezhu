package com.zr.xuezhu.util;

import lombok.Getter;

@Getter
public enum PayOffStatusEnum {
    YIHAIQING("已还清",0),WEIHUANQING("未还清",1),
    ZUOFEI("作废",2);

    private final String payOffStatusName;
    private final Integer payOffStatusValue;
    // 通过值取名称
    public static String getName(int payOffStatusValue) {
        for (PayOffStatusEnum c : PayOffStatusEnum.values()) {
            if (c.getPayOffStatusValue() == payOffStatusValue) {
                return c.payOffStatusName;
            }
        }
        return null;
    }
    // 通过名称取值
    public static Integer getValue(String statusName) {
        for (PayOffStatusEnum c : PayOffStatusEnum.values()) {
            if (c.getPayOffStatusName().equals(statusName)) {
                return c.getPayOffStatusValue();
            }
        }
        return null;
    }

    PayOffStatusEnum(String payOffStatusName, Integer payOffStatusValue)
    {
        this.payOffStatusName = payOffStatusName;
        this.payOffStatusValue = payOffStatusValue;
    }

}