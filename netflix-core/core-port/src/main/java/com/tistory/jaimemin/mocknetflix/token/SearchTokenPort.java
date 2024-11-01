package com.tistory.jaimemin.mocknetflix.token;

public interface SearchTokenPort {

	TokenPortResponse findByUserId(String userId);
}
