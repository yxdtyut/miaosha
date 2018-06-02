package com.yxdtyut.miaosha.service.impl;

import com.yxdtyut.miaosha.entity.MiaoshaOrder;
import com.yxdtyut.miaosha.entity.MiaoshaUser;
import com.yxdtyut.miaosha.key.MiaoshaKey;
import com.yxdtyut.miaosha.key.OrderKey;
import com.yxdtyut.miaosha.service.MiaoshaService;
import com.yxdtyut.miaosha.service.OrderService;
import com.yxdtyut.miaosha.service.RedisService;
import com.yxdtyut.miaosha.util.MD5Utils;
import com.yxdtyut.miaosha.util.UUIDUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @Author : yangxudong
 * @Description :   秒杀service的实现类
 * @Date : 下午3:56 2018/6/1
 */
@Service
public class MiaoshaServiceImpl implements MiaoshaService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private OrderService orderService;

    public BufferedImage createVerifyCode(MiaoshaUser user, String goodsId) {
        if(user == null || StringUtils.isEmpty(goodsId)) {
            return null;
        }
        int width = 80;
        int height = 32;
        //create the image
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        // set the background color
        g.setColor(new Color(0xDCDCDC));
        g.fillRect(0, 0, width, height);
        // draw the border
        g.setColor(Color.black);
        g.drawRect(0, 0, width - 1, height - 1);
        // create a random instance to generate the codes
        Random rdm = new Random();
        // make some confusion
        for (int i = 0; i < 50; i++) {
            int x = rdm.nextInt(width);
            int y = rdm.nextInt(height);
            g.drawOval(x, y, 0, 0);
        }
        // generate a random code
        String verifyCode = generateVerifyCode(rdm);
        g.setColor(new Color(0, 100, 0));
        g.setFont(new Font("Candara", Font.BOLD, 24));
        g.drawString(verifyCode, 8, 24);
        g.dispose();
        //把验证码存到redis中
        int rnd = calc(verifyCode);
        redisService.set(MiaoshaKey.miaoshaVerifyCode, user.getId()+","+goodsId, rnd);
        //输出图片
        return image;
    }

    @Override
    public boolean configVerifyCode(MiaoshaUser user, String goodsId, int verifyCode) {
        Integer verifyCodeOld = redisService.get(MiaoshaKey.miaoshaVerifyCode,user.getId()+","+goodsId,Integer.class);
        return verifyCodeOld == verifyCode;
    }

    @Override
    public String createMiaoshaRandomPath(MiaoshaUser user, String goodsId) {
        String str = MD5Utils.md5(UUIDUtils.uuid());
        redisService.set(MiaoshaKey.path, user.getId() + "_" + goodsId, str);
        return str;
    }

    @Override
    public String getMiaoshaResult(String id, String goodsId) {
        MiaoshaOrder miaoshaOrder = orderService.getMiaoshaOrderByUserIdAndGoodsId(id, goodsId);
        if (miaoshaOrder != null) {
            return miaoshaOrder.getOrderId();
        } else{
            Boolean goodsOverFlag = redisService.exists(MiaoshaKey.goodsOver, goodsId);
            if (goodsOverFlag) {
                return "-1";
            } else {
                return "0";
            }
        }
    }

    private static int calc(String exp) {
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("JavaScript");
            return (Integer)engine.eval(exp);
        }catch(Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static char[] ops = new char[] {'+', '-', '*'};
    /**
     * + - *
     * */
    private String generateVerifyCode(Random rdm) {
        int num1 = rdm.nextInt(10);
        int num2 = rdm.nextInt(10);
        int num3 = rdm.nextInt(10);
        char op1 = ops[rdm.nextInt(3)];
        char op2 = ops[rdm.nextInt(3)];
        String exp = ""+ num1 + op1 + num2 + op2 + num3;
        return exp;
    }
}
