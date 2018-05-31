package com.yxdtyut.miaosha.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author : yangxudong
 * @Description :   订单pojo
 * @Date : 上午10:50 2018/5/29
 */
@Data
public class OrderInfo {
    /**订单id.*/
    private String id;

    /**用户id.*/
    private String userId;

    /**商品id.*/
    private String goodsId;

    /**收货地址id.*/
    private String deliveryAddrId;

    /**冗余过来的商品名称.*/
    private String goodsName;

    /**商品数量.*/
    private Integer goodsCount;

    /**商品单价.*/
    private BigDecimal goodsPrice;

    /**1pc,2android,3ios.*/
    private Integer orderChannel;

    /**订单状态: 0新建未支付，1已支付，2已发货，3已收获，4已退款，5已完成.*/
    private Integer status;

    /**订单创建时间.*/
    private Date createDate;

    /**支付时间.*/
    private Date payDate;
}
