package com.tistory.jaimemin.mocknetflix.repository.user.social;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tistory.jaimemin.mocknetflix.entity.user.SocialUserEntity;

public interface SocialUserJpaRepository extends JpaRepository<SocialUserEntity, String> {

	Optional<SocialUserEntity> findByProviderId(String providerId);
}
