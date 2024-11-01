package com.tistory.jaimemin.mocknetflix.token;

import com.tistory.jaimemin.mocknetflix.user.UserPortResponse;

public interface KakaoUserPort {

	UserPortResponse findUserFromKakao(String accessToken);
}
