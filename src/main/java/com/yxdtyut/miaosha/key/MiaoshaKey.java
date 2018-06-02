package com.yxdtyut.miaosha.key;

/**
 * @Author : yangxudong
 * @Description :   订单key
 * @Date : 下午3:34 2018/5/29
 */
public class MiaoshaKey extends BasePrefixKey {

    public MiaoshaKey(Integer expireSeconds, String prefixKey) {
        super(expireSeconds, prefixKey);
    }

    public MiaoshaKey(String prefixKey) {
        super(prefixKey);
    }

    public static MiaoshaKey miaoshaVerifyCode = new MiaoshaKey("miaoshaVerifyCode");
    public static MiaoshaKey path = new MiaoshaKey("miaoshaPath");
    public static MiaoshaKey goodsOver = new MiaoshaKey("goodsOver");

    public static MiaoshaKey preventBrushWithSecond(Integer expireSeconds) {
        return new MiaoshaKey(expireSeconds, "preventBrush");
    }
}
