package com.tistory.jaimemin.mocknetflix.authentication;

import lombok.Getter;

@Getter
public class RequestedBy implements Authentication {

	private final String requestedBy;

	public RequestedBy(final String requestedBy) {
		this.requestedBy = requestedBy;
	}

	@Override
	public String getRequestedBy() {
		return requestedBy;
	}
}
