package com.gientech.stms.application.validate;


import com.gientech.stms.application.persistent.entity.OrderDO;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
* 订单表(Order)校验逻辑
*
* @author sunzh
* @since 2025-01-02 17:48:47
*/
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = OrderCheck.OrderValidator.class)
public @interface OrderCheck {
    /**
    * 是否允许为空
    */
    boolean required() default true;

    /**
    * 校验不通过返回的提示信息
    */
    String message() default "不是一个手机号码格式";

    /**
    * Constraint要求的属性，用于分组校验和扩展，留空就好
    */
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    //注解校验器
    class OrderValidator implements ConstraintValidator<OrderCheck, OrderDO> {
        private boolean required = false;
        /**
        * 在验证开始前调用注解里的方法，从而获取到一些注解里的参数
        *
        * @param constraintAnnotation annotation instance for a given constraint declaration
        */
        @Override
        public void initialize(OrderCheck constraintAnnotation) {
            this.required = constraintAnnotation.required();
        }
        /**
        * 判断参数是否合法
        *
        * @param value   object to validate
        * @param context context in which the constraint is evaluated
        */
        @Override
        public boolean isValid(OrderDO value, ConstraintValidatorContext context) {
            if (this.required) {
                return false;
            }
            return true;
        }

    }
}




