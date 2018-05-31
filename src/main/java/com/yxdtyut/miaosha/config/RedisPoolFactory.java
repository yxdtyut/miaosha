package com.yxdtyut.miaosha.config;

import com.yxdtyut.miaosha.properties.MyRedisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author : yangxudong
 * @Description :   获取jedis连接对象的配置类
 * @Date : 下午3:15 2018/5/29
 */
@Configuration
public class RedisPoolFactory {

    @Autowired
    private MyRedisProperties redisProperties;

    @Bean
    public JedisPool jedisPoolFactory(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(redisProperties.getPoolMaxIdle());
        poolConfig.setMaxTotal(redisProperties.getPoolMaxTotal());
        poolConfig.setMaxWaitMillis(redisProperties.getPoolMaxWait() * 1000);
        JedisPool jp = new JedisPool(poolConfig, redisProperties.getHost(), redisProperties.getPort(),
                redisProperties.getTimeout()*1000, redisProperties.getPassword(), 0);
        return jp;
    }
}
