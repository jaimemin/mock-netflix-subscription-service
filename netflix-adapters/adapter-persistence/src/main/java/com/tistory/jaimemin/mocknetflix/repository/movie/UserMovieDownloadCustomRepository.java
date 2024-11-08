package com.tistory.jaimemin.mocknetflix.repository.movie;

public interface UserMovieDownloadCustomRepository {

	long countDownloadToday(String userId);
}
