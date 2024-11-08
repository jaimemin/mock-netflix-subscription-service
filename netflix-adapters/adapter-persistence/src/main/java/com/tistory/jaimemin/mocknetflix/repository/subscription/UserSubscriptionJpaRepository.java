package com.tistory.jaimemin.mocknetflix.repository.subscription;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tistory.jaimemin.mocknetflix.entity.subscription.UserSubscriptionEntity;

public interface UserSubscriptionJpaRepository extends JpaRepository<UserSubscriptionEntity, Long> {

	Optional<UserSubscriptionEntity> findByUserId(String userId);
}
