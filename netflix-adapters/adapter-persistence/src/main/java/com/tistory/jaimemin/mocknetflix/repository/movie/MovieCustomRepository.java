package com.tistory.jaimemin.mocknetflix.repository.movie;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tistory.jaimemin.mocknetflix.entity.movie.MovieEntity;

public interface MovieCustomRepository {

	Optional<MovieEntity> findByMovieName(String name);

	Page<MovieEntity> search(Pageable pageable);
}
