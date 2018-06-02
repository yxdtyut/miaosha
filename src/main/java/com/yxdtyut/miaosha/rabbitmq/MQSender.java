package com.yxdtyut.miaosha.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class MQSender {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void sendMiaoshaMessage(MiaoshaMessage mm) {
		log.info("send message:"+mm.toString());
		rabbitTemplate.convertAndSend(MQConfig.MIAOSHA_QUEUE, mm);
	}

}
