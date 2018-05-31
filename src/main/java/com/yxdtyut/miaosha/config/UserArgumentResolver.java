package com.yxdtyut.miaosha.config;

import com.yxdtyut.miaosha.common.CommonParams;
import com.yxdtyut.miaosha.common.UserContext;
import com.yxdtyut.miaosha.entity.MiaoshaUser;
import com.yxdtyut.miaosha.key.UserKey;
import com.yxdtyut.miaosha.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author : yangxudong
 * @Description :   获取用户信息
 * @Date : 上午11:03 2018/5/30
 */
@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {



    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> parameterType = methodParameter.getParameterType();
        return parameterType == MiaoshaUser.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        return UserContext.getMiaoshaUser();
    }


}
