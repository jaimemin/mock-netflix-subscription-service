package com.tistory.jaimemin.mocknetflix.entity.user;

import com.tistory.jaimemin.mocknetflix.audit.MutableBaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "user_histories")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserHistoryEntity extends MutableBaseEntity {

	@Id
	@Column(name = "USER_HISTORY_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userHistoryId;

	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "USER_ROLE")
	private String userRole;

	@Column(name = "REQ_IP")
	private String clientIp;

	@Column(name = "REQ_METHOD")
	private String requestMethod;

	@Column(name = "REQ_URL")
	private String requestUrl;

	@Column(name = "REQ_HEADER")
	private String requestHeader;

	@Column(name = "REQ_PAYLOAD")
	private String requestPayload;

	public UserHistoryEntity(String userId, String userRole, String clientIp, String requestMethod,
		String requestUrl, String requestHeader, String requestPayload) {
		this.userId = userId;
		this.userRole = userRole;
		this.clientIp = clientIp;
		this.requestMethod = requestMethod;
		this.requestUrl = requestUrl;
		this.requestHeader = requestHeader;
		this.requestPayload = requestPayload;
	}
}
