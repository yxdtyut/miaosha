package com.yxdtyut.miaosha.vo;

import com.yxdtyut.miaosha.entity.Goods;
import com.yxdtyut.miaosha.entity.MiaoshaUser;
import lombok.Data;

/**
 * @Author : yangxudong
 * @Description :   秒杀detail专用vo
 * @Date : 下午4:23 2018/6/1
 */
@Data
public class MiaoshaVo {
    private MiaoshaUser user;
    private GoodsVo goods;
    private Integer miaoshaStatus;
    private long remainSeconds;

}
