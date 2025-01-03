package com.sunny.boot.base.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;
import java.util.stream.Stream;

public enum LogicDeleteEnum implements IEnum<String> {
    删除("Y"),
    未删除("N");
    @EnumValue
    String code;

    LogicDeleteEnum(String strCode) {
        this.code = strCode;
        ;
    }

    /**
     * 根据code取枚举对象
     *
     * @param code
     * @return
     */
    @JsonCreator
    public static LogicDeleteEnum getByCode(String code) {
        return Stream.of(LogicDeleteEnum.values())
                .filter(value -> Objects.equals(code, ((IEnum) value).getValue()))
                .findAny()
                .orElse(null);
    }

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    @JsonIgnore
    public String getValue() {
        return code;
    }

}
