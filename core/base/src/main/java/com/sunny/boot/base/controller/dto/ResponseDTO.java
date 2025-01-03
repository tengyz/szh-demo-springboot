package com.sunny.boot.base.controller.dto;


import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.sunny.boot.base.exception.code.ResultEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseDTO<T> implements Serializable {
    private static final long serialVersionUID = -4696008537295855861L;
    private T data;
    private Integer code;
    private String msg;
    private String desc;
    private static final String SUCCESS_MSG = "操作成功";
    private static final String FAILED_MSG = "操作失败";

    public ResponseDTO(T data, Integer code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public ResponseDTO(T data, Integer code, String msg, String desc) {
        this.data = data;
        this.code = code;
        this.msg = msg;
        this.desc = desc;
    }

    public static <T> ResponseDTO<T> succeed() {
        return succeedWith((T) null, ResultEnum.SUCCESS.getCode(), "操作成功");
    }

    public static <T> ResponseDTO<T> succeed(String msg) {
        return succeedWith((T) null, ResultEnum.SUCCESS.getCode(), msg);
    }

    public static <T> ResponseDTO<T> succeed(T model) {
        return succeedWith(model, ResultEnum.SUCCESS.getCode(), "操作成功");
    }

    public static <T> ResponseDTO<T> handleFeign(ResponseDTO<T> response) {
        ResponseDTO result;
        if (ResultEnum.SUCCESS.getCode().equals(response.getCode())) {
            result = succeed(response.getData());
            result.setDesc(response.getDesc());
            return result;
        } else {
            result = failed(response.getMsg());
            if (ObjectUtils.isNotEmpty(response) && ObjectUtils.isNotEmpty(response.getData())) {
                result.setData(response.getData());
            }

            return result;
        }
    }

    public static <T> ResponseDTO<T> succeed(T model, String msg) {
        return succeedWith(model, ResultEnum.SUCCESS.getCode(), msg);
    }

    public static <T> ResponseDTO<T> succeed(T model, String msg, String desc) {
        return succeedWith(model, ResultEnum.SUCCESS.getCode(), msg, desc);
    }

    public static <T> ResponseDTO<T> succeedWith(T data, Integer code, String msg, String desc) {
        return new ResponseDTO(data, code, msg, desc);
    }

    public static <T> ResponseDTO<T> succeedWith(T data, Integer code, String msg) {
        return new ResponseDTO(data, code, msg);
    }

    public static <T> ResponseDTO<T> failed(String msg) {
        return failedWith(null, ResultEnum.ERROR.getCode(), msg);
    }

    public static <T> ResponseDTO<T> failed() {
        return failedWith(null, ResultEnum.ERROR.getCode(), "操作失败");
    }

    public static <T> ResponseDTO<T> failed(T model, String msg) {
        return failedWith(model, ResultEnum.ERROR.getCode(), msg);
    }

    public static <T> ResponseDTO<T> failedWith(T data, Integer code, String msg) {
        return new ResponseDTO(data, code, msg);
    }

    public boolean succeedStatus() {
        return ResultEnum.SUCCESS.getCode().equals(this.code);
    }

    public boolean errorStatus() {
        return !this.succeedStatus();
    }


}
