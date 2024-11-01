package com.tistory.jaimemin.mocknetflix.token;

public interface KakaoTokenPort {

	String getAccessTokenByCode(String code);
}
