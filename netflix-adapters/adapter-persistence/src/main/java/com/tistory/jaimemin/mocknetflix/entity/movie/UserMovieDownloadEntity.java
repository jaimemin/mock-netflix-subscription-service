package com.tistory.jaimemin.mocknetflix.entity.movie;

import com.tistory.jaimemin.mocknetflix.audit.MutableBaseEntity;
import com.tistory.jaimemin.mocknetflix.movie.UserMovieDownload;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@AllArgsConstructor
@Table(name = "user_movie_downloads")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserMovieDownloadEntity extends MutableBaseEntity {

	@Id
	@Column(name = "USER_MOVIE_DOWNLOAD_ID")
	private String userMovieDownloadId;

	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "MOVIE_ID")
	private String movieId;

	public UserMovieDownload toDomain() {
		return UserMovieDownload.builder()
			.userMovieDownloadId(userMovieDownloadId)
			.userId(userId)
			.movieId(movieId)
			.build();
	}

	public static UserMovieDownloadEntity toEntity(UserMovieDownload domain) {
		return new UserMovieDownloadEntity(
			domain.getUserMovieDownloadId(),
			domain.getUserId(),
			domain.getMovieId()
		);
	}
}
