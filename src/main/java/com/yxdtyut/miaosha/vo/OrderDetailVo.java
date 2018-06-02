package com.yxdtyut.miaosha.vo;

import com.yxdtyut.miaosha.entity.OrderInfo;
import lombok.Data;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午6:07 2018/6/1
 */
@Data
public class OrderDetailVo {
    private OrderInfo order;
    private GoodsVo goods;
}
