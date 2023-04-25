package com.example.demoJwt.model.converter;

import com.example.demoJwt.model.UserEntity;
import com.example.demoJwt.request.UserDto;

public class UserConverter {

	public static UserEntity toEntity(UserDto user) {
		UserEntity entity = new UserEntity();
		entity.setUserName(user.getUserName());
		entity.setEmail(user.getEmail());
		entity.setFullName(user.getFullname());
		
		return entity;
	}
}
