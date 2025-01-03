package com.sunny.boot.base.exception.code;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.sunny.boot.base.enums.IEnum;

/**
 *  数据库类公共业务异常枚举
 *
 * @author sunzh
 * @since 2025-01-02 12:00:00
 */

public enum DbCodeBusinessExceptionEnum implements IEnum<Integer> {
    新增失败(3000, "数据库新增失败，主键值是不在数据库存在"),
    更新失败(3001, "数据库更新失败，主键值是不在数据库存在"),
    删除失败(3002, "数据库删除失败，主键值是不在数据库存在"),
    查询失败(3003, "数据库查询失败，主键值是不在数据库存在");
    @EnumValue
    Integer code;
    String name;

    DbCodeBusinessExceptionEnum(int code, String name) {
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
    public static DbCodeBusinessExceptionEnum getByCode(Integer code) {
        return IEnum.getEnumByCode(code, DbCodeBusinessExceptionEnum.values());
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
