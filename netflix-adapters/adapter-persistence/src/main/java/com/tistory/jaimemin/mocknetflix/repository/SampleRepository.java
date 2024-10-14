package com.tistory.jaimemin.mocknetflix.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tistory.jaimemin.mocknetflix.entity.SampleEntity;
import com.tistory.jaimemin.mocknetflix.sample.SamplePersistencePort;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SampleRepository implements SamplePersistencePort {

	private final SampleJpaRepository jpaRepository;

	@Override
	@Transactional
	public String getSampleName(String id) {
		SampleEntity sampleEntity = jpaRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Sample not found"));

		return sampleEntity.getSampleName();
	}
}
