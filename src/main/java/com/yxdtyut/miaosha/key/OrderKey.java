package com.yxdtyut.miaosha.key;

/**
 * @Author : yangxudong
 * @Description :   订单key
 * @Date : 下午3:34 2018/5/29
 */
public class OrderKey extends BasePrefixKey {

    public OrderKey(Integer expireSeconds, String prefixKey) {
        super(expireSeconds, prefixKey);
    }

    public OrderKey(String prefixKey) {
        super(prefixKey);
    }

    public static OrderKey miaosha = new OrderKey("miaosha");
}
