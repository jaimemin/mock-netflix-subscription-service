package com.tistory.jaimemin.mocknetflix.repository.movie;

import static com.tistory.jaimemin.mocknetflix.entity.movie.QUserMovieDownloadEntity.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserMovieDownloadCustomRepositoryImpl implements UserMovieDownloadCustomRepository {

	private final JPAQueryFactory factory;

	@Override
	public long countDownloadToday(String userId) {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime start = now.truncatedTo(ChronoUnit.DAYS);
		LocalDateTime end = start.plusDays(1).truncatedTo(ChronoUnit.DAYS);

		return factory.selectFrom(userMovieDownloadEntity)
			.where(userMovieDownloadEntity.userId.eq(userId)
				.and(userMovieDownloadEntity.createdAt.goe(start))
				.and(userMovieDownloadEntity.createdAt.lt(end))
			)
			.fetch()
			.size();
	}
}
