package com.tistory.jaimemin.mocknetflix.user;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserHistoryService implements LogUserAuditHistoryUseCase {

	private final UserHistoryPort userHistoryPort;

	@Override
	public void log(String userId, String userRole, String clientIp, String requestMethod, String requestUrl,
		String requestHeader, String requestPayload) {
		userHistoryPort.create(userId, userRole, clientIp, requestMethod, requestUrl, requestHeader, requestPayload);
	}
}
