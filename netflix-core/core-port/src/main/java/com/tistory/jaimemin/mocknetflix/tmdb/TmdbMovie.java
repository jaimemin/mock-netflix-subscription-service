package com.tistory.jaimemin.mocknetflix.tmdb;

import java.util.List;

import lombok.Getter;

@Getter
public class TmdbMovie {

	private final String movieName;

	private final Boolean isAdult;

	private final List<String> genre;

	private final String overview;

	private final String releaseAt;

	public TmdbMovie(String movieName, Boolean isAdult, List<String> genre, String overview, String releaseAt) {
		this.movieName = movieName;
		this.isAdult = isAdult;
		this.genre = genre;
		this.overview = overview;
		this.releaseAt = releaseAt;
	}
}
