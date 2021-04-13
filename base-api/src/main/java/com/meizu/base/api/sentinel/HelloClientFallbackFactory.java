package com.meizu.base.api.sentinel;

import org.apache.logging.log4j.Logger;
//import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import com.meizu.base.api.HelloClientApi;
import com.meizu.base.constant.ComConstant;
import com.meizu.base.util.LogUtil;

/**
 * Feign+Sentinel 限流降级类
 * 
 * @author xiedongxiao
 *
 */

//@Component
//public class HelloClientFallbackFactory implements FallbackFactory<HelloClientApi> {
//	
//	private static Logger log = LogUtil.get(HelloClientFallbackFactory.class);
//
//	@Override
//	public HelloClientApi create(Throwable cause) {
//		return new HelloClientApi() {
//			@Override
//			public String sayHello(String str) {
//				log.error("远程调用被限流或降级了，throwable={}", cause);
//				return ComConstant.DEFAULT_NAME;
//			}
//		};
//	}
//
//}
