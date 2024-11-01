package com.tistory.jaimemin.mocknetflix.entity.token;

import java.time.LocalDateTime;
import java.util.UUID;

import com.tistory.jaimemin.mocknetflix.audit.MutableBaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Entity
@Table(name = "tokens")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenEntity extends MutableBaseEntity {

	@Id
	@Column(name = "TOKEN_ID")
	private String tokenId;

	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "ACCESS_TOKEN")
	private String accessToken;

	@Column(name = "REFRESH_TOKEN")
	private String refreshToken;

	@Column(name = "ACCESS_TOKEN_EXPIRES_AT")
	private LocalDateTime accessTokenExpiresAt;

	@Column(name = "REFRESH_TOKEN_EXPIRES_AT")
	private LocalDateTime refreshTokenExpiresAt;

	public TokenEntity(String userId, String accessToken, String refreshToken, LocalDateTime accessTokenExpiresAt,
		LocalDateTime refreshTokenExpiresAt) {
		this.tokenId = UUID.randomUUID().toString();
		this.userId = userId;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.accessTokenExpiresAt = accessTokenExpiresAt;
		this.refreshTokenExpiresAt = refreshTokenExpiresAt;
	}

	public static TokenEntity newTokenEntity(String userId, String accessToken, String refreshToken) {
		LocalDateTime now = LocalDateTime.now();

		return new TokenEntity(
			userId, accessToken, refreshToken, getAccessTokenExpiresAt(now), getRefreshTokenExpiresAt(now)
		);
	}

	public void updateToken(String accessToken, String refreshToken) {
		LocalDateTime now = LocalDateTime.now();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.accessTokenExpiresAt = getAccessTokenExpiresAt(now);
		this.refreshTokenExpiresAt = getRefreshTokenExpiresAt(now);
	}

	private static LocalDateTime getAccessTokenExpiresAt(LocalDateTime now) {
		return now.plusMinutes(3);
	}

	private static LocalDateTime getRefreshTokenExpiresAt(LocalDateTime now) {
		return now.plusMinutes(24);
	}
}
