package com.example.demoJwt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demoJwt.model.Myuser;
import com.example.demoJwt.model.RoleEntity;
import com.example.demoJwt.model.UserEntity;
import com.example.demoJwt.model.converter.UserConverter;
import com.example.demoJwt.repository.RoleRepository;
import com.example.demoJwt.repository.UserRepository;
import com.example.demoJwt.request.UserDto;
import com.example.demoJwt.response.LoginResponse;
import com.example.demoJwt.security.JwtProvider;
import com.example.demoJwt.service.IUserService;

@Service
public class UserService implements IUserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository; 
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public String createUser(UserDto user) {
		if(userRepository.existsByUserName(user.getUserName())) {
			return "The user name existed! Please try again";
		}
		if(userRepository.existsByEmail(user.getEmail())) {
			return "The email existed! Please try again";
		}
		List<RoleEntity> roles = new ArrayList<>();
		UserEntity userEntity = UserConverter.toEntity(user);
		RoleEntity role = roleRepository.findByName("USER")
				.orElseThrow(() -> null);
		roles.add(role);
		userEntity.setRoles(roles);
		userEntity.setPassWord(passwordEncoder.encode(user.getPassword()));
		
		userRepository.save(userEntity);
		return "Create user success";
	}

	@Override
	public LoginResponse login(UserDto user) {
		Authentication authentication = authenticationManager.authenticate((
				new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword())
		));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtProvider.createToken(authentication);
		Myuser myUser = (Myuser) authentication.getPrincipal();
		LoginResponse response = new LoginResponse(myUser.getId(), token, myUser.getFullName(), myUser.getAuthorities());
		return response;
	}

}
