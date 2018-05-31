package com.yxdtyut.miaosha.entity;

import lombok.Data;

/**
 * @Author : yangxudong
 * @Description :   秒杀订单的pojo
 * @Date : 上午10:38 2018/5/29
 */
@Data
public class MiaoshaOrder {
    /**秒杀订单的主键.*/
    private String id;

    /**用户id.*/
    private String userId;

    /**订单id.*/
    private String orderId;

    /**商品id.*/
    private String goodsId;
}
