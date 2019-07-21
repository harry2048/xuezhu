package com.zr.xuezhu.Live.enumall;

import lombok.Getter;

@Getter
public enum RelationEnum {
    FUNV("父女",0),MUNV("母女",1),
    JIEMEI("姐妹",2),XIONGDI("兄弟",3);

    private final String relationsWithMeName;
    private final Integer relationsWithMeValue;
    // 通过值取名称
    public static String getName(int relationsWithMeValue) {
        for (RelationEnum c : RelationEnum.values()) {
            if (c.getRelationsWithMeValue() == relationsWithMeValue) {
                return c.relationsWithMeName;
            }
        }
        return null;
    }
    // 通过名称取值
    public static Integer getValue(String statusName) {
        for (RelationEnum c : RelationEnum.values()) {
            if (c.getRelationsWithMeName().equals(statusName)) {
                return c.getRelationsWithMeValue();
            }
        }
        return null;
    }

    RelationEnum(String relationsWithMeName, Integer relationsWithMeValue)
    {
        this.relationsWithMeName = relationsWithMeName;
        this.relationsWithMeValue = relationsWithMeValue;
    }

}