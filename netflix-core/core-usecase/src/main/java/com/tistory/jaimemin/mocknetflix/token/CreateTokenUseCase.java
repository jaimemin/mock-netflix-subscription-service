package com.tistory.jaimemin.mocknetflix.token;

import com.tistory.jaimemin.mocknetflix.token.response.TokenResponse;

public interface CreateTokenUseCase {

	TokenResponse createNewToken(String userId);
}
