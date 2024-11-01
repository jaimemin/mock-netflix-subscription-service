package com.tistory.jaimemin.mocknetflix.entity.user;

import java.util.UUID;

import com.tistory.jaimemin.mocknetflix.audit.MutableBaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "social_users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SocialUserEntity extends MutableBaseEntity {

	@Id
	@Column(name = "SOCIAL_USER_ID")
	private String socialUserId;

	@Column(name = "USER_NAME")
	private String username;

	@Column(name = "PROVIDER")
	private String provider;

	@Column(name = "PROVIDER_ID")
	private String providerId;

	public SocialUserEntity(String username, String provider, String providerId) {
		this.socialUserId = UUID.randomUUID().toString();
		this.username = username;
		this.provider = provider;
		this.providerId = providerId;
	}
}
