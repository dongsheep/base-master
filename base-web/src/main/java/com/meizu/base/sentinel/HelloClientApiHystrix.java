package com.meizu.base.sentinel;

import org.springframework.stereotype.Component;

import com.meizu.base.service.HelloService;

/**
 * Feign+Hystrix 熔断处理类
 * 
 * @author xiedongxiao
 *
 */

@Component
public class HelloClientApiHystrix implements HelloService {

	@Override
	public String sayHello(String str) {
		return "Say Hello Method Fallback...";
	}

}
