package com.tistory.jaimemin.mocknetflix.token.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenResponse {

	private final String accessToken;

	private final String refreshToken;
}
