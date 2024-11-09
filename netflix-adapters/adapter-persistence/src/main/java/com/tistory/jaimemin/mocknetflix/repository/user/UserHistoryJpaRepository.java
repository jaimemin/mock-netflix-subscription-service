package com.tistory.jaimemin.mocknetflix.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tistory.jaimemin.mocknetflix.entity.user.UserHistoryEntity;

public interface UserHistoryJpaRepository extends JpaRepository<UserHistoryEntity, Long> {
}
