package com.tistory.jaimemin.mocknetflix.aspect;

import java.lang.reflect.Modifier;
import java.util.Arrays;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.tistory.jaimemin.mocknetflix.annotation.PasswordEncryption;

import lombok.RequiredArgsConstructor;

@Aspect
@Component
@RequiredArgsConstructor
public class PasswordEncryptionAspect {

	private final PasswordEncoder passwordEncoder;

	@Around("execution(* com.tistory.jaimemin.mocknetflix.controller..*.*(..))")
	public Object passwordEncryptionAspect(ProceedingJoinPoint pjp) throws Throwable {
		Arrays.stream(pjp.getArgs())
			.forEach(this::fieldEncryption);

		return pjp.proceed();
	}

	public void fieldEncryption(Object obj) {
		if (ObjectUtils.isEmpty(obj)) {
			return;
		}

		FieldUtils.getAllFieldsList(obj.getClass())
			.stream()
			.filter(filter -> !(Modifier.isFinal(filter.getModifiers()) || Modifier.isStatic(filter.getModifiers())))
			.forEach(field -> {
				try {
					boolean encryptionTarget = field.isAnnotationPresent(PasswordEncryption.class);

					if (!encryptionTarget) {
						return;
					}

					Object encryptionField = FieldUtils.readField(field, obj, true);

					if (!(encryptionField instanceof String)) {
						return;
					}

					String encrypted = passwordEncoder.encode((String)encryptionField);
					FieldUtils.writeField(field, obj, encrypted);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			});
	}
}
