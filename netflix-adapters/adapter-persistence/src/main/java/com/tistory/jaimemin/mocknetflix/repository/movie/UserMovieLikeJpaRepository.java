package com.tistory.jaimemin.mocknetflix.repository.movie;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tistory.jaimemin.mocknetflix.entity.movie.UserMovieLikeEntity;

public interface UserMovieLikeJpaRepository extends JpaRepository<UserMovieLikeEntity, String> {

	Optional<UserMovieLikeEntity> findByUserIdAndMovieId(String userId, String movieId);
}
