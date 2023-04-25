package com.example.demoJwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoJwt.request.UserDto;
import com.example.demoJwt.response.LoginResponse;
import com.example.demoJwt.response.ResponseMessage;
import com.example.demoJwt.service.IUserService;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class HomeController {
	
	@Autowired
	private IUserService userService;
	
	@PostMapping("/signup")
	public ResponseEntity<?> createUser(@RequestBody UserDto user) {
		String message = userService.createUser(user);
		return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserDto user) {
		LoginResponse res = userService.login(user);
		return ResponseEntity.ok(res);
	}
}
