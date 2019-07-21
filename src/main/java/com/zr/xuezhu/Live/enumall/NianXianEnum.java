package com.zr.xuezhu.Live.enumall;

import lombok.Getter;

@Getter
public enum NianXianEnum {
    BANNIAN("半年",0),YINIAN("一年",1),
    LIANGNIAN("两年",2),SANNIAN("三年",3);

    private final String liveYearName;
    private final Integer liveYearValue;
    // 通过值取名称
    public static String getName(int liveYearValue) {
        for (NianXianEnum c : NianXianEnum.values()) {
            if (c.getLiveYearValue() == liveYearValue) {
                return c.liveYearName;
            }
        }
        return null;
    }
    // 通过名称取值
    public static Integer getValue(String statusName) {
        for (NianXianEnum c : NianXianEnum.values()) {
            if (c.getLiveYearName().equals(statusName)) {
                return c.getLiveYearValue();
            }
        }
        return null;
    }

    NianXianEnum(String liveYearName, Integer liveYearValue)
    {
        this.liveYearName = liveYearName;
        this.liveYearValue = liveYearValue;
    }

}