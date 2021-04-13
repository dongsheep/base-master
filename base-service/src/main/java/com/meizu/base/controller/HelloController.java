package com.meizu.base.controller;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meizu.base.api.HelloClientApi;
import com.meizu.base.util.LogUtil;

@RefreshScope
@RestController
public class HelloController implements HelloClientApi {

//	@RequestMapping(value = "/hello/{str}", method = RequestMethod.GET)
//	public String echo(@PathVariable String str) {
//		return "Hello Nacos Discovery " + str;
//	}
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Value("${myflag:false}")
	private Boolean myflag;
	
	@RequestMapping(value = "/getServiceList")
	public List<ServiceInstance> getServiceList() {
		List<ServiceInstance> instances = discoveryClient.getInstances("base-service");
		System.err.println("myflag:" + myflag);
		return instances;
	}

	@Override
	public String sayHello(String str) {
//		try {
//			Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		return "Hello Nacos Discovery " + str;
	}
	
	private static Logger log = LogUtil.get(HelloController.class);
	
	@PostMapping("/test")
	public void test() {
		log.info("test base-service...");
	}
	
}
