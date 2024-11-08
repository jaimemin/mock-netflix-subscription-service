package com.tistory.jaimemin.mocknetflix.entity.subscription;

import java.time.LocalDateTime;
import java.util.UUID;

import com.tistory.jaimemin.mocknetflix.audit.MutableBaseEntity;
import com.tistory.jaimemin.mocknetflix.subscription.SubscriptionType;
import com.tistory.jaimemin.mocknetflix.subscription.UserSubscription;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@AllArgsConstructor
@Table(name = "user_subscriptions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserSubscriptionEntity extends MutableBaseEntity {

	@Id
	@Column(name = "USER_SUBSCRIPTION_ID")
	private String userSubscriptionId;

	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "SUBSCRIPTION_NAME")
	@Enumerated(value = EnumType.STRING)
	private SubscriptionType subscriptionType;

	@Column(name = "START_AT")
	private LocalDateTime subscriptionStartAt;

	@Column(name = "END_AT")
	private LocalDateTime subscriptionEndAt;

	@Column(name = "VALID_YN")
	private Boolean validYn;

	public UserSubscription toDomain() {
		return UserSubscription.builder()
			.userId(this.userId)
			.subscriptionType(this.subscriptionType)
			.startAt(this.subscriptionStartAt)
			.endAt(this.subscriptionEndAt)
			.validYn(this.validYn)
			.build();
	}

	public static UserSubscriptionEntity toEntity(UserSubscription userSubscription) {
		return new UserSubscriptionEntity(
			UUID.randomUUID().toString(),
			userSubscription.getUserId(),
			userSubscription.getSubscriptionType(),
			userSubscription.getStartAt(),
			userSubscription.getEndAt(),
			userSubscription.getValidYn()
		);
	}
}
