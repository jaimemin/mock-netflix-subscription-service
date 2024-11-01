package com.tistory.jaimemin.mocknetflix.token;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NetflixToken {

	private final String accessToken;

	private final String refreshToken;

	private final LocalDateTime accessTokenExpireAt;

	private final LocalDateTime refreshTokenExpireAt;

	public NetflixToken(String accessToken, String refreshToken, LocalDateTime accessTokenExpireAt,
		LocalDateTime refreshTokenExpireAt) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.accessTokenExpireAt = accessTokenExpireAt;
		this.refreshTokenExpireAt = refreshTokenExpireAt;
	}
}
