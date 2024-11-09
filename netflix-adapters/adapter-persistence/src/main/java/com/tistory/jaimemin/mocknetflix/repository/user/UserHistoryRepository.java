package com.tistory.jaimemin.mocknetflix.repository.user;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tistory.jaimemin.mocknetflix.entity.user.UserHistoryEntity;
import com.tistory.jaimemin.mocknetflix.user.UserHistoryPort;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserHistoryRepository implements UserHistoryPort {

	private final UserHistoryJpaRepository userHistoryJpaRepository;

	@Override
	@Transactional
	public void create(String userId, String userRole, String clientIp, String requestMethod, String requestUrl,
		String requestHeader, String requestPayload) {
		userHistoryJpaRepository.save(
			new UserHistoryEntity(userId, userRole, clientIp, requestMethod, requestUrl, requestHeader, requestPayload)
		);
	}
}
