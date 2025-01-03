package com.sunny.boot.base.exception;

import com.sunny.boot.base.enums.IEnum;
import com.sunny.boot.base.exception.code.DbCodeBusinessExceptionEnum;
import lombok.Data;

@Data
public class BusinessException extends RuntimeException {
    IEnum<Integer> codeEnum;

    public BusinessException(DbCodeBusinessExceptionEnum codeEnum) {
        this.codeEnum=codeEnum;
    }
}