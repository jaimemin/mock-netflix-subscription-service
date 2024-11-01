package com.tistory.jaimemin.mocknetflix.token;

public interface InsertTokenPort {

	TokenPortResponse create(String userId, String accessToken, String refreshToken);
}
