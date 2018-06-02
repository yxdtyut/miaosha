package com.yxdtyut.miaosha.config;

import com.yxdtyut.miaosha.interceptor.UserInterceptor;
import com.yxdtyut.miaosha.interceptor.UserNotNullInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @Author : yangxudong
 * @Description :   web配置类
 * @Date : 下午4:56 2018/5/29
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private UserArgumentResolver userArgumentResolver;

    @Autowired
    private UserInterceptor userInterceptor;

    @Autowired
    private UserNotNullInterceptor userNotNullInterceptor;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        //super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(userArgumentResolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userNotNullInterceptor)
                .addPathPatterns("/**").excludePathPatterns("/user/do_login","/");
        registry.addInterceptor(userInterceptor);
    }
}
