package com.tistory.jaimemin.mocknetflix.security;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tistory.jaimemin.mocknetflix.user.FetchUserUseCase;
import com.tistory.jaimemin.mocknetflix.user.response.UserResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NetflixUserDetailsService implements UserDetailsService {

	private final FetchUserUseCase fetchUserUseCase;

	@Override
	public NetflixAuthUser loadUserByUsername(String email) throws UsernameNotFoundException {
		UserResponse userByEmail = fetchUserUseCase.findUserByEmail(email);

		return new NetflixAuthUser(
			userByEmail.getUserId(),
			userByEmail.getUsername(),
			userByEmail.getPassword(),
			userByEmail.getEmail(),
			userByEmail.getPhone(),
			List.of(new SimpleGrantedAuthority(userByEmail.getRole()))
		);
	}
}
