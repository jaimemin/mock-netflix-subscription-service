package com.tistory.jaimemin.mocknetflix.user;

public interface InsertUserPort {

	UserPortResponse create(CreateUser user);

	UserPortResponse createSocialUser(String username, String provider, String providerId);
}
