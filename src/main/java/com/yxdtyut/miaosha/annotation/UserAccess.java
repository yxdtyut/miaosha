package com.yxdtyut.miaosha.annotation;

import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Author : yangxudong
 * @Description :   自定义用户的注解类
 * @Date : 下午1:53 2018/5/30
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
public @interface UserAccess {

    String message() default "{请先登陆用户!}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    boolean mustLogin() default true;

}
