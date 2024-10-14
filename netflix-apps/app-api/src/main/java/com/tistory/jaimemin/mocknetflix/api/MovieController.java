package com.tistory.jaimemin.mocknetflix.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tistory.jaimemin.mocknetflix.movie.FetchMovieUseCase;
import com.tistory.jaimemin.mocknetflix.response.PageableMovieResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MovieController {

	private final FetchMovieUseCase fetchMovieUseCase;

	@GetMapping("/api/v1/movie/client/{page}")
	public String fetchMoviePageables(@PathVariable int page) {
		PageableMovieResponse pageableMovieResponse = fetchMovieUseCase.fetchFromClient(page);

		return "";
	}
}
