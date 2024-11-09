package com.tistory.jaimemin.mocknetflix.movie;

public interface LikeMovieUseCase {

	void like(String userId, String movieId);

	void unlike(String userId, String movieId);
}
