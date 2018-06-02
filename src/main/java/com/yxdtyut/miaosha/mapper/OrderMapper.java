package com.yxdtyut.miaosha.mapper;

import com.yxdtyut.miaosha.entity.MiaoshaOrder;
import com.yxdtyut.miaosha.entity.OrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午2:03 2018/5/31
 */
@Mapper
public interface OrderMapper {
    int getMiaoshaOrderCountByUserIdAndGoodsId(@Param("userId") String userId, @Param("goodsId") String goodsId);

    void saveOrderInfo(OrderInfo orderInfo);

    void saveMiaoshaOrder(MiaoshaOrder miaoshaOrder);

    OrderInfo getOrderById(@Param("id") String orderId);
}
