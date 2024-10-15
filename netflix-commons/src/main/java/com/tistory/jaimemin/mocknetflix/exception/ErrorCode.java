package com.tistory.jaimemin.mocknetflix.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

	DEFAULT_ERROR("NFX0000", "에러가 발생했습니다."),
	USER_DOES_NOT_EXIST("NFX2000", "사용자가 존재하지 않습니다."),
	USER_ALREADY_EXIST("NFX2001", "사용자가 이미 존재합니다.");

	private final String code;

	private final String message;

	ErrorCode(final String code, final String message) {
		this.code = code;
		this.message = message;
	}

	@Override
	public String toString() {
		return "[" + code + "] " + message;
	}
}
