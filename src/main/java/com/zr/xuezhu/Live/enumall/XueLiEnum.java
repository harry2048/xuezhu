package com.zr.xuezhu.Live.enumall;

import lombok.Getter;

@Getter
public enum XueLiEnum {
    BOSHI("博士",0),SHUOSHI("硕士",1),
    BENKE("本科",2),ZHUANKE("专科",3),
    GAOZHONG("高中",4),CHUZHONG("初中",5);

    private final String educationName;
    private final Integer educationValue;
    // 通过值取名称
    public static String getName(int HunYinStatusValue) {
        for (XueLiEnum c : XueLiEnum.values()) {
            if (c.getEducationValue() == HunYinStatusValue) {
                return c.educationName;
            }
        }
        return null;
    }
    // 通过名称取值
    public static Integer getValue(String statusName) {
        for (XueLiEnum c : XueLiEnum.values()) {
            if (c.getEducationName().equals(statusName)) {
                return c.getEducationValue();
            }
        }
        return null;
    }

    XueLiEnum(String educationName, Integer educationValue)
    {
        this.educationName = educationName;
        this.educationValue = educationValue;
    }

}