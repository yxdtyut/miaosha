package com.yxdtyut.miaosha.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author : yangxudong
 * @Description :   redis配置类，用来读取redis配置的属性
 * @Date : 下午3:17 2018/5/29
 */
@Configuration
@ConfigurationProperties(prefix = "redis")
@Data
public class MyRedisProperties {
    private String host;
    private int port;
    private int timeout;//秒
    private String password;
    private int poolMaxTotal;
    private int poolMaxIdle;
    private int poolMaxWait;//秒
}
