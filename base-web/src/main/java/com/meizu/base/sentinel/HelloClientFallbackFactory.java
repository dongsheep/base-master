package com.meizu.base.sentinel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import com.meizu.base.constant.ComConstant;
import com.meizu.base.service.HelloService;

/**
 * Feign+Sentinel 限流降级类-工厂
 * 
 * @author xiedongxiao
 *
 */

@Component
public class HelloClientFallbackFactory implements FallbackFactory<HelloService> {
	
	private static Logger log = LogManager.getLogger(HelloClientFallbackFactory.class);

	@Override
	public HelloService create(Throwable cause) {
		return new HelloService() {
			@Override
			public String sayHello(String str) {
				log.error("远程调用被限流或降级了，throwable={}", cause);
				return ComConstant.DEFAULT_NAME;
			}
		};
	}

}
