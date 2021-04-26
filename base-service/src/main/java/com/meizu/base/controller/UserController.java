package com.meizu.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meizu.base.dto.UserDto;
import com.meizu.base.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping(value = "/users")
	public List<UserDto> getUsers() {
		List<UserDto> lst = userService.getUsers();
		return lst;
	}

	@PostMapping(value = "/insert")
	public UserDto addUser(UserDto user) {
		UserDto rec = userService.addUser(user);
		return rec;
	}

	@PostMapping(value = "/update")
	public UserDto updateUser(UserDto user) {
		UserDto rec = userService.updateUser(user);
		return rec;
	}

	@PostMapping(value = "/delete")
	public int deleteUser(Integer id) {
		int count = userService.deleteUser(id);
		return count;
	}
	
}
