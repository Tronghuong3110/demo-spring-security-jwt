package com.example.demoJwt.service;

import com.example.demoJwt.request.UserDto;
import com.example.demoJwt.response.LoginResponse;

public interface IUserService {
	String createUser(UserDto user);
	LoginResponse login(UserDto user);
}
