package com.meizu.base.service;

import com.meizu.base.dto.UserDto;

public interface MqService {
	
	void createBefore(UserDto user);
	
	void create(UserDto user, String txId);

}
