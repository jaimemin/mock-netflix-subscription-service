package com.tistory.jaimemin.mocknetflix.repository.sample;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tistory.jaimemin.mocknetflix.entity.sample.SampleEntity;

public interface SampleJpaRepository extends JpaRepository<SampleEntity, String>, SampleCustomRepository {
}
