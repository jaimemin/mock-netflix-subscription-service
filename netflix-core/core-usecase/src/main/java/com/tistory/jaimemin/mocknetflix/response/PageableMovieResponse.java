package com.tistory.jaimemin.mocknetflix.response;

import java.util.List;

import lombok.Getter;

@Getter
public class PageableMovieResponse {

	private final List<MovieResponse> tmdbMovies;

	private final int page;

	private final boolean hasNext;

	public PageableMovieResponse(List<MovieResponse> tmdbMovies, int page, boolean hasNext) {
		this.tmdbMovies = tmdbMovies;
		this.page = page;
		this.hasNext = hasNext;
	}
}
