package com.meizu.base;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan("com.meizu.base.mapper")
@EnableDiscoveryClient // 开启服务注册发现功能
@SpringBootApplication
public class BaseServiceApplication {

	public static void main(String[] args) {
		// 开启log4j2全异步，减小输出日志对性能的影响
		System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
		SpringApplication.run(BaseServiceApplication.class, args);
	}

}
