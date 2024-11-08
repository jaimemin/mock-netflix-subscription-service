package com.tistory.jaimemin.mocknetflix.repository.movie;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tistory.jaimemin.mocknetflix.entity.movie.UserMovieDownloadEntity;

public interface UserMovieDownloadJpaRepository
	extends JpaRepository<UserMovieDownloadEntity, String>, UserMovieDownloadCustomRepository {

}
