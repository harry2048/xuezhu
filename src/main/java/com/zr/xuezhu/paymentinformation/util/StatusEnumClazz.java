package com.zr.xuezhu.paymentinformation.util;

/**
 * Created by 86151 on 2019/4/25.
 */

public enum StatusEnumClazz {
    KAIFA("Java开发工程师",0),QUANZHANKAIFA("Java全栈开发工程师",1);

    private final String statusName;
    private final Integer statusValue;

    private StatusEnumClazz(String statusName, Integer statusValue)
    {
        this.statusName = statusName;
        this.statusValue = statusValue;
    }
    // 通过值取名称
    public static String getName(int statusValue) {
        for (StatusEnumClazz c : StatusEnumClazz.values()) {
            if (c.getStatusValue() == statusValue) {
                return c.statusName;
            }
        }
        return null;
    }
    // 通过名称取值
    public static Integer getValue(String statusName) {
        for (StatusEnumClazz c : StatusEnumClazz.values()) {
            if (c.getStatusName().equals(statusName)) {
                return c.getStatusValue();
            }
        }
        return null;
    }


    public String getStatusName() {
        return statusName;
    }

    public Integer getStatusValue() {
        return statusValue;
    }
}
