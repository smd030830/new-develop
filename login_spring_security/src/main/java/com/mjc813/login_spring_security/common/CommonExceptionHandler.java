package com.mjc813.login_spring_security.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@ExceptionHandler(AuthorizedException.class)
	public ResponseEntity<ComResponseDto<String>> exceptionHandler(AuthorizedException ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
				ComResponseDto.make(ResponseCode.AUTHORIZATION_ERROR, ex.getMessage())
		);
	}

	@ExceptionHandler(Mjc813Exception.class)
	public ResponseEntity<ComResponseDto<String>> exceptionHandler(Mjc813Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
				ComResponseDto.make(ex.getCode(), ex.getMessage())
		);
	}
}
