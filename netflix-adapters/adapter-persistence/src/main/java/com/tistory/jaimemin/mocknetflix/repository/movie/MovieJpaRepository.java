package com.tistory.jaimemin.mocknetflix.repository.movie;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tistory.jaimemin.mocknetflix.entity.movie.MovieEntity;

public interface MovieJpaRepository extends JpaRepository<MovieEntity, String>, MovieCustomRepository {
}
