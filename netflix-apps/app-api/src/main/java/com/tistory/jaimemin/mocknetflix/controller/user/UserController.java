package com.tistory.jaimemin.mocknetflix.controller.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tistory.jaimemin.mocknetflix.controller.NetflixApiResponse;
import com.tistory.jaimemin.mocknetflix.controller.user.request.UserRegisterRequest;
import com.tistory.jaimemin.mocknetflix.user.RegisterUserUseCase;
import com.tistory.jaimemin.mocknetflix.user.command.UserRegistrationCommand;
import com.tistory.jaimemin.mocknetflix.user.response.UserRegistrationResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

	private final RegisterUserUseCase registerUserUseCase;

	private final PasswordEncoder passwordEncoder;

	@PostMapping("/user/register")
	public NetflixApiResponse<UserRegistrationResponse> register(@RequestBody UserRegisterRequest request) {
		UserRegistrationResponse register = registerUserUseCase.register(
			UserRegistrationCommand.builder()
				.email(request.getEmail())
				.username(request.getUsername())
				.encryptedPassword(passwordEncoder.encode(request.getPassword()))
				.phone(request.getPhone())
				.build()
		);

		return NetflixApiResponse.ok(register);
	}
}
