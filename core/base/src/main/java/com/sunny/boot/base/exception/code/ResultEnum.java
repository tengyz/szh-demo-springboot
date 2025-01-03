package com.sunny.boot.base.exception.code;

public enum ResultEnum {
    SUCCESS(0, "接口调用成功"),
    ERROR(1000, "接口调用成功"),
    UNKNOWN(2001, "未知异常"),
    VALIDATE_FAILED(2002, "参数校验失败"),
    COMMON_FAILED(2003, "接口调用失败"),
    FORBIDDEN(2004, "没有权限访问资源");

    private Integer code;
    private String message;

    ResultEnum(int code, String message) {
        this.code=code;
        this.message=message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
