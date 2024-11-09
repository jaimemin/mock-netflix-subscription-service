package com.tistory.jaimemin.mocknetflix.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tistory.jaimemin.mocknetflix.user.LogUserAuditHistoryUseCase;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserHistoryLoggingFilter extends OncePerRequestFilter {

	private final LogUserAuditHistoryUseCase useCase;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		CompletableFuture.runAsync(() -> log(authentication, request));

		filterChain.doFilter(request, response);
	}

	public void log(Authentication authentication, HttpServletRequest request) {
		useCase.log(authentication.getName(),
			authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(
				Collectors.joining(",")), request.getRemoteAddr(), request.getMethod(), request.getRequestURI(),
			getHeaders(request), "payload");
	}

	private String getHeaders(HttpServletRequest request) {
		Map<String, String> headersMap = new HashMap<>();
		Enumeration<String> headerNames = request.getHeaderNames();

		if (headerNames != null) {
			while (headerNames.hasMoreElements()) {
				String headerName = headerNames.nextElement();
				String headerValue = request.getHeader(headerName);

				headersMap.put(headerName, headerValue);
			}
		}

		ObjectMapper objectMapper = new ObjectMapper();

		try {
			return objectMapper.writeValueAsString(headersMap);
		} catch (JsonProcessingException e) {
			return "{}";
		}
	}
}
