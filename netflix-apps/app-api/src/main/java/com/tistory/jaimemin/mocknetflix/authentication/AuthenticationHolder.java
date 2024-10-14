package com.tistory.jaimemin.mocknetflix.authentication;

import java.util.Optional;

public interface AuthenticationHolder {

	Optional<Authentication> getAuthentication();

	void setAuthentication(Authentication authentication);
}
