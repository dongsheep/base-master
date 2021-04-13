package com.base.mq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class OnewayProducer {

	public static void main(String[] args) throws Exception {
		// Instantiate with a producer group name.
		DefaultMQProducer producer = new DefaultMQProducer("my_mq_test_producer_group");
		// Specify name server addresses.
		producer.setNamesrvAddr("http://172.16.180.93:9876");
		// Launch the instance.
		producer.start();
		for (int i = 0; i < 10; i++) {
			// Create a message instance, specifying topic, tag and message body.
			Message msg = new Message("test_topic" /* Topic */, "TagA" /* Tag */, ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
			);
			// Call send message to deliver message to one of brokers.
			producer.sendOneway(msg);
		}
		// Wait for sending to complete
		Thread.sleep(5000);
		producer.shutdown();
	}

}
