package com.tistory.jaimemin.mocknetflix.client;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TmdbHttpClient {

	private final HttpClient httpClient;

	@Value("${tmdb.auth.access-token}")
	private String accessToken;

	public String request(String uri, HttpMethod method, MultiValueMap<String, String> headers,
		Map<String, Object> param) {
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add(HttpHeaders.ACCEPT, "application/json");
		multiValueMap.add(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
		multiValueMap.addAll(headers);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.addAll(multiValueMap);

		return httpClient.request(uri, method, httpHeaders, param);
	}
}