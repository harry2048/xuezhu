package com.zr.xuezhu.Live.enumall;

import lombok.Getter;

@Getter
public enum ZhiYeEnum {
    JAVA("java开发工程师",0),PH("PHP开发工程师",1);

    private final String occupationName;
    private final Integer occupationValue;
    // 通过值取名称
    public static String getName(int occupationValue) {
        for (ZhiYeEnum c : ZhiYeEnum.values()) {
            if (c.getOccupationValue() == occupationValue) {
                return c.occupationName;
            }
        }
        return null;
    }
    // 通过名称取值
    public static Integer getValue(String statusName) {
        for (ZhiYeEnum c : ZhiYeEnum.values()) {
            if (c.getOccupationName().equals(statusName)) {
                return c.getOccupationValue();
            }
        }
        return null;
    }
    ZhiYeEnum(String occupationName, Integer occupationValue)
    {
        this.occupationName = occupationName;
        this.occupationValue = occupationValue;
    }
}