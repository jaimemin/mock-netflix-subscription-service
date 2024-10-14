package com.tistory.jaimemin.mocknetflix.authentication;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class AuthenticationHolderImpl implements AuthenticationHolder, RequestedByProvider {

	private Authentication authentication;

	@Override
	public Optional<Authentication> getAuthentication() {
		return Optional.of(authentication);
	}

	@Override
	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}

	@Override
	public Optional<String> getRequestedBy() {
		return getAuthentication()
			.map(Authentication::getRequestedBy);
	}
}
