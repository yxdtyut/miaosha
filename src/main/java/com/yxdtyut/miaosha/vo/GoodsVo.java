package com.yxdtyut.miaosha.vo;

import com.yxdtyut.miaosha.entity.Goods;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author : yangxudong
 * @Description :   商品vo
 * @Date : 下午1:47 2018/5/30
 */
@Data
public class GoodsVo extends Goods{
    /**秒杀价.*/
    private BigDecimal miaoshaPrice;

    /**秒杀商品库存.*/
    private Integer stockCount;

    /**秒杀开始时间.*/
    private Date startDate;

    /**秒杀结束时间.*/
    private Date endDate;
}
