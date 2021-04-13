package com.base.mq;

import java.io.UnsupportedEncodingException;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class SyncProducer {

	public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, MQBrokerException, InterruptedException {
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
			SendResult sendResult = producer.send(msg);
			System.out.printf("%s%n", sendResult);
		}
		// Shut down once the producer instance is not longer in use.
		producer.shutdown();
	}

}
