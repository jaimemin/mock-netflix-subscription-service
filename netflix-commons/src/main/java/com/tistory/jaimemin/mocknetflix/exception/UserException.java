package com.tistory.jaimemin.mocknetflix.exception;

import lombok.Getter;

@Getter
public class UserException extends RuntimeException {

	private final ErrorCode errorCode;

	public UserException(final ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public static class UserDoesNotExistException extends UserException {

		public UserDoesNotExistException() {
			super(ErrorCode.USER_DOES_NOT_EXIST);
		}
	}

	public static class UserAlreadyExistsException extends UserException {

		public UserAlreadyExistsException() {
			super(ErrorCode.USER_ALREADY_EXIST);
		}
	}
}
