package com.tistory.jaimemin.mocknetflix.repository.token;

import static com.tistory.jaimemin.mocknetflix.entity.token.QTokenEntity.*;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tistory.jaimemin.mocknetflix.entity.token.TokenEntity;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TokenCustomRepositoryImpl implements TokenCustomRepository {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Optional<TokenEntity> findByUserId(String userId) {
		return jpaQueryFactory.selectFrom(tokenEntity)
			.where(tokenEntity.userId.eq(userId))
			.fetch()
			.stream().findFirst();
	}
}
