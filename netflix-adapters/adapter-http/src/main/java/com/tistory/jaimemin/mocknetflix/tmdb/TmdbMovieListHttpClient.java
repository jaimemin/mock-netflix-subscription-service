package com.tistory.jaimemin.mocknetflix.tmdb;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tistory.jaimemin.mocknetflix.client.TmdbHttpClient;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TmdbMovieListHttpClient implements TmdbMoviePort {

	private final TmdbHttpClient tmdbHttpClient;

	@Value("${tmdb.api.movie-lists.now-playing}")
	private String nowPlayingUrl;

	@Override
	public TmdbPageableMovies fetchPageable(int page) {
		String url = new StringBuilder()
			.append(nowPlayingUrl)
			.append("?language=ko-KR&page=")
			.append(page)
			.toString();
		String request = tmdbHttpClient.request(url, HttpMethod.GET, CollectionUtils.toMultiValueMap(Map.of()),
			Map.of());
		TmdbMovieNowPlayingResponse response = null;

		try {
			response = new ObjectMapper().readValue(request, TmdbMovieNowPlayingResponse.class);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

		return new TmdbPageableMovies(
			response.getResults().stream()
				.map(
					movie -> new TmdbMovie(
						movie.getTitle(),
						movie.getAdult(),
						movie.getGenreIds(),
						movie.getOverview(),
						movie.getReleaseDate())
				)
				.toList(),
			page,
			response.getTotalPages() - page != 0
		);
	}
}
