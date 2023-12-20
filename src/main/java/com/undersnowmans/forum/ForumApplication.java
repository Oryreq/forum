package com.undersnowmans.forum;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import com.undersnowmans.forum.config.JwtAuthenticationConfigurer;
import com.undersnowmans.forum.utils.AccessTokenJwsStringSerializer;
import com.undersnowmans.forum.utils.RefreshTokenJweStringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import java.text.ParseException;

@SpringBootApplication
public class ForumApplication {

	@Bean
	public JwtAuthenticationConfigurer jwtAuthenticationConfigurer(@Value("${jwt.access-token-key}") String accessTokenKey,
																   @Value("${jwt.refresh-token-key") String refreshTokenKey
	) throws ParseException, JOSEException {
		return new JwtAuthenticationConfigurer()
				.accessTokenStringSerializer(new AccessTokenJwsStringSerializer(
						new MACSigner(OctetSequenceKey.parse(accessTokenKey))
				))
				.refreshTokenStringSerializer(new RefreshTokenJweStringSerializer(
						new DirectEncrypter(OctetSequenceKey.parse(refreshTokenKey))
				));
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http,
												   JwtAuthenticationConfigurer jwtAuthenticationConfigurer
	) throws Exception {
		http.apply(jwtAuthenticationConfigurer);

		return http
				.httpBasic(Customizer.withDefaults())
				.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(authorizeHttpRequests ->
						authorizeHttpRequests
								.requestMatchers("/thread/**").hasRole("ADMIN")
								.requestMatchers("/auth/**").permitAll()
								.anyRequest().authenticated())
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
	}

}
