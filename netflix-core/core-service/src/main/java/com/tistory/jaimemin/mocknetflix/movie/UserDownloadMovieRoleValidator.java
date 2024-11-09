package com.tistory.jaimemin.mocknetflix.movie;

public interface UserDownloadMovieRoleValidator {

	boolean validate(long count);

	boolean isTarget(String role);
}
