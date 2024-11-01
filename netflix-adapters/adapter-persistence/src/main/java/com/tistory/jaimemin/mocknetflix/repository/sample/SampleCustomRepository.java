package com.tistory.jaimemin.mocknetflix.repository.sample;

import java.util.List;

import com.tistory.jaimemin.mocknetflix.entity.sample.SampleEntity;

public interface SampleCustomRepository {

	List<SampleEntity> findAll();
}
