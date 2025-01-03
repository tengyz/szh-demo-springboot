package com.sunny.boot.base.config;

import com.sunny.boot.base.controller.dto.ResponseDTO;
import com.sunny.boot.base.exception.BusinessException;
import com.sunny.boot.base.exception.ForbiddenException;
import com.sunny.boot.base.exception.code.ResultEnum;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * 控制层异常处理切面
 */
@RestControllerAdvice(basePackages = {"com"})
public class ExceptionAdvice {
    /**
     * 捕获 {@code BusinessException} 异常
     */
    @ExceptionHandler({BusinessException.class})
    public ResponseDTO<?> handleBusinessException(BusinessException ex) {
        return ResponseDTO.failed(ex.getCodeEnum().getValue(),ex.getCodeEnum().getName());
    }

    /**
     * 捕获 {@code ForbiddenException} 异常
     */
    @ExceptionHandler({ForbiddenException.class})
    public ResponseDTO<?> handleForbiddenException(ForbiddenException ex) {
        return ResponseDTO.failed(ResultEnum.FORBIDDEN.getMessage());
    }

    /**
     * {@code @RequestBody} 参数校验不通过时抛出的异常处理
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseDTO<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder sb = new StringBuilder("校验失败:");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getField()).append("：").append(fieldError.getDefaultMessage()).append(";\n ");
        }
        String msg = sb.toString();
        if (StringUtils.hasText(msg)) {
            return ResponseDTO.failed(ResultEnum.VALIDATE_FAILED.getCode(), msg);
        }
        return ResponseDTO.failed(ResultEnum.VALIDATE_FAILED.getMessage());
    }

    /**
     * {@code @PathVariable} 和 {@code @RequestParam} 参数校验不通过时抛出的异常处理
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseDTO<?> handleConstraintViolationException(ConstraintViolationException ex) {
        if (StringUtils.hasText(ex.getMessage())) {
            return ResponseDTO.failed(ResultEnum.VALIDATE_FAILED.getCode(), ex.getMessage());
        }
        return ResponseDTO.failed(ResultEnum.VALIDATE_FAILED.getMessage());
    }

    /**
     * 顶级异常捕获并统一处理，当其他异常无法处理时候选择使用
     */
    @ExceptionHandler({Exception.class})
    public ResponseDTO<?> handle(Exception ex) {
        ex.printStackTrace();
        return ResponseDTO.failed(ex.getMessage());
    }
}
