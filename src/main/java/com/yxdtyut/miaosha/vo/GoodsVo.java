package com.yxdtyut.miaosha.vo;

import com.yxdtyut.miaosha.entity.Goods;
import lombok.Data;

import java.math.BigDecimal;

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
}
