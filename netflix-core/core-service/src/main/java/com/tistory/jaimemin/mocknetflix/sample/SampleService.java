package com.tistory.jaimemin.mocknetflix.sample;

import org.springframework.stereotype.Service;

import com.tistory.jaimemin.mocknetflix.sample.response.SampleResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SampleService implements SearchSampleUseCase {

	private final SamplePort samplePort;

	private final SamplePersistencePort samplePersistencePort;

	@Override
	public SampleResponse getSample() {
		SamplePortResponse sample = samplePort.getSample();
		String sampleName = samplePersistencePort.getSampleName("1");

		return new SampleResponse(sample.getName());
	}
}
