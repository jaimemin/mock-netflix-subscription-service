package com.tistory.jaimemin.mocknetflix.movie;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tistory.jaimemin.mocknetflix.response.MovieResponse;
import com.tistory.jaimemin.mocknetflix.response.PageableMovieResponse;
import com.tistory.jaimemin.mocknetflix.tmdb.TmdbMoviePort;
import com.tistory.jaimemin.mocknetflix.tmdb.TmdbPageableMovies;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService implements FetchMovieUseCase {

	private final TmdbMoviePort tmdbMoviePort;

	@Override
	public PageableMovieResponse fetchFromClient(int page) {
		TmdbPageableMovies tmdbPageableMovies = tmdbMoviePort.fetchPageable(page);

		return new PageableMovieResponse(
			tmdbPageableMovies.getTmdbMovies().stream()
				.map(movie -> new MovieResponse(
						movie.getMovieName(),
						movie.getIsAdult(),
						movie.getGenre(),
						movie.getOverview(),
						movie.getReleaseAt()
					)
				)
				.collect(Collectors.toList()),
			tmdbPageableMovies.getPage(),
			tmdbPageableMovies.isHasNext()
		);
	}
}
