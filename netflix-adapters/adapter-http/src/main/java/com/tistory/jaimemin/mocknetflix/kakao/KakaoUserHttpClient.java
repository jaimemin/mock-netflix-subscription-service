package com.tistory.jaimemin.mocknetflix.kakao;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.tistory.jaimemin.mocknetflix.token.KakaoUserPort;
import com.tistory.jaimemin.mocknetflix.user.UserPortResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KakaoUserHttpClient implements KakaoUserPort {

	private final String KAKAO_USERINFO_API_URL = "https://kapi.kakao.com/v2/user/me";

	@Override
	public UserPortResponse findUserFromKakao(String accessToken) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + accessToken);

		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<Map> response = restTemplate.exchange(KAKAO_USERINFO_API_URL, HttpMethod.GET, entity, Map.class);

		Map properties = (Map)response.getBody().get("properties");
		String nickname = (String)properties.get("nickname");
		Long id = (Long)response.getBody().get("id");

		return UserPortResponse.builder()
			.username(nickname)
			.provider("kakao")
			.providerId(id.toString())
			.build();
	}
}
