package com.sunny.boot.base.enums;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;
import java.util.stream.Stream;

public interface IEnum <V>{
    String getName();
    @JsonValue
    V getValue();

    static <T extends Enum<T>> T  getEnumByCode(Object code, T [] enus) {
        if(StrUtil.isBlank(StrUtil.toString(code))|| !NumberUtil.isNumber(StrUtil.toString(code))){
            return null;
        }
        Integer curCode = NumberUtil.parseInt(StrUtil.toString(code));
        return Stream.of(enus)
                .filter(value -> Objects.equals(curCode, ((IEnum) value).getValue()))
                .findAny()
                .orElse(null);
    }
}
