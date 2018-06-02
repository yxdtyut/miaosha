package com.yxdtyut.miaosha.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.yxdtyut.miaosha.entity.MiaoshaOrder;
import com.yxdtyut.miaosha.entity.MiaoshaUser;
import com.yxdtyut.miaosha.service.GoodsService;
import com.yxdtyut.miaosha.service.OrderService;
import com.yxdtyut.miaosha.service.RedisService;
import com.yxdtyut.miaosha.vo.GoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class MQReceiver {


		@Autowired
		RedisService redisService;
		
		@Autowired
		GoodsService goodsService;
		
		@Autowired
		OrderService orderService;
		
		@RabbitListener(queues=MQConfig.MIAOSHA_QUEUE)
		public void receive(MiaoshaMessage mm) {
			log.info("receive message:"+mm.toString());
			MiaoshaUser user = mm.getUser();
			String goodsId = mm.getGoodsId();

			GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
	    	int stock = goods.getStockCount();
	    	if(stock <= 0) {
	    		return;
	    	}
	    	//判断是否已经秒杀到了
	    	MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdAndGoodsId(user.getId(), goodsId);
	    	if(order != null) {
	    		return;
	    	}
	    	//减库存 下订单 写入秒杀订单
			boolean miaoshaFlag = orderService.doMiaosha(user, goods);

		}

}
