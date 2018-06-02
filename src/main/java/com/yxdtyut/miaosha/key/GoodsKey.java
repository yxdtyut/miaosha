package com.yxdtyut.miaosha.key;

/**
 * @Author : yangxudong
 * @Description :   商品key
 * @Date : 下午3:34 2018/5/29
 */
public class GoodsKey extends BasePrefixKey {

    public GoodsKey(Integer expireSeconds, String prefixKey) {
        super(expireSeconds, prefixKey);
    }

    public GoodsKey(String prefixKey) {
        super(prefixKey);
    }

    public static GoodsKey stockCount = new GoodsKey("stockCount");
    public static GoodsKey listPage = new GoodsKey(60,"listPage");
    public static GoodsKey detailPage = new GoodsKey(60,"detailPage");
}
