package com.tistory.jaimemin.mocknetflix.controller;

import com.tistory.jaimemin.mocknetflix.exception.ErrorCode;

public record NetflixApiResponse<T>(
	boolean success,
	String code,
	String message,
	T data
) {
	public static final String CODE_SUCCEED = "SUCCEED";

	public static <T> NetflixApiResponse<T> ok(T data) {
		return new NetflixApiResponse<>(true, CODE_SUCCEED, null, data);
	}

	public static <T> NetflixApiResponse<T> fail(ErrorCode errorCode, String message) {
		return new NetflixApiResponse<>(false, errorCode.getCode(), message, null);
	}
}
