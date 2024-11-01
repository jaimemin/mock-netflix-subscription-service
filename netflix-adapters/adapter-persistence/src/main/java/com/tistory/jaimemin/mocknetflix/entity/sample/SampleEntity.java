package com.tistory.jaimemin.mocknetflix.entity.sample;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "sample")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SampleEntity {

	@Id
	@Column(name = "SAMPLE_ID")
	private String sample_id;

	@Column(name = "SAMPLE_NAME")
	private String sampleName;

	@Column(name = "SAMPLE_DESCRIPTION")
	private String sampleDescription;
}
