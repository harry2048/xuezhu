package com.zr.xuezhu.userrepay.meiju;

/**
 * Created by ZLFamily on 2019/7/3.
 */
public enum StatusEnum {
    GONGSHANG("工商银行",0),
    NONGYE("农业银行",1),
    YOUZHENG("邮政储蓄银行",2),
    ZHAOSHANG("招商银行",3),
    ZHONGRUAN("中软云际",4),
    SHOUQUANSHU("授权书",5);
    ;

    private final String statusName;
    private final Integer statusValue;

    private StatusEnum(String statusName, Integer statusValue){
        this.statusName = statusName;
        this.statusValue = statusValue;
    }
    // 普通方法
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

    public String getStatusName() {
        return statusName;
    }

    public Integer getStatusValue() {
        return statusValue;
    }
}
