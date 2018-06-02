package com.yxdtyut.miaosha.service;

import com.yxdtyut.miaosha.entity.MiaoshaOrder;
import com.yxdtyut.miaosha.entity.MiaoshaUser;
import com.yxdtyut.miaosha.entity.OrderInfo;
import com.yxdtyut.miaosha.vo.GoodsVo; /**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午2:01 2018/5/31
 */

public interface OrderService {
    /**根据用户id和商品id获取是否秒杀过商品.*/
    MiaoshaOrder getMiaoshaOrderByUserIdAndGoodsId(String id, String goodsId);

    /**秒杀商品.*/
    boolean doMiaosha(MiaoshaUser user, GoodsVo goodsVo);

    /**根据主键获取orderInfo信息。*/
    OrderInfo getOrderById(String orderId);
}
