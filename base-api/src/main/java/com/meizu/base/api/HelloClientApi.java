package com.meizu.base.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.meizu.base.api.sentinel.HelloClientFallbackFactory;

@FeignClient(name = "base-service", fallbackFactory = HelloClientFallbackFactory.class)
//@FeignClient(name = "base-service", fallback = HelloClientApiHystrix.class, contextId = "HelloClient") //声明调用的服务名称+熔断处理类
//@FeignClient(name = "base-service", value = "base-service", fallback = HelloClientApiHystrix.class, contextId = "HelloClient") //声明调用的服务名称+熔断处理类
public interface HelloClientApi {

	@RequestMapping(value = "/hello/{str}", method = RequestMethod.GET)
	String sayHello(@PathVariable String str);
	
}
