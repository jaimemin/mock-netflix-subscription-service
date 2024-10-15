package com.tistory.jaimemin.mocknetflix.repository.user;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.tistory.jaimemin.mocknetflix.entity.user.UserEntity;
import com.tistory.jaimemin.mocknetflix.user.CreateUser;
import com.tistory.jaimemin.mocknetflix.user.FetchUserPort;
import com.tistory.jaimemin.mocknetflix.user.InsertUserPort;
import com.tistory.jaimemin.mocknetflix.user.UserPortResponse;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Repository
@Transactional
@RequiredArgsConstructor
public class UserRepository implements FetchUserPort, InsertUserPort {

	private final UserJpaRepository userJpaRepository;

	@Override
	public Optional<UserPortResponse> findByEmail(String email) {
		return userJpaRepository.findByEmail(email)
			.map(byEmail -> UserPortResponse.builder()
				.userId(byEmail.getUserId())
				.username(byEmail.getUsername())
				.password(byEmail.getPassword())
				.email(byEmail.getEmail())
				.phone(byEmail.getPhone())
				.build());
	}

	@Override
	public UserPortResponse create(CreateUser user) {
		UserEntity userEntity = new UserEntity(
			user.getUsername(),
			user.getEncryptedPassword(),
			user.getEmail(),
			user.getPhone()
		);
		UserEntity save = userJpaRepository.save(userEntity);

		return UserPortResponse.builder()
			.userId(save.getUserId())
			.username(save.getUsername())
			.password(save.getPassword())
			.email(save.getEmail())
			.phone(save.getPhone())
			.build();
	}
}
