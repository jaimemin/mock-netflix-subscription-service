package com.tistory.jaimemin.mocknetflix.movie;

import org.springframework.stereotype.Component;

@Component
public class GoleRoleValidator implements UserDownloadMovieRoleValidator {

	@Override
	public boolean validate(long count) {
		return true;
	}

	@Override
	public boolean isTarget(String role) {
		return role.equals("ROLE_GOLD");
	}
}
