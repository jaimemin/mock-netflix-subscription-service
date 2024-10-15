package com.tistory.jaimemin.mocknetflix.user;

import com.tistory.jaimemin.mocknetflix.user.response.UserResponse;

public interface FetchUserUseCase {

	UserResponse findUserByEmail(String email);
}
