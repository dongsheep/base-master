package com.meizu.base.mq;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import com.meizu.base.dto.UserDto;

@RocketMQMessageListener(
		consumerGroup = "test_consumer", // 消费者组名 
		topic = "TopicTest", // 消费主题
		consumeMode = ConsumeMode.CONCURRENTLY, // 消费模式，同步（默认），顺序
		messageModel = MessageModel.CLUSTERING // 消息模式，集群（默认），广播
)
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
