package com.tistory.jaimemin.mocknetflix.batch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader;

import com.tistory.jaimemin.mocknetflix.movie.FetchMovieUseCase;
import com.tistory.jaimemin.mocknetflix.response.MovieResponse;
import com.tistory.jaimemin.mocknetflix.response.PageableMovieResponse;

public class HttpPageItemReader extends AbstractItemCountingItemStreamItemReader<MovieResponse> {

	private int page;

	private final List<MovieResponse> contents = new ArrayList<>();

	private final FetchMovieUseCase fetchMovieUseCase;

	public HttpPageItemReader(int page, FetchMovieUseCase fetchMovieUseCase) {
		this.page = page;
		this.fetchMovieUseCase = fetchMovieUseCase;
	}

	@Override
	protected MovieResponse doRead() throws Exception {
		if (this.contents.isEmpty()) {
			readRow();
		}

		int size = contents.size();
		int idx = size - 1;

		if (idx < 0) {
			return null;
		}

		return contents.remove(contents.size() - 1);
	}

	@Override
	protected void doOpen() throws Exception {
		setName(HttpPageItemReader.class.getName());
	}

	@Override
	protected void doClose() throws Exception {

	}

	private void readRow() {
		PageableMovieResponse pageableMovieResponse = fetchMovieUseCase.fetchFromClient(page);
		contents.addAll(pageableMovieResponse.getTmdbMovies());
		page++;
	}
}
