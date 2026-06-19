package com.mjc813.jwtsecurity_login.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ComResponseDto<String>> exceptionHandler(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
			ComResponseDto.make(ResponseCode.SERVER_ERROR, ex.getMessage())
		);
	}

	@ExceptionHandler(LoginException.class)
	public ResponseEntity<ComResponseDto<String>> exceptionHandler(LoginException ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
				ComResponseDto.make(ResponseCode.AUTHENTICATION_ERROR, ex.getMessage())
		);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ComResponseDto<String>> exceptionHandler(BadCredentialsException ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
				ComResponseDto.make(ResponseCode.AUTHENTICATION_ERROR, ex.getMessage())
		);
	}
}
