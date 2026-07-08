package com.mjc813.jwtsecurity_login.conf;

import com.mjc813.jwtsecurity_login.models.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.security.autoconfigure.web.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class CWCWebSecurityConfig {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private MemberService memberService;

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider
				= new DaoAuthenticationProvider(memberService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		return daoAuthenticationProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	public CWCAuthenticationFilter cwcAuthenticationFilter() {
		return new CWCAuthenticationFilter();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConf = new CorsConfiguration();
		corsConf.setAllowedOriginPatterns(List.of("*"));
		corsConf.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PATH", "DELETE", "OPTIONS"));
		corsConf.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
		corsConf.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConf);
		return source;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf(AbstractHttpConfigurer::disable)
			.cors( x -> x.configurationSource(corsConfigurationSource()))
			.headers( x -> x
					// X-XSS-Protection 헤더
					.xssProtection(xss -> xss
							.headerValue(XXssProtectionHeaderWriter.HeaderValue.ENABLED_MODE_BLOCK)
					)
					// Content Security Policy
					.contentSecurityPolicy(csp -> csp
							.policyDirectives(
									"default-src 'self'; " +
									"script-src 'self'; " +
									"style-src 'self' 'unsafe-inline'; " +
									"img-src 'self' data:; " +
									"object-src 'none'; " +
									"frame-ancestors 'none';"
							)
					)
					.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
			)
			.authorizeHttpRequests( x -> x
					.requestMatchers("/").permitAll()
					.requestMatchers("/signup").permitAll()
					.requestMatchers("/signin").permitAll()
					.requestMatchers("/api/v1/auth/**").permitAll()
					.anyRequest().authenticated()
			)
			.sessionManagement(x ->
				x.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			)
			.authenticationProvider(daoAuthenticationProvider())
			.addFilterBefore(cwcAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
		;
		return http.build();
	}

	@Bean
	public WebSecurityCustomizer staticResourceCustomizer() {
		return x -> x.ignoring()
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}
}
