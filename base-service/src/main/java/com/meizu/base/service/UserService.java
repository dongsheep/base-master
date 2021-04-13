package com.meizu.base.service;

import java.util.List;

import com.meizu.base.dto.UserDto;

public interface UserService {
	
	List<UserDto> getUsers();
	
	UserDto addUser(UserDto dto);

	UserDto updateUser(UserDto dto);
	
	int deleteUser(Integer id);
	
}
