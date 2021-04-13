package com.meizu.base.service;

import org.springframework.cloud.openfeign.FeignClient;

import com.meizu.base.api.UserClientApi;

@FeignClient(name = "base-service")
public interface UserService extends UserClientApi {
	
}
