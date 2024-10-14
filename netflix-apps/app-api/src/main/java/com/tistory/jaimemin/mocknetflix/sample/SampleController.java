package com.tistory.jaimemin.mocknetflix.sample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tistory.jaimemin.mocknetflix.sample.response.SampleResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SampleController {

	private final SearchSampleUseCase searchSampleUseCase;

	@GetMapping("/api/v1/sample")
	public SampleResponse sample() {
		return searchSampleUseCase.getSample();
	}
}
