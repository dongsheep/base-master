package com.meizu.base.mq;

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.meizu.base.dto.UserDto;
import com.meizu.base.service.MqService;
import com.meizu.base.util.RedisUtil;

/**
 * MQ本地事务监听器
 * 
 * @author xiedongxiao
 *
 */

@RocketMQTransactionListener
@Component
public class TransactionMessageListener implements RocketMQLocalTransactionListener {

	@Autowired
	private MqService mqService;
	
	@Autowired
	private RedisUtil redisUtil;
	
	/**
	 * 执行本地事务
	 */
	@Override
	public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
		try {
			String txId = (String) msg.getHeaders().get("txId");
			UserDto user = (UserDto) arg;
			mqService.create(user, txId);
			return RocketMQLocalTransactionState.COMMIT;
		} catch (Exception e) {
			e.printStackTrace();
			return RocketMQLocalTransactionState.ROLLBACK;
		}
	}

	/**
	 * 消息回查
	 */
	@Override
	public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
		String txId = (String) msg.getHeaders().get("txId");
		if (redisUtil.get(txId) != null) { 
			// 本地事务成功
			return RocketMQLocalTransactionState.COMMIT;
		} else {
			return RocketMQLocalTransactionState.ROLLBACK;
		}
	}

}
