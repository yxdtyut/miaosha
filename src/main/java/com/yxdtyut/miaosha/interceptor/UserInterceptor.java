package com.yxdtyut.miaosha.interceptor;

import com.alibaba.fastjson.JSON;
import com.yxdtyut.miaosha.annotation.UserAccess;
import com.yxdtyut.miaosha.common.CommonParams;
import com.yxdtyut.miaosha.common.UserContext;
import com.yxdtyut.miaosha.entity.MiaoshaUser;
import com.yxdtyut.miaosha.key.MiaoshaKey;
import com.yxdtyut.miaosha.key.UserKey;
import com.yxdtyut.miaosha.result.CodeMsg;
import com.yxdtyut.miaosha.result.Result;
import com.yxdtyut.miaosha.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author : yangxudong
 * @Description :   用户拦截器
 * @Date : 下午1:51 2018/5/30
 */
@Component
public class UserInterceptor extends HandlerInterceptorAdapter{

    @Autowired
    private RedisService redisService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            UserAccess userAccess = handlerMethod.getMethodAnnotation(UserAccess.class);
            if (userAccess == null) {
                return true;
            }
            int maxCount = userAccess.maxCount();
            int seconds = userAccess.seconds();
            MiaoshaUser user = UserContext.getMiaoshaUser();
            if (user == null) {
                render(response, CodeMsg.USER_NOT_LOGIN);
                return false;
            }
            String key = request.getRequestURI() + "_" + user.getId();
            Integer count = redisService.get(MiaoshaKey.preventBrushWithSecond(seconds), key, Integer.class);
            if (count == null) {
                redisService.set(MiaoshaKey.preventBrushWithSecond(seconds), key, 1);
            } else if (count < maxCount){
                redisService.incr(MiaoshaKey.preventBrushWithSecond(seconds), key);
            } else {
                render(response, CodeMsg.OPERATION_FREQUENT);
                return false;
            }
        }
        return true;
    }

    private void render(HttpServletResponse response, CodeMsg userNotLogin) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        String s = JSON.toJSONString(Result.error(userNotLogin));
        out.write(s.getBytes("UTF-8"));
        out.flush();
        out.close();
    }


}
