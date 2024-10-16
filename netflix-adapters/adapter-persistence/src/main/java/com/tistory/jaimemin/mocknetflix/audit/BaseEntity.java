package com.tistory.jaimemin.mocknetflix.audit;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

	@CreatedDate
	@Column(name = "CREATED_AT", updatable = false)
	private LocalDateTime createdAt;

	@CreatedBy
	@Column(name = "CREATED_BY", updatable = false)
	private String createdBy;
}