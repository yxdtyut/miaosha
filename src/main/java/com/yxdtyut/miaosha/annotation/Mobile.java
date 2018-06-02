package com.yxdtyut.miaosha.annotation;

import com.yxdtyut.miaosha.annotation.validator.MobileConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Author : yangxudong
 * @Description :   主键类
 * @Date : 上午10:58 2018/5/31
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {MobileConstraintValidator.class })
public @interface Mobile {

    String message() default "{手机号格式不正确}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
