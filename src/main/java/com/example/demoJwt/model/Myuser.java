package com.example.demoJwt.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class Myuser implements UserDetails{
	
	private Long id;
	private String fullName;
	private String email;
	private String userName;
	private String password;
	private Collection<? extends GrantedAuthority> roles;
	
	public Myuser() {
		super();
	}
	
	public Myuser(Long id, String fullName, String email, String password, String userName, Collection<? extends GrantedAuthority> roles) {
		super();
		this.setId(id);
		this.setFullName(fullName);
		this.setEmail(email);
		this.password = password;
		this.userName = userName;
		this.roles = roles;
	}


	public static Myuser build(UserEntity userEntity) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		// dua role tu user ve role cua he thong
		for(RoleEntity role : userEntity.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
 		return new Myuser(
 					userEntity.getId(),
 					userEntity.getFullName(),
 					userEntity.getEmail(),
 					userEntity.getPassWord(),
 					userEntity.getUserName(),
 					authorities
 				);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
