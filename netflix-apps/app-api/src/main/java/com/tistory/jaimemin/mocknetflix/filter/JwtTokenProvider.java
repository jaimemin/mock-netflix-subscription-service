package com.tistory.jaimemin.mocknetflix.filter;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.tistory.jaimemin.mocknetflix.token.FetchTokenUseCase;
import com.tistory.jaimemin.mocknetflix.user.response.UserResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

	private final FetchTokenUseCase fetchTokenUseCase;

	public Authentication getAuthentication(String accessToken) {
		UserResponse user = fetchTokenUseCase.findUserByAccessToken(accessToken);
		List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole()));
		UserDetails principal = new User(user.getUsername(),
			StringUtils.isBlank(user.getPassword()) ? "password" : user.getPassword(), authorities);

		return new UsernamePasswordAuthenticationToken(principal, user.getUserId(), authorities);
	}

	public String getuserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		return (String)authentication.getCredentials();
	}

	public String getRole() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		return authentication.getAuthorities().stream().findAny().orElseThrow().getAuthority();
	}
}
