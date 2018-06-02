package com.yxdtyut.miaosha.controller;

import com.yxdtyut.miaosha.annotation.UserAccess;
import com.yxdtyut.miaosha.entity.MiaoshaOrder;
import com.yxdtyut.miaosha.entity.MiaoshaUser;
import com.yxdtyut.miaosha.entity.OrderInfo;
import com.yxdtyut.miaosha.exception.GlobleException;
import com.yxdtyut.miaosha.key.GoodsKey;
import com.yxdtyut.miaosha.key.MiaoshaKey;
import com.yxdtyut.miaosha.key.OrderKey;
import com.yxdtyut.miaosha.rabbitmq.MQSender;
import com.yxdtyut.miaosha.rabbitmq.MiaoshaMessage;
import com.yxdtyut.miaosha.result.CodeMsg;
import com.yxdtyut.miaosha.result.Result;
import com.yxdtyut.miaosha.service.GoodsService;
import com.yxdtyut.miaosha.service.MiaoshaService;
import com.yxdtyut.miaosha.service.OrderService;
import com.yxdtyut.miaosha.service.RedisService;
import com.yxdtyut.miaosha.vo.GoodsVo;
import org.omg.CORBA.CODESET_INCOMPATIBLE;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午1:48 2018/5/31
 */
@Controller
@RequestMapping("/miaosha")
public class MiaoshaController implements InitializingBean {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private MiaoshaService miaoshaService;

    @Autowired
    private MQSender mqSender;

    //定义一个内存标记
    private Map<String, Boolean> localOverMap = new HashMap<>();

    @PostMapping("{path}/do_miaosha")
    @ResponseBody
    public Result<Integer> miaosha(MiaoshaUser user, HttpServletRequest request,
                          Model model,
                                   @PathVariable("path") String path) {
        String goodsId = request.getParameter("goodsId");

        if (localOverMap.get(goodsId)) {
            return Result.error(CodeMsg.STOCK_NOT_ENUOUGH);
        }

        //预减库存
        Integer stockCount = redisService.get(GoodsKey.stockCount, goodsId, Integer.class);
        if (stockCount < 0) {
            redisService.decr(GoodsKey.stockCount, goodsId);
            redisService.set(MiaoshaKey.goodsOver,goodsId,true);
            localOverMap.put(goodsId, true);
            return Result.error(CodeMsg.STOCK_NOT_ENUOUGH);
        }

        //判断有没有秒杀过商品
        MiaoshaOrder miaoshaOrder = orderService.getMiaoshaOrderByUserIdAndGoodsId(user.getId(),goodsId);
        if (miaoshaOrder != null) {
            return Result.error(CodeMsg.REPEAT_MIAOSHA);
        }

        //异步下单
        MiaoshaMessage miaoshaMessage = new MiaoshaMessage();
        miaoshaMessage.setGoodsId(goodsId);
        miaoshaMessage.setUser(user);
        mqSender.sendMiaoshaMessage(miaoshaMessage);
        return Result.success(0);
    }

    @GetMapping("/path")
    @ResponseBody
    @UserAccess(maxCount = 5,seconds = 5)
    public Result<String> path(@RequestParam("goodsId") String goodsId,
                               @RequestParam(value = "verifyCode",required = false, defaultValue = "0") int verifyCode,
                               MiaoshaUser user) {
        //验证验证码是否正确
        boolean ifRight = miaoshaService.configVerifyCode(user,goodsId,verifyCode);
        if (!ifRight) {
            return Result.error(CodeMsg.VERIFYCODE_NOT_RIGHT);
        }
        //生成path
        String path = miaoshaService.createMiaoshaRandomPath(user,goodsId);
        return Result.success(path);
    }

    @GetMapping("verifyCode")
    @ResponseBody
    public Result<String> verifyCode(@RequestParam("goodsId") String goodsId,
                                     MiaoshaUser user, HttpServletResponse response) {
        BufferedImage image = miaoshaService.createVerifyCode(user, goodsId);
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            ImageIO.write(image,"JPEG",outputStream);
            outputStream.flush();
            outputStream.close();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    @GetMapping("/result")
    @ResponseBody
    public Result<String> getMiaoshaResult(@RequestParam("goodsId") String goodsId,
                                                 MiaoshaUser user) {
        String result = miaoshaService.getMiaoshaResult(user.getId(), goodsId);
        return Result.success(result);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        //缓存秒杀库存信息
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        for (GoodsVo goodsVo : goodsList) {
            redisService.set(GoodsKey.stockCount, goodsVo.getId(), goodsVo.getStockCount());
            localOverMap.put(goodsVo.getId(), false);
        }
    }
}
