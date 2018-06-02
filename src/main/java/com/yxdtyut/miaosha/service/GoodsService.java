package com.yxdtyut.miaosha.service;

import com.yxdtyut.miaosha.vo.GoodsVo;

import java.util.List;

/**
 * @Author : yangxudong
 * @Description :   商品service
 * @Date : 下午2:34 2018/5/30
 */

public interface GoodsService {
    /**获取商品vo集合.*/
    List<GoodsVo> listGoodsVo();

    /**根据商品id获取商品vo对象.*/
    GoodsVo getGoodsVoByGoodsId(String goodsId);

    /**减商品库存.*/
    Integer reduceMiaoshaGoodsStock(String id);
}
