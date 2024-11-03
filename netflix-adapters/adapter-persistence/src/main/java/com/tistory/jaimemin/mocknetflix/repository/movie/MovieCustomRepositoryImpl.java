package com.tistory.jaimemin.mocknetflix.repository.movie;

import static com.tistory.jaimemin.mocknetflix.entity.movie.QMovieEntity.*;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tistory.jaimemin.mocknetflix.entity.movie.MovieEntity;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MovieCustomRepositoryImpl implements MovieCustomRepository {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Optional<MovieEntity> findByMovieName(String name) {
		return jpaQueryFactory.selectFrom(movieEntity)
			.where(movieEntity.movieName.eq(name))
			.fetch()
			.stream()
			.findFirst();
	}

	@Override
	public Page<MovieEntity> search(Pageable pageable) {
		List<MovieEntity> fetch = jpaQueryFactory.selectFrom(movieEntity)
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();
		long count = jpaQueryFactory.select(movieEntity)
			.fetch()
			.size();

		return new PageImpl<>(fetch, pageable, count);
	}
}
