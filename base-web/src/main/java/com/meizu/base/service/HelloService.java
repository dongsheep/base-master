package com.meizu.base.service;

import org.springframework.cloud.openfeign.FeignClient;

import com.meizu.base.api.HelloClientApi;
import com.meizu.base.sentinel.HelloClientFallbackFactory;

@FeignClient(name = "base-service", fallbackFactory = HelloClientFallbackFactory.class)
public interface HelloService extends HelloClientApi {

}
