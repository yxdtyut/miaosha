package com.yxdtyut.miaosha.annotation.validator;

import com.yxdtyut.miaosha.annotation.Mobile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author : yangxudong
 * @Description :   手机格式验证
 * @Date : 上午10:59 2018/5/31
 */

public class MobileConstraintValidator implements ConstraintValidator<Mobile,String> {

    @Override
    public void initialize(Mobile constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile("1\\d{10}");
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
