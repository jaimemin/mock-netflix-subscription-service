package com.tistory.jaimemin.mocknetflix.token;

public interface UpdateTokenPort {

	void updateToken(String userId, String accessToken, String refreshToken);
}
