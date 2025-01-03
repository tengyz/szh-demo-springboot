package com.sunny.boot.base.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * 交易类型
 * @author
 * @Date
 */
public enum TradeTypeEnum implements IEnum<Integer> {
    汇入汇款(0, "汇入汇款"),
    汇出汇款(1, "汇出汇款"),
    即期结售汇(2, "即期结售汇");
    @EnumValue
    Integer code;
    String name;

    TradeTypeEnum(int code, String name) {
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
    public static TradeTypeEnum getByCode(Integer code) {
        return IEnum.getEnumByCode(code, TradeTypeEnum.values());
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
