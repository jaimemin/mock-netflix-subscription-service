package com.tistory.jaimemin.mocknetflix.tmdb;

public interface TmdbMoviePort {

	TmdbPageableMovies fetchPageable(int page);
}
