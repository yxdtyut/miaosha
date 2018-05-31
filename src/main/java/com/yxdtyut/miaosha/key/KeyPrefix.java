package com.yxdtyut.miaosha.key;

/**
 * @Author : yangxudong
 * @Description :   redis中保存key的前缀
 * @Date : 下午3:28 2018/5/29
 */

public interface KeyPrefix {
    public Integer expireSeconds();

    public String getPrefix();
}
