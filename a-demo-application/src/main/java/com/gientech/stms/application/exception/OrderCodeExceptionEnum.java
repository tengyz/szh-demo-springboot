package com.gientech.stms.application.exception;


import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.sunny.boot.base.enums.IEnum;
/**
 * 订单表(Order)表业务异常枚举
 *
 * @author sunzh
 * @since 2025-01-02 17:48:47
 */

public enum OrderCodeExceptionEnum implements IEnum<Integer> {
    /**
    * 不同模块异常 编号 从3100开始分配
    */
    Order3100(3100, "业务未知异常");
    @EnumValue
    Integer code;
    String name;

    OrderCodeExceptionEnum (int code, String name) {
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
    public static OrderCodeExceptionEnum  getByCode(Integer code) {
    return IEnum.getEnumByCode(code, OrderCodeExceptionEnum .values());
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

 
