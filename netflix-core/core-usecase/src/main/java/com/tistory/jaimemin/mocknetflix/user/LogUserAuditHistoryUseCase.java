package com.tistory.jaimemin.mocknetflix.user;

public interface LogUserAuditHistoryUseCase {

	void log(String userId, String userRole, String clientIp, String requestMethod, String requestUrl,
		String requestHeader, String requestPayload);
}
