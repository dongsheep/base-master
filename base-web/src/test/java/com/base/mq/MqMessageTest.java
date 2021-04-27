package com.base.mq;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.meizu.base.BaseWebApplication;

@SpringBootTest(classes = BaseWebApplication.class)
public class MqMessageTest {

	@Autowired
	private RocketMQTemplate rocketMQTemplate;
	
	@Test
	public void sendOrderMessage() {
		String body = "hello send msg...";
		for (int i = 0; i < 5; i++) {
			rocketMQTemplate.syncSendOrderly("TopicTest", body + i, "xxx");
			System.out.println("calllback_" + i);
		}
	}	
	
}
