package com.yxdtyut.miaosha.key;

import lombok.Data;

/**
 * @Author : yangxudong
 * @Description :   用户key
 * @Date : 下午3:34 2018/5/29
 */
public class UserKey extends BasePrefixKey {

    public UserKey(Integer expireSeconds, String prefixKey) {
        super(expireSeconds, prefixKey);
    }

    public UserKey(String prefixKey) {
        super(prefixKey);
    }

    public static UserKey userId = new UserKey("id");
    public static UserKey token = new UserKey(3600*24*7, "token");
}
