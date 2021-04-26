package com.meizu.base.mq;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import com.meizu.base.dto.UserDto;

@RocketMQMessageListener(consumerGroup = "my_test_consumer_group", topic = "my_topic")
@Component
public class TestListener implements RocketMQListener<UserDto> {

	private static Logger log = LogManager.getLogger(TestListener.class);

	@Override
	public void onMessage(UserDto message) {
		log.info("消费消息: {}", message.toString());
		System.err.println("执行...");
//		try {
//			TimeUnit.SECONDS.sleep(2);
//		} catch (InterruptedException e) {
//			log.error("", e);
//		}
	}

}