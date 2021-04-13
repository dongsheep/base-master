package com.meizu.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.meizu.base.api.UserClientApi;
import com.meizu.base.dto.UserDto;
import com.meizu.base.service.UserService;

@RestController
public class UserController implements UserClientApi  {
	
	@Autowired
	private UserService userService;

	@Override
	public List<UserDto> getUsers() {
		List<UserDto> lst = userService.getUsers();
		return lst;
	}

	@Override
	public UserDto addUser(UserDto user) {
		UserDto rec = userService.addUser(user);
		return rec;
	}

	@Override
	public UserDto updateUser(UserDto user) {
		UserDto rec = userService.updateUser(user);
		return rec;
	}

	@Override
	public int deleteUser(Integer id) {
		int count = userService.deleteUser(id);
		return count;
	}
	
}
