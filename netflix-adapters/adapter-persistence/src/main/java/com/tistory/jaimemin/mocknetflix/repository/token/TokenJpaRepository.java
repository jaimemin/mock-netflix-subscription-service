package com.tistory.jaimemin.mocknetflix.repository.token;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tistory.jaimemin.mocknetflix.entity.token.TokenEntity;

public interface TokenJpaRepository extends JpaRepository<TokenEntity, Long>, TokenCustomRepository {
}
