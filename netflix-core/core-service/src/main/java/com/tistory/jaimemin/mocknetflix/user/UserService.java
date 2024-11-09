package com.tistory.jaimemin.mocknetflix.user;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tistory.jaimemin.mocknetflix.exception.UserException;
import com.tistory.jaimemin.mocknetflix.token.KakaoUserPort;
import com.tistory.jaimemin.mocknetflix.user.command.UserRegistrationCommand;
import com.tistory.jaimemin.mocknetflix.user.response.UserRegistrationResponse;
import com.tistory.jaimemin.mocknetflix.user.response.UserResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements FetchUserUseCase, RegisterUserUseCase {

	private final FetchUserPort fetchUserPort;

	private final InsertUserPort insertUserPort;

	private final KakaoUserPort kakaoUserPort;

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
	public UserResponse findByProviderId(String providerId) {
		return fetchUserPort.findByProviderId(providerId)
			.map(it -> UserResponse.builder()
				.userId(it.getUserId())
				.username(it.getUsername())
				.provider(it.getProvider())
				.providerId(it.getProviderId())
				.role(it.getRole())
				.build())
			.orElse(null);
	}

	@Override
	public UserResponse findKakaoUser(String accessToken) {
		UserPortResponse userFromKakao = kakaoUserPort.findUserFromKakao(accessToken);

		return UserResponse.builder()
			.provider(userFromKakao.getProvider())
			.providerId(userFromKakao.getProviderId())
			.username(userFromKakao.getUsername())
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

	@Override
	public UserRegistrationResponse registerSocialUser(String username, String provider, String providerId) {
		return fetchUserPort.findByProviderId(providerId)
			.map(user -> (UserRegistrationResponse)null) // 이미 존재하면 null 반환
			.orElseGet(() -> {
				UserPortResponse socialUser = insertUserPort.createSocialUser(username, provider, providerId);

				return new UserRegistrationResponse(socialUser.getUsername(), socialUser.getEmail(),
					socialUser.getPhone());
			});
	}
}
