package com.tistory.jaimemin.mocknetflix.repository.movie;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tistory.jaimemin.mocknetflix.entity.movie.UserMovieDownloadEntity;
import com.tistory.jaimemin.mocknetflix.movie.DownloadMoviePort;
import com.tistory.jaimemin.mocknetflix.movie.UserMovieDownload;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserMovieDownloadRepository implements DownloadMoviePort {

	private final UserMovieDownloadJpaRepository userMovieDownloadJpaRepository;

	@Override
	@Transactional
	public void save(UserMovieDownload domain) {
		userMovieDownloadJpaRepository.save(UserMovieDownloadEntity.toEntity(domain));
	}

	@Override
	public long downloadCntToday(String userId) {
		return userMovieDownloadJpaRepository.countDownloadToday(userId);
	}
}
