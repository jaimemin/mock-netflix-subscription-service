package com.tistory.jaimemin.mocknetflix.repository.user;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tistory.jaimemin.mocknetflix.entity.user.SocialUserEntity;
import com.tistory.jaimemin.mocknetflix.entity.user.UserEntity;
import com.tistory.jaimemin.mocknetflix.repository.subscription.UserSubscriptionRepository;
import com.tistory.jaimemin.mocknetflix.repository.user.social.SocialUserJpaRepository;
import com.tistory.jaimemin.mocknetflix.subscription.UserSubscription;
import com.tistory.jaimemin.mocknetflix.user.CreateUser;
import com.tistory.jaimemin.mocknetflix.user.FetchUserPort;
import com.tistory.jaimemin.mocknetflix.user.InsertUserPort;
import com.tistory.jaimemin.mocknetflix.user.UserPortResponse;

import lombok.RequiredArgsConstructor;

@Repository
@Transactional
@RequiredArgsConstructor
public class UserRepository implements FetchUserPort, InsertUserPort {

	private final UserJpaRepository userJpaRepository;

	private final SocialUserJpaRepository socialUserJpaRepository;

	private final UserSubscriptionRepository userSubscriptionRepository;

	@Override
	public Optional<UserPortResponse> findByEmail(String email) {
		return userJpaRepository.findByEmail(email)
			.map(byEmail -> UserPortResponse.builder()
				.userId(byEmail.getUserId())
				.username(byEmail.getUsername())
				.password(byEmail.getPassword())
				.email(byEmail.getEmail())
				.phone(byEmail.getPhone())
				.build());
	}

	@Override
	public Optional<UserPortResponse> findByProviderId(String providerId) {
		return socialUserJpaRepository.findByProviderId(providerId).map(socialUserEntity -> {
			UserSubscription userSubscription = userSubscriptionRepository.fetchByUserId(
				socialUserEntity.getSocialUserId()
			).orElseGet(() -> UserSubscription.newSubscription(socialUserEntity.getSocialUserId()));

			return UserPortResponse.builder()
				.userId(socialUserEntity.getSocialUserId())
				.provider(socialUserEntity.getProvider())
				.providerId(socialUserEntity.getProviderId())
				.username(socialUserEntity.getUsername())
				.role(userSubscription.getSubscriptionType().toRole())
				.build();
		});
	}

	@Override
	@Transactional
	public UserPortResponse create(CreateUser user) {
		UserEntity userEntity = new UserEntity(
			user.getUsername(),
			user.getEncryptedPassword(),
			user.getEmail(),
			user.getPhone()
		);
		UserEntity save = userJpaRepository.save(userEntity);
		userSubscriptionRepository.create(userEntity.getUserId());

		return UserPortResponse.builder()
			.userId(save.getUserId())
			.username(save.getUsername())
			.password(save.getPassword())
			.email(save.getEmail())
			.phone(save.getPhone())
			.build();
	}

	@Override
	@Transactional
	public UserPortResponse createSocialUser(String username, String provider, String providerId) {
		SocialUserEntity socialUserEntity = new SocialUserEntity(username, provider, providerId);
		socialUserJpaRepository.save(socialUserEntity);
		userSubscriptionRepository.create(socialUserEntity.getSocialUserId());

		return UserPortResponse.builder()
			.username(socialUserEntity.getUsername())
			.provider(socialUserEntity.getProvider())
			.providerId(socialUserEntity.getProviderId())
			.build();
	}
}
