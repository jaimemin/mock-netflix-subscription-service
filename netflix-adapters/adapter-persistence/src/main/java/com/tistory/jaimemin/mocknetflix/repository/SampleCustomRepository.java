package com.tistory.jaimemin.mocknetflix.repository;

import java.util.List;

import com.tistory.jaimemin.mocknetflix.entity.SampleEntity;

public interface SampleCustomRepository {

	List<SampleEntity> findAll();
}
