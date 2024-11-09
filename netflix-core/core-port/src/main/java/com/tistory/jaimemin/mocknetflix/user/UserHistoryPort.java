package com.tistory.jaimemin.mocknetflix.user;

public interface UserHistoryPort {

	void create(String userId, String userRole, String clientIp, String requestMethod, String requestUrl,
		String requestHeader,
		String requestPayload);
}
