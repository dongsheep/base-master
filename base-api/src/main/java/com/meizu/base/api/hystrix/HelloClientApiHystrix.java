package com.meizu.base.api.hystrix;

import org.springframework.stereotype.Component;

import com.meizu.base.api.HelloClientApi;

/**
 * Feign+Hystrix 熔断处理类
 * 
 * @author xiedongxiao
 *
 */

@Component
public class HelloClientApiHystrix implements HelloClientApi {

	@Override
	public String sayHello(String str) {
		return "Say Hello Method Fallback...";
	}

}
