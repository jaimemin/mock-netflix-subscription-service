package com.tistory.jaimemin.mocknetflix.tmdb;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class TmdbMovieNowPlayingResponse {

	private TmdbDateResponse dates;

	private String page;

	@JsonProperty("total_pages")
	private int totalPages;

	@JsonProperty("total_results")
	private int totalResults;

	private List<TmdbMovieNowPlaying> results;
}
