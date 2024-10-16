package com.tistory.jaimemin.mocknetflix.user;

import java.util.Optional;

public interface FetchUserPort {

	Optional<UserPortResponse> findByEmail(String email);
}