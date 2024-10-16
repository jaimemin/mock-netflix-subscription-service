package com.tistory.jaimemin.mocknetflix.controller.user;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tistory.jaimemin.mocknetflix.controller.NetflixApiResponse;
import com.tistory.jaimemin.mocknetflix.controller.user.request.UserLoginRequest;
import com.tistory.jaimemin.mocknetflix.controller.user.request.UserRegisterRequest;
import com.tistory.jaimemin.mocknetflix.security.NetflixAuthUser;
import com.tistory.jaimemin.mocknetflix.user.RegisterUserUseCase;
import com.tistory.jaimemin.mocknetflix.user.command.UserRegistrationCommand;
import com.tistory.jaimemin.mocknetflix.user.response.UserRegistrationResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

	private final RegisterUserUseCase registerUserUseCase;

	private final AuthenticationManagerBuilder authenticationManagerBuilder;

	@PostMapping("/register")
	public NetflixApiResponse<UserRegistrationResponse> register(@RequestBody UserRegisterRequest request) {
		UserRegistrationResponse register = registerUserUseCase.register(
			UserRegistrationCommand.builder()
				.email(request.getEmail())
				.username(request.getUsername())
				.encryptedPassword(request.getPassword())
				.phone(request.getPhone())
				.build()
		);

		return NetflixApiResponse.ok(register);
	}

	@PostMapping("/login")
	public NetflixApiResponse<String> login(@RequestBody UserLoginRequest request) {
		String email = request.getEmail();
		String password = request.getPassword();
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
		Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(token);
		NetflixAuthUser principal = (NetflixAuthUser)authenticate.getPrincipal();

		// 수정 예정
		return NetflixApiResponse.ok("access-token");
	}
}
