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
public class MovieService implements FetchMovieUseCase, InsertMovieUseCase, DownloadMovieUseCase, LikeMovieUseCase {

	private final TmdbMoviePort tmdbMoviePort;

	private final LikeMoviePort likeMoviePort;

	private final DownloadMoviePort downloadMoviePort;

	private final PersistenceMoviePort persistenceMoviePort;

	private final List<UserDownloadMovieRoleValidator> validators;

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
	public PageableMovieResponse fetchFromDb(int page) {
		List<NetflixMovie> netflixMovies = persistenceMoviePort.fetchBy(page, 10);

		return new PageableMovieResponse(
			netflixMovies.stream()
				.map(it -> new MovieResponse(it.getMovieName(), it.getIsAdult(), List.of(), it.getOverview(),
					it.getReleasedAt())).toList(),
			page,
			true
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

	@Override
	public String download(String userId, String role, String movieId) {
		long cnt = downloadMoviePort.downloadCntToday(userId);
		boolean validate = validators.stream()
			.filter(validator -> validator.isTarget(role))
			.findAny()
			.orElseThrow()
			.validate(cnt);

		if (!validate) {
			throw new RuntimeException("더 이상 다운로드를 할 수 없습니다.");
		}

		NetflixMovie movie = persistenceMoviePort.findBy(movieId);
		downloadMoviePort.save(UserMovieDownload.newDownload(userId, movieId));

		return movie.getMovieName();
	}

	@Override
	public void like(String userId, String movieId) {
		likeMoviePort.findByUserIdAndMovieId(userId, movieId)
			.ifPresentOrElse(
				userMovieLike -> {
					userMovieLike.like();
					likeMoviePort.save(userMovieLike);
				},
				() -> likeMoviePort.save(UserMovieLike.newLike(userId, movieId))
			);
	}

	@Override
	public void unlike(String userId, String movieId) {
		likeMoviePort.findByUserIdAndMovieId(userId, movieId)
			.ifPresentOrElse(
				userMovieLike -> {
					userMovieLike.unlike();
					likeMoviePort.save(userMovieLike);
				},
				() -> likeMoviePort.save(UserMovieLike.newLike(userId, movieId))
			);
	}

}
