package com.meizu.base.service.impl;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meizu.base.constant.StatusCode;
import com.meizu.base.domain.UserEntity;
import com.meizu.base.dto.UserDto;
import com.meizu.base.exception.BussinessException;
import com.meizu.base.mapper.UserMapper;
import com.meizu.base.service.UserService;
import com.meizu.base.util.LogUtil;
import com.meizu.base.util.RedisUtil;

import cn.hutool.core.date.DateUtil;

@Service
public class UserServiceImpl implements UserService {
	
	private static Logger log = LogUtil.get(UserServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Value("${server.port}")
	private String port;

	@Override
	public List<UserDto> getUsers() {
		log.info("running getUsers..." + port);
		List<UserDto> lst = userMapper.getUsers();
		return lst;
	}

	@Transactional
	@Override
	public UserDto addUser(UserDto dto) {
		UserEntity user = new UserEntity();
		BeanUtils.copyProperties(dto, user);
		user.setUpdateTime(DateUtil.date());
		int count = userMapper.insert(user);
		if (count == 1) {
			return userMapper.findUserById(user.getId());
		} else {
			log.warn("addUser success zero...");
			throw new BussinessException(StatusCode.INTERNAL_ERROR);
		}
	}

	@Transactional
	@Override
	public UserDto updateUser(UserDto dto) {
		UserEntity user = new UserEntity();
		BeanUtils.copyProperties(dto, user);
		user.setUpdateTime(DateUtil.date());
		int count = userMapper.updateByPrimaryKeySelective(user);
		double x = 1 / 0;
		if (count == 1) {
			return userMapper.findUserById(user.getId());
		} else {
			log.warn("updUser success zero...");
			throw new BussinessException(StatusCode.INTERNAL_ERROR);
		}
	}

	@Transactional
	@Override
	public int deleteUser(Integer id) {
		int count = userMapper.deleteByPrimaryKey(id);
		return count;
	}
	
}
