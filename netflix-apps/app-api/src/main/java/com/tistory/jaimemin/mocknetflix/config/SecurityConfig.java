package com.tistory.jaimemin.mocknetflix.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.tistory.jaimemin.mocknetflix.security.NetflixUserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final NetflixUserDetailsService userDetailsService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.httpBasic(AbstractHttpConfigurer::disable);
		httpSecurity.formLogin(AbstractHttpConfigurer::disable);
		httpSecurity.csrf(AbstractHttpConfigurer::disable);
		httpSecurity.cors(cors -> cors.configurationSource(corsConfigurationSource()));
		httpSecurity.userDetailsService(userDetailsService);
		httpSecurity.authorizeHttpRequests(auth ->
			auth.requestMatchers("/api/v1/user/register", "/api/v1/user/login").permitAll()
				.anyRequest().authenticated()
		);
		// httpSecurity.oauth2Login(oauth2 -> oauth2.failureUrl("/login?error=true"));

		return httpSecurity.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	private CorsConfigurationSource corsConfigurationSource() {
		return request -> {
			CorsConfiguration configuration = new CorsConfiguration();
			configuration.setAllowedHeaders(Collections.singletonList("*"));
			configuration.setAllowedMethods(Collections.singletonList("*"));
			configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
			configuration.setAllowCredentials(true);

			return configuration;
		};
	}
}
