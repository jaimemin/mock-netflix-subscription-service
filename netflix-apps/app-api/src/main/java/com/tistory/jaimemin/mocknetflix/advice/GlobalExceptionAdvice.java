package com.tistory.jaimemin.mocknetflix.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tistory.jaimemin.mocknetflix.controller.NetflixApiResponse;
import com.tistory.jaimemin.mocknetflix.exception.ErrorCode;
import com.tistory.jaimemin.mocknetflix.exception.UserException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

	@ExceptionHandler(UserException.class)
	protected NetflixApiResponse<?> handleUserException(final UserException e) {
		log.error("error={}", e.getMessage(), e);

		return NetflixApiResponse.fail(e.getErrorCode(), e.getMessage());
	}

	@ExceptionHandler(RuntimeException.class)
	protected NetflixApiResponse<?> handleRuntimeException(final RuntimeException e) {
		log.error("error={}", e.getMessage(), e);

		return NetflixApiResponse.fail(ErrorCode.DEFAULT_ERROR, e.getMessage());
	}
}
