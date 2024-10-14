package com.tistory.jaimemin.mocknetflix.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tistory.jaimemin.mocknetflix.entity.EntityModule;
import com.tistory.jaimemin.mocknetflix.repository.RepositoryModule;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Configuration
@EntityScan(basePackageClasses = {EntityModule.class})
@EnableJpaRepositories(basePackageClasses = {RepositoryModule.class})
public class PersistenceJpaConfig {

	@PersistenceContext
	public EntityManager entityManager;

	@Bean
	public JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
		return new JPAQueryFactory(entityManager);
	}
}
