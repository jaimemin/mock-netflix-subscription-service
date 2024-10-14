package com.tistory.jaimemin.mocknetflix.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tistory.jaimemin.mocknetflix.entity.QSampleEntity;
import com.tistory.jaimemin.mocknetflix.entity.SampleEntity;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SampleCustomRepositoryImpl implements SampleCustomRepository {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<SampleEntity> findAll() {
		return jpaQueryFactory.selectFrom(QSampleEntity.sampleEntity).fetch();
	}
}
