package com.example.demoJwt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demoJwt.model.Myuser;
import com.example.demoJwt.model.UserEntity;
import com.example.demoJwt.repository.UserRepository;

@Service
public class UserDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Name not found" + username));
		return Myuser.build(user);
	}

}
