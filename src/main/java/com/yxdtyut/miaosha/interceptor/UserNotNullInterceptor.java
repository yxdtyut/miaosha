package com.yxdtyut.miaosha.interceptor;

import com.yxdtyut.miaosha.common.CommonParams;
import com.yxdtyut.miaosha.common.UserContext;
import com.yxdtyut.miaosha.entity.MiaoshaUser;
import com.yxdtyut.miaosha.key.UserKey;
import com.yxdtyut.miaosha.result.CodeMsg;
import com.yxdtyut.miaosha.result.Result;
import com.yxdtyut.miaosha.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author : yangxudong
 * @Description :   用户必须登陆拦截器
 * @Date : 下午4:36 2018/5/30
 */
@Component
public class UserNotNullInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取用户
        String app_token = request.getParameter(CommonParams.COOKIE_NAME);
        String cookie_token = getCookieToken(request, CommonParams.COOKIE_NAME);
        if (StringUtils.isEmpty(app_token) && StringUtils.isEmpty(cookie_token)) {
            request.setAttribute("msg", Result.error(CodeMsg.USER_NOT_LOGIN).getMsg());
            request.getRequestDispatcher("/").forward(request,response);
            return false;
        }
        String token = StringUtils.isEmpty(app_token)?cookie_token:app_token;

        MiaoshaUser miaoshaUser = redisService.get(UserKey.token, token, MiaoshaUser.class);
        UserContext.setMiaoshaUser(miaoshaUser);
        if (miaoshaUser != null) {
            return true;
        } else {
            request.setAttribute("msg", Result.error(CodeMsg.USER_NOT_LOGIN).getMsg());
            request.getRequestDispatcher("/").forward(request,response);
            return false;
        }
    }

    private String getCookieToken(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length <=0) {
            return null;
        }
        for(Cookie cookie: cookies) {
            if (CommonParams.COOKIE_NAME.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
