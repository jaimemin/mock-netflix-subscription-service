package com.tistory.jaimemin.mocknetflix.movie;

import java.util.List;

import com.tistory.jaimemin.mocknetflix.response.MovieResponse;

public interface InsertMovieUseCase {

	void insert(List<MovieResponse> items);
}
