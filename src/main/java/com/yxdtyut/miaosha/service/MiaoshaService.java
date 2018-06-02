package com.yxdtyut.miaosha.service;

import com.yxdtyut.miaosha.entity.MiaoshaUser;

import java.awt.image.BufferedImage;

/**
 * @Author : yangxudong
 * @Description :   秒杀相关的service
 * @Date : 下午3:55 2018/6/1
 */

public interface MiaoshaService {
    /**生成验证码.*/
    BufferedImage createVerifyCode(MiaoshaUser user, String goodsId);

    /**验证验证码.*/
    boolean configVerifyCode(MiaoshaUser user, String goodsId, int verifyCode);

    /**创建秒杀随机路径.*/
    String createMiaoshaRandomPath(MiaoshaUser user, String goodsId);

    String getMiaoshaResult(String id, String goodsId);
}
