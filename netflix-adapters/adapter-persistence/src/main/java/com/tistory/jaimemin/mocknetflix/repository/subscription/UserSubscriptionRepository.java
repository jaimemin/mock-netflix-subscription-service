package com.tistory.jaimemin.mocknetflix.repository.subscription;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tistory.jaimemin.mocknetflix.entity.subscription.UserSubscriptionEntity;
import com.tistory.jaimemin.mocknetflix.subscription.FetchUserSubscriptionPort;
import com.tistory.jaimemin.mocknetflix.subscription.InsertUserSubscriptionPort;
import com.tistory.jaimemin.mocknetflix.subscription.UpdateUserSubscriptionPort;
import com.tistory.jaimemin.mocknetflix.subscription.UserSubscription;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserSubscriptionRepository implements FetchUserSubscriptionPort, InsertUserSubscriptionPort,
	UpdateUserSubscriptionPort {

	private final UserSubscriptionJpaRepository jpaRepository;

	private final UserSubscriptionJpaRepository userSubscriptionJpaRepository;

	@Override
	public Optional<UserSubscription> fetchByUserId(String userId) {
		return jpaRepository.findByUserId(userId)
			.map(UserSubscriptionEntity::toDomain);
	}

	@Override
	@Transactional
	public void create(String userId) {
		UserSubscription userSubscription = UserSubscription.newSubscription(userId);

		jpaRepository.save(UserSubscriptionEntity.toEntity(userSubscription));
	}

	@Override
	@Transactional
	public void update(UserSubscription subscription) {
		userSubscriptionJpaRepository.save(UserSubscriptionEntity.toEntity(subscription));
	}
}
