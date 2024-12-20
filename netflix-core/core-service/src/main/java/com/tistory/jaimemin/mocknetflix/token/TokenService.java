package com.tistory.jaimemin.mocknetflix.token;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.tistory.jaimemin.mocknetflix.token.response.TokenResponse;
import com.tistory.jaimemin.mocknetflix.user.FetchUserUseCase;
import com.tistory.jaimemin.mocknetflix.user.response.UserResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenService implements FetchTokenUseCase, CreateTokenUseCase, UpdateTokenUseCase {

	private final KakaoTokenPort kakaoTokenPort;

	private final InsertTokenPort insertTokenPort;

	private final UpdateTokenPort updateTokenPort;

	private final SearchTokenPort searchTokenPort;

	private final FetchUserUseCase fetchUserUseCase;

	@Value("${spring.jwt.secret}")
	private String secretKey;

	@Override
	public TokenResponse createNewToken(String userId) {
		String accessToken = getToken(userId, Duration.ofHours(3));
		String refreshToken = getToken(userId, Duration.ofHours(24));
		TokenPortResponse tokenPortResponse = insertTokenPort.create(userId, accessToken, refreshToken);

		return TokenResponse.builder()
			.accessToken(tokenPortResponse.getAccessToken())
			.refreshToken(tokenPortResponse.getRefreshToken())
			.build();
	}

	@Override
	public Boolean validateToken(String accessToken) {
		Jwts.parser()
			.setSigningKey(secretKey)
			.build()
			.parseClaimsJws(accessToken);

		return true;
	}

	@Override
	public String getTokenFromKakao(String code) {
		return kakaoTokenPort.getAccessTokenByCode(code);
	}

	@Override
	public UserResponse findUserByAccessToken(String accessToken) {
		Claims claims = parseClaims(accessToken);
		Object userId = claims.get("userId");

		if (ObjectUtils.isEmpty(userId)) {
			throw new RuntimeException("Invalid access token");
		}

		return fetchUserUseCase.findByProviderId(userId.toString());
	}

	private Claims parseClaims(String accessToken) {
		try {
			return Jwts.parser()
				.setSigningKey(secretKey)
				.build()
				.parseClaimsJws(accessToken)
				.getBody();
		} catch (ExpiredJwtException e) {
			return e.getClaims();
		}
	}

	private String getToken(String userId, Duration expireAt) {
		Date now = new Date();
		Instant instant = now.toInstant();

		return Jwts.builder()
			.claim("userId", userId)
			.issuedAt(now)
			.expiration(Date.from(instant.plus(expireAt)))
			.signWith(getSigningKey())
			.compact();
	}

	private SecretKey getSigningKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);

		return Keys.hmacShaKeyFor(keyBytes);
	}

	@Override
	public String upsertToken(String providerId) {
		TokenPortResponse byUserId = searchTokenPort.findByUserId(providerId);
		String accessToken = getToken(providerId, Duration.ofHours(3));
		String refreshToken = getToken(providerId, Duration.ofHours(24));

		if (byUserId == null) {
			insertTokenPort.create(providerId, accessToken, refreshToken);
		} else {
			updateTokenPort.updateToken(providerId, accessToken, refreshToken);
		}

		return accessToken;
	}
}
