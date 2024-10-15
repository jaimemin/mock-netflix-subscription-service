package com.tistory.jaimemin.mocknetflix.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class NetflixAuthUser extends User {

	private final String userId;

	private final String username;

	private final String password;

	private final String email;

	private final String phoneNumber;

	public NetflixAuthUser(String userId, String username, String password, String email, String phoneNumber,
		Collection<? extends GrantedAuthority> authorities) {
		super(email, password, authorities);
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
}
