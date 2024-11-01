package com.tistory.jaimemin.mocknetflix.token;

import com.tistory.jaimemin.mocknetflix.user.response.UserResponse;

public interface FetchTokenUseCase {

	Boolean validateToken(String accessToken);

	String getTokenFromKakao(String code);

	UserResponse findUserByAccessToken(String accessToken);
}
