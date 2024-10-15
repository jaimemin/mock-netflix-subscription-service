package com.tistory.jaimemin.mocknetflix.user;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tistory.jaimemin.mocknetflix.exception.UserException;
import com.tistory.jaimemin.mocknetflix.user.command.UserRegistrationCommand;
import com.tistory.jaimemin.mocknetflix.user.response.UserRegistrationResponse;
import com.tistory.jaimemin.mocknetflix.user.response.UserResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements FetchUserUseCase, RegisterUserUseCase {

	private final FetchUserPort fetchUserPort;

	private final InsertUserPort insertUserPort;

	@Override
	public UserResponse findUserByEmail(String email) {
		UserPortResponse byEmail = fetchUserPort.findByEmail(email)
			.orElseThrow(() -> new UserException.UserDoesNotExistException());

		return UserResponse.builder()
			.userId(byEmail.getUserId())
			.username(byEmail.getUsername())
			.email(byEmail.getEmail())
			.password(byEmail.getPassword())
			.phone(byEmail.getPhone())
			.role(byEmail.getRole())
			.build();
	}

	@Override
	public UserRegistrationResponse register(UserRegistrationCommand command) {
		String email = command.getEmail();
		Optional<UserPortResponse> userPortResponse = fetchUserPort.findByEmail(email);

		if (userPortResponse.isPresent()) {
			throw new UserException.UserAlreadyExistsException();
		}

		UserPortResponse response = insertUserPort.create(CreateUser.builder()
			.username(command.getUsername())
			.encryptedPassword(command.getEncryptedPassword())
			.email(command.getEmail())
			.phone(command.getPhone())
			.build());
		
		return new UserRegistrationResponse(response.getUsername(), response.getEmail(), response.getPhone());
	}
}
