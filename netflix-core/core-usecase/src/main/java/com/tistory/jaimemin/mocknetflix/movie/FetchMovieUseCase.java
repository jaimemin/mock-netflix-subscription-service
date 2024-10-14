package com.tistory.jaimemin.mocknetflix.movie;

import com.tistory.jaimemin.mocknetflix.response.PageableMovieResponse;

public interface FetchMovieUseCase {

	PageableMovieResponse fetchFromClient(int page);
}
