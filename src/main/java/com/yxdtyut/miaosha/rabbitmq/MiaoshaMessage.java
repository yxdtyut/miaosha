package com.yxdtyut.miaosha.rabbitmq;


import com.yxdtyut.miaosha.entity.MiaoshaUser;
import lombok.Data;

@Data
public class MiaoshaMessage {
	private MiaoshaUser user;
	private String goodsId;
}
