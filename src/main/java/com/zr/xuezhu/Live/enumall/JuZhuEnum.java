package com.zr.xuezhu.Live.enumall;

import lombok.Getter;

@Getter
public enum JuZhuEnum {
    BENDI("本地",0),WEIHUN("外地",1);

    private final String liveStatusName;
    private final Integer liveStatusValue;
    // 通过值取名称
    public static String getName(int liveStatusValue) {
        for (JuZhuEnum c : JuZhuEnum.values()) {
            if (c.getLiveStatusValue() == liveStatusValue) {
                return c.liveStatusName;
            }
        }
        return null;
    }
    // 通过名称取值
    public static Integer getValue(String statusName) {
        for (JuZhuEnum c : JuZhuEnum.values()) {
            if (c.getLiveStatusName().equals(statusName)) {
                return c.getLiveStatusValue();
            }
        }
        return null;
    }

    JuZhuEnum(String liveStatusName, Integer liveStatusValue)
    {
        this.liveStatusName = liveStatusName;
        this.liveStatusValue = liveStatusValue;
    }

}