package com.zr.xuezhu.paymentinformation.util;

/**
 * Created by 86151 on 2019/4/25.
 */

public enum StatusEnumRepay {
    RIGHTEEN("6+12个月", 0, 18),
    TWENTYFOUR("6+18个月", 1, 24),
    TWELVE("12个月", 2, 12);

    private String statusName;
    private Integer statusValue;
    private Integer monthNum;

    StatusEnumRepay(String statusName, Integer statusValue, Integer monthNum) {
        this.statusName = statusName;
        this.statusValue = statusValue;
        this.monthNum = monthNum;
    }

    private StatusEnumRepay(String statusName, Integer statusValue)
    {
        this.statusName = statusName;
        this.statusValue = statusValue;
    }
    // 通过值取名称
    public static String getName(int statusValue) {
        for (StatusEnumRepay c : StatusEnumRepay.values()) {
            if (c.getStatusValue() == statusValue) {
                return c.statusName;
            }
        }
        return null;
    }
    // 通过值取月数
    public static Integer getMonthNum(int statusValue) {
        for (StatusEnumRepay c : StatusEnumRepay.values()) {
            if (c.getStatusValue() == statusValue) {
                return c.monthNum;
            }
        }
        return null;
    }
    // 通过名称取值
    public static Integer getValue(String statusName) {
        for (StatusEnumRepay c : StatusEnumRepay.values()) {
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
