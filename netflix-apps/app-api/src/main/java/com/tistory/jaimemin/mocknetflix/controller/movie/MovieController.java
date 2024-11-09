package com.tistory.jaimemin.mocknetflix.controller.movie;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tistory.jaimemin.mocknetflix.controller.NetflixApiResponse;
import com.tistory.jaimemin.mocknetflix.filter.JwtTokenProvider;
import com.tistory.jaimemin.mocknetflix.movie.DownloadMovieUseCase;
import com.tistory.jaimemin.mocknetflix.movie.FetchMovieUseCase;
import com.tistory.jaimemin.mocknetflix.movie.LikeMovieUseCase;
import com.tistory.jaimemin.mocknetflix.response.PageableMovieResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movie")
@CrossOrigin(origins = "http://localhost:3000")
public class MovieController {

	private final JwtTokenProvider jwtTokenProvider;

	private final LikeMovieUseCase likeMovieUseCase;

	private final FetchMovieUseCase fetchMovieUseCase;

	private final DownloadMovieUseCase downloadMovieUseCase;

	@GetMapping("/client/{page}")
	public NetflixApiResponse<PageableMovieResponse> fetchMoviePageables(@PathVariable int page) {
		PageableMovieResponse pageableMovieResponse = fetchMovieUseCase.fetchFromClient(page);

		return NetflixApiResponse.ok(pageableMovieResponse);
	}

	@PostMapping("/search")
	public NetflixApiResponse<PageableMovieResponse> search(@RequestParam int page) {
		PageableMovieResponse pageableMovieResponse = fetchMovieUseCase.fetchFromDb(page);

		return NetflixApiResponse.ok(pageableMovieResponse);
	}

	@PostMapping("/{movieId}/download")
	@PreAuthorize("hasAnyRole('ROLE_BRONZE', 'ROLE_SILVER', 'ROLE_GOLD')")
	public NetflixApiResponse<String> download(@PathVariable String movieId) {
		String download = downloadMovieUseCase.download(jwtTokenProvider.getuserId(), jwtTokenProvider.getRole(),
			movieId);

		return NetflixApiResponse.ok(download);
	}

	@PostMapping("/{movieId}/like")
	@PreAuthorize("hasAnyRole('ROLE_FREE', 'ROLE_BRONZE', 'ROLE_SILVER', 'ROLE_GOLD')")
	public NetflixApiResponse<String> like(@PathVariable String movieId) {
		String userId = jwtTokenProvider.getuserId();
		likeMovieUseCase.like(userId, movieId);

		return NetflixApiResponse.ok("liked");
	}

	@PostMapping("/{movieId}/unlike")
	@PreAuthorize("hasAnyRole('ROLE_FREE', 'ROLE_BRONZE', 'ROLE_SILVER', 'ROLE_GOLD')")
	public NetflixApiResponse<String> unlike(@PathVariable String movieId) {
		String userId = jwtTokenProvider.getuserId();
		likeMovieUseCase.unlike(userId, movieId);

		return NetflixApiResponse.ok("liked");
	}
}
