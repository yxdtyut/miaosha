package com.yxdtyut.miaosha.service.impl;

import com.yxdtyut.miaosha.entity.MiaoshaOrder;
import com.yxdtyut.miaosha.entity.MiaoshaUser;
import com.yxdtyut.miaosha.entity.OrderInfo;
import com.yxdtyut.miaosha.key.MiaoshaKey;
import com.yxdtyut.miaosha.key.OrderKey;
import com.yxdtyut.miaosha.mapper.OrderMapper;
import com.yxdtyut.miaosha.service.GoodsService;
import com.yxdtyut.miaosha.service.OrderService;
import com.yxdtyut.miaosha.service.RedisService;
import com.yxdtyut.miaosha.util.SnowflakeIdWorker;
import com.yxdtyut.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午2:01 2018/5/31
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private RedisService redisService;


    @Override
    public MiaoshaOrder getMiaoshaOrderByUserIdAndGoodsId(String userId, String goodsId) {
        return redisService.get(OrderKey.miaosha,userId + "_" + goodsId, MiaoshaOrder.class);
    }

    @Override
    @Transactional
    public boolean doMiaosha(MiaoshaUser user, GoodsVo goodsVo) {
        //减库存
        int reduceCount = goodsService.reduceMiaoshaGoodsStock(goodsVo.getId());
        if (reduceCount < 0) {
            redisService.set(MiaoshaKey.goodsOver,goodsVo.getId(),true);
        }
        //添加订单表
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setId(SnowflakeIdWorker.primaryKey());
        orderInfo.setUserId(user.getId());
        orderInfo.setGoodsId(goodsVo.getId());
        orderInfo.setGoodsName(goodsVo.getGoodsName());
        orderInfo.setGoodsPrice(goodsVo.getMiaoshaPrice());
        orderInfo.setCreateDate(new Date());
        orderInfo.setStatus(0);
        orderMapper.saveOrderInfo(orderInfo);
        //添加秒杀订单表
        MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
        miaoshaOrder.setId(SnowflakeIdWorker.primaryKey());
        miaoshaOrder.setGoodsId(goodsVo.getId());
        miaoshaOrder.setUserId(user.getId());
        miaoshaOrder.setOrderId(orderInfo.getId());
        orderMapper.saveMiaoshaOrder(miaoshaOrder);

        //将秒杀订单数据写入redis
        redisService.set(OrderKey.miaosha,user.getId() + "_" + goodsVo.getId(),miaoshaOrder);
        return true;
    }

    @Override
    public OrderInfo getOrderById(String orderId) {
        return orderMapper.getOrderById(orderId);
    }
}
