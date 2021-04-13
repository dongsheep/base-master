package com.meizu.base.api;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.meizu.base.dto.UserDto;

/**
 * 服务接口
 * 
 * @author xiedongxiao
 *
 */

@FeignClient(name = "base-service")
public interface UserClientApi {
	
	@PostMapping(value = "/users")
	List<UserDto> getUsers();
	
	@PostMapping(value = "/insert")
	UserDto addUser(@RequestBody UserDto user);
	
	@PostMapping(value = "/update")
	UserDto updateUser(@RequestBody UserDto user);
	
	@PostMapping(value = "/delete")
	int deleteUser(@RequestParam Integer id);

}
