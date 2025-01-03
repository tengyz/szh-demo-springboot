package com.sunny.boot.base.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * 交易类型
 * @author
 * @Date
 */
public enum YesOrNoEnum implements IEnum<Integer> {
    否(0, "否"),
    是(1, "是");
    @EnumValue
    Integer code;
    String name;

    YesOrNoEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 根据code取枚举对象
     *
     * @param code
     * @return
     */
    @JsonCreator
    public static YesOrNoEnum getByCode(Integer code) {
        return IEnum.getEnumByCode(code, YesOrNoEnum.values());
    }

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public Integer getValue() {
        return code;
    }

}
