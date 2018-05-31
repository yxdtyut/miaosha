package com.yxdtyut.miaosha.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author : yangxudong
 * @Description :   秒杀商品的pojo
 * @Date : 上午10:34 2018/5/29
 */
@Data
public class MiaoShaGoods {
    /**秒杀商品的主键.*/
    private String id;

    /**商品id.*/
    private String goodsId;

    /**秒杀价.*/
    private BigDecimal miaoshaPrice;

    /**秒杀商品库存.*/
    private Integer stockCount;

    /**秒杀开始时间.*/
    private Date startDate;

    /**秒杀结束时间.*/
    private Date endDate;

}
