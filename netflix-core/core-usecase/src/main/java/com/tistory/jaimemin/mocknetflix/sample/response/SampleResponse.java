package com.tistory.jaimemin.mocknetflix.sample.response;

import lombok.Getter;

@Getter
public class SampleResponse {

	private final String name;

	public SampleResponse(String name) {
		this.name = name;
	}
}