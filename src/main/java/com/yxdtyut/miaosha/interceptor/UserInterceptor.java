package com.yxdtyut.miaosha.interceptor;

import com.yxdtyut.miaosha.annotation.UserAccess;
import com.yxdtyut.miaosha.common.CommonParams;
import com.yxdtyut.miaosha.common.UserContext;
import com.yxdtyut.miaosha.entity.MiaoshaUser;
import com.yxdtyut.miaosha.key.UserKey;
import com.yxdtyut.miaosha.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author : yangxudong
 * @Description :   用户拦截器
 * @Date : 下午1:51 2018/5/30
 */
@Component
public class UserInterceptor extends HandlerInterceptorAdapter{


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            UserAccess userAccess = handlerMethod.getMethodAnnotation(UserAccess.class);
            if (userAccess == null) {
                return true;
            }
        }
        return true;
    }



}
