package com.tistory.jaimemin.mocknetflix.repository.token;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tistory.jaimemin.mocknetflix.entity.token.TokenEntity;
import com.tistory.jaimemin.mocknetflix.token.InsertTokenPort;
import com.tistory.jaimemin.mocknetflix.token.SearchTokenPort;
import com.tistory.jaimemin.mocknetflix.token.TokenPortResponse;
import com.tistory.jaimemin.mocknetflix.token.UpdateTokenPort;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TokenRepository implements SearchTokenPort, InsertTokenPort, UpdateTokenPort {

	private final TokenJpaRepository tokenJpaRepository;

	@Override
	@Transactional
	public TokenPortResponse create(String userId, String accessToken, String refreshToken) {
		TokenEntity entity = TokenEntity.newTokenEntity(userId, accessToken, refreshToken);
		tokenJpaRepository.save(entity);

		return new TokenPortResponse(accessToken, refreshToken);
	}

	@Override
	public TokenPortResponse findByUserId(String userId) {
		return tokenJpaRepository.findByUserId(userId)
			.map(result -> new TokenPortResponse(result.getAccessToken(), result.getRefreshToken()))
			.orElse(null);
	}

	@Override
	@Transactional
	public void updateToken(String userId, String accessToken, String refreshToken) {
		TokenEntity tokenEntity = tokenJpaRepository.findByUserId(userId).orElseThrow();
		tokenEntity.updateToken(accessToken, refreshToken);

		tokenJpaRepository.save(tokenEntity);
	}
}
