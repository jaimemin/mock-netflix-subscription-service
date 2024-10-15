package com.tistory.jaimemin.mocknetflix.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tistory.jaimemin.mocknetflix.entity.user.UserEntity;

public interface UserJpaRepository extends JpaRepository<UserEntity, String> {

	Optional<UserEntity> findByEmail(String email);
}
