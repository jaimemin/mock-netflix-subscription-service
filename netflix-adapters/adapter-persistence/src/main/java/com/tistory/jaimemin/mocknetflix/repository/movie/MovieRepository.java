package com.tistory.jaimemin.mocknetflix.repository.movie;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tistory.jaimemin.mocknetflix.entity.movie.MovieEntity;
import com.tistory.jaimemin.mocknetflix.movie.NetflixMovie;
import com.tistory.jaimemin.mocknetflix.movie.PersistenceMoviePort;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MovieRepository implements PersistenceMoviePort {

	private final MovieJpaRepository movieJpaRepository;

	@Override
	public List<NetflixMovie> fetchBy(int page, int size) {
		return movieJpaRepository.search(PageRequest.of(page, size))
			.stream()
			.map(MovieEntity::toDomain)
			.toList();
	}

	@Override
	public NetflixMovie findBy(String movieName) {
		return movieJpaRepository.findByMovieName(movieName)
			.map(MovieEntity::toDomain)
			.orElseThrow();
	}

	@Override
	@Transactional
	public void insert(NetflixMovie movie) {
		movieJpaRepository.findByMovieName(movie.getMovieName())
			.orElseGet(() -> {
				MovieEntity movieEntity = MovieEntity.newEntity(
					movie.getMovieName(),
					movie.getIsAdult(),
					movie.getGenre(),
					movie.getOverview(),
					movie.getReleasedAt()
				);
				
				return movieJpaRepository.save(movieEntity);
			});
	}
}
