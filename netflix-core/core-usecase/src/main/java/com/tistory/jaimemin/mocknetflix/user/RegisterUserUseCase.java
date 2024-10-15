package com.tistory.jaimemin.mocknetflix.user;

import com.tistory.jaimemin.mocknetflix.user.command.UserRegistrationCommand;
import com.tistory.jaimemin.mocknetflix.user.response.UserRegistrationResponse;

public interface RegisterUserUseCase {

	UserRegistrationResponse register(UserRegistrationCommand command);
}
