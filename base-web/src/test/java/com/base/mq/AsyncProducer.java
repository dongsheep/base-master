package com.base.mq;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class AsyncProducer {
	
	public static void main(String[] args) throws Exception {
		// Instantiate with a producer group name.
		DefaultMQProducer producer = new DefaultMQProducer("my_mq_test_producer_group");
		// Specify name server addresses.
		producer.setNamesrvAddr("http://172.16.180.93:9876");
		// Launch the instance.
		producer.start();
		producer.setRetryTimesWhenSendAsyncFailed(0);

		int messageCount = 10;
		final CountDownLatch countDownLatch = new CountDownLatch(messageCount);
		for (int i = 0; i < messageCount; i++) {
			try {
				final int index = i;
				Message msg = new Message("test_topic", "TagA", "OrderID188", "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
				producer.send(msg, new SendCallback() {
					@Override
					public void onSuccess(SendResult sendResult) {
						countDownLatch.countDown();
						System.out.printf("%-10d OK %s %n", index, sendResult.getMsgId());
					}

					@Override
					public void onException(Throwable e) {
						countDownLatch.countDown();
						System.out.printf("%-10d Exception %s %n", index, e);
						e.printStackTrace();
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		countDownLatch.await(5, TimeUnit.SECONDS);
		producer.shutdown();
	}
	
}
