package com.tistory.jaimemin.mocknetflix.movie;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserMovieDownload {

	private final String userMovieDownloadId;

	private final String userId;

	private final String movieId;

	public static UserMovieDownload newDownload(String userId, String movieId) {
		return UserMovieDownload.builder()
			.userMovieDownloadId(UUID.randomUUID().toString())
			.userId(userId)
			.movieId(movieId)
			.build();
	}
}
