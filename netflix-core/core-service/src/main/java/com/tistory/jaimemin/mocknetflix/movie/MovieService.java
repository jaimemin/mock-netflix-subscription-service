package com.tistory.jaimemin.mocknetflix.movie;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tistory.jaimemin.mocknetflix.response.MovieResponse;
import com.tistory.jaimemin.mocknetflix.response.PageableMovieResponse;
import com.tistory.jaimemin.mocknetflix.tmdb.TmdbMoviePort;
import com.tistory.jaimemin.mocknetflix.tmdb.TmdbPageableMovies;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService implements FetchMovieUseCase, InsertMovieUseCase {

	private final TmdbMoviePort tmdbMoviePort;

	private final PersistenceMoviePort persistenceMoviePort;

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

	@Override
	public void insert(List<MovieResponse> items) {
		items.forEach(it -> {
			NetflixMovie netflixMovie = NetflixMovie.builder()
				.movieName(it.getMovieName())
				.isAdult(it.getIsAdult())
				.overview(it.getOverview())
				.releasedAt(it.getReleaseAt())
				.genre("dummy genre")
				.build();

			persistenceMoviePort.insert(netflixMovie);
		});
	}
}
