package com.meizu.base.controller;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.meizu.base.api.HelloClientApi;
import com.meizu.base.api.UserClientApi;
import com.meizu.base.constant.StatusCode;
import com.meizu.base.dto.ResultDto;
import com.meizu.base.dto.UserDto;
import com.meizu.base.exception.BussinessException;
import com.meizu.base.service.HelloService;
import com.meizu.base.service.UserService;
import com.meizu.base.util.LogUtil;
import com.meizu.base.util.ResponseUtil;

import io.seata.spring.annotation.GlobalTransactional;

@RestController
public class TestController {

	private static Logger log = LogUtil.get(TestController.class);

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public void test() throws InterruptedException {
		log.info("test base-web...");
//		Thread.sleep(60000);
	}

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Ribbon-负载均衡，调用注册中心的服务
	 * 
	 * @param str
	 * @return
	 */
	@RequestMapping(value = "/hello/{str}", method = RequestMethod.GET)
	public String hello(@PathVariable String str) {
		return restTemplate.getForObject("http://base-service/hello/" + str, String.class);
	}

//	@Autowired
//	private HelloClientApi client;
	
	@Autowired
	private HelloService helloService;

	/**
	 * Feign-轻量级RESTful，内置了Ribbon
	 * 
	 * @param str
	 * @return
	 */
	@RequestMapping(value = "/hello2/{str}", method = RequestMethod.GET)
	public String hello2(@PathVariable String str) {
//		return client.sayHello("2021");
		return helloService.sayHello("2021");
	}

//	@Autowired
//	private UserClientApi userClientApi;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ResultDto<List<UserDto>> getUsers() {
		long st = System.currentTimeMillis();
		List<UserDto> users = userService.getUsers();
		long ed = System.currentTimeMillis();
		System.err.println("users time:" + (ed - st) + "ms");
		return ResponseUtil.ok(users);
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ResultDto<Object> addUser() {
		UserDto user = new UserDto();
		user.setName("Tom");
		user.setSex(1);
		user.setSexText("男");
		UserDto dto = userService.addUser(user);
		return ResponseUtil.ok(dto);
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public ResultDto<Object> updateUser() {
		UserDto user = new UserDto();
		user.setId(6);
		user.setName("Susan");
		user.setSex(2);
		user.setSexText("女");
		UserDto dto = userService.updateUser(user);
		return ResponseUtil.ok(dto);
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public ResultDto<Object> deleteUser() {
		int count = userService.deleteUser(6);
		return ResponseUtil.ok(count);
	}

	@GlobalTransactional
	@RequestMapping(value = "/mergeOpt", method = RequestMethod.POST)
	public ResultDto<String> mergeOperation() {
		try {
			UserDto user = new UserDto();
			user.setName("Jack");
			user.setSex(1);
			user.setSexText("男");
			UserDto one = userService.addUser(user);

			UserDto user2 = new UserDto();
			user2.setId(1);
			user2.setName("Susan");
			user2.setSex(2);
			user2.setSexText("女");
			UserDto two = userService.updateUser(user);

		} catch (Exception e) {
			e.printStackTrace();
			throw new BussinessException(StatusCode.INTERNAL_ERROR);
		}
		return ResponseUtil.ok();
	}

	@Autowired
	private RocketMQTemplate rocketMQTemplate;
	
	@Value("${rocketmq.topic}")
	private String topic;
	
	@PostMapping("/producer")
	public void producer() {
		UserDto user = new UserDto();
		user.setName("Sarah");
		user.setSex(2);
		user.setSexText("女");
		rocketMQTemplate.convertAndSend(topic, user);
		//当发送的消息不重要时，采用one-way方式，以提高吞吐量；
		//当发送的消息很重要是，且对响应时间不敏感的时候采用sync方式;
		//当发送的消息很重要，且对响应时间非常敏感的时候采用async方式
	}

}
