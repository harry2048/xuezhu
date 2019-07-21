package com.zr.xuezhu.Live.enumall;

import lombok.Getter;

@Getter
public enum HuiYinEnum {
    YIHUI("已婚",0),WEIHUN("未婚",1);

    private final String maritalStatusName;
    private final Integer maritalStatusValue;
    // 通过值取名称
    public static String getName(int HunYinStatusValue) {
        for (HuiYinEnum c : HuiYinEnum.values()) {
            if (c.getMaritalStatusValue() == HunYinStatusValue) {
                return c.maritalStatusName;
            }
        }
        return null;
    }
    // 通过名称取值
    public static Integer getValue(String statusName) {
        for (HuiYinEnum c : HuiYinEnum.values()) {
            if (c.getMaritalStatusName().equals(statusName)) {
                return c.getMaritalStatusValue();
            }
        }
        return null;
    }

    HuiYinEnum(String maritalStatusName, Integer maritalStatusValue)
    {
        this.maritalStatusName = maritalStatusName;
        this.maritalStatusValue = maritalStatusValue;
    }

}