package com.meizu.base.service.impl;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meizu.base.dto.UserDto;
import com.meizu.base.service.MqService;
import com.meizu.base.util.RedisUtil;

import cn.hutool.core.util.IdUtil;

@Service
public class MqServiceImpl implements MqService {

	@Autowired
	private RocketMQTemplate rocketMQTemplate;
	
	@Autowired
	private RedisUtil redisUtil;

	@Override
	public void createBefore(UserDto user) {
		// 发送半事务消息
		rocketMQTemplate.sendMessageInTransaction("tx_topic", MessageBuilder.withPayload(user).setHeader("txId", IdUtil.simpleUUID()).build(), user);
	}

	@Transactional
	@Override
	public void create(UserDto user, String txId) {
		System.err.println("成功创建用户：" + user.toString());
		redisUtil.set(txId, user.toString());
	}

}
