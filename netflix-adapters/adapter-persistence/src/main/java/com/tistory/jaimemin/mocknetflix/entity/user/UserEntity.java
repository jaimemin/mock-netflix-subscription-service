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
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity extends MutableBaseEntity {

	@Id
	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "USER_NAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PHONE")
	private String phone;

	public UserEntity(String username, String password, String email, String phone) {
		this.userId = UUID.randomUUID().toString();
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
	}
}
