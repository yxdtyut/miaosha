package com.yxdtyut.miaosha.key;

import lombok.Data;

/**
 * @Author : yangxudong
 * @Description :   redis中保存key的前缀的抽象类
 * @Date : 下午3:30 2018/5/29
 */
@Data
public abstract class BasePrefixKey implements KeyPrefix {
    private Integer expireSeconds;
    private String prefixKey;

    public BasePrefixKey(Integer expireSeconds, String prefixKey) {
        this.expireSeconds = expireSeconds;
        this.prefixKey = prefixKey;
    }

    public BasePrefixKey(String prefixKey) {
        this.expireSeconds = 0;    //0代表永不过期
        this.prefixKey = prefixKey;
    }

    @Override
    public Integer expireSeconds() {
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className + ":" + prefixKey;
    }
}
