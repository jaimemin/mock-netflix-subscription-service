package com.tistory.jaimemin.mocknetflix.redis;

import java.time.Duration;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RedisRepository {

	private final RedisTemplate<String, String> redisTemplate;

	public String getValue(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	public void setValue(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
	}

	public void setValueTtl(String key, String value, Duration ttl) {
		redisTemplate.opsForValue().set(key, value, ttl);
	}
}
