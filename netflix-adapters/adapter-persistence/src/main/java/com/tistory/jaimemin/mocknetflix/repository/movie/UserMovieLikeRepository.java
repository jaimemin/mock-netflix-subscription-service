package com.tistory.jaimemin.mocknetflix.repository.movie;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.tistory.jaimemin.mocknetflix.entity.movie.UserMovieLikeEntity;
import com.tistory.jaimemin.mocknetflix.movie.LikeMoviePort;
import com.tistory.jaimemin.mocknetflix.movie.UserMovieLike;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserMovieLikeRepository implements LikeMoviePort {

	private final UserMovieLikeJpaRepository userMovieLikeJpaRepository;

	@Override
	public void save(UserMovieLike domain) {
		userMovieLikeJpaRepository.save(UserMovieLikeEntity.toEntity(domain));
	}

	@Override
	public Optional<UserMovieLike> findByUserIdAndMovieId(String userId, String movieId) {
		return userMovieLikeJpaRepository.findByUserIdAndMovieId(userId, movieId)
			.map(UserMovieLikeEntity::toDomain);
	}
}
