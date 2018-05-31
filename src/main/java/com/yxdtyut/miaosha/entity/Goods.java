package com.yxdtyut.miaosha.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author : yangxudong
 * @Description :   商品pojo
 * @Date : 上午10:30 2018/5/29
 */
@Data
public class Goods {
    /**商品id.*/
    private String id;

    /**商品名称.*/
    private String goodsName;

    /**商品标题.*/
    private String goodsTitle;

    /**商品图片.*/
    private String goodsImg;

    /**商品详情介绍.*/
    private String goodsDetail;

    /**商品价格.*/
    private BigDecimal goodsPrice;

    /**商品库存 -1表示没有限制.*/
    private Integer goodsStock;
}
