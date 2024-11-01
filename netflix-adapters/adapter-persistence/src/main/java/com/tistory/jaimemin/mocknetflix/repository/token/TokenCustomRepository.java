package com.tistory.jaimemin.mocknetflix.repository.token;

import java.util.Optional;

import com.tistory.jaimemin.mocknetflix.entity.token.TokenEntity;

public interface TokenCustomRepository {

	Optional<TokenEntity> findByUserId(String userId);
}
