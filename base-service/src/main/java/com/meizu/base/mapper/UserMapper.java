package com.meizu.base.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.meizu.base.domain.UserEntity;
import com.meizu.base.dto.UserDto;

@Component
public interface UserMapper extends BaseMapper<UserEntity> {

	List<UserDto> getUsers();
	
	UserDto findUserById(Integer id);
    
}