package com.mjc813.cafe_kiosk.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class CafeResponseException {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<String>> handleException(Exception e) {
		ApiResponse<String> apiResponse = ApiResponse.make(CafeResponse.other_failed, "error", e.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ApiResponse<String>> handleNoSuchElementException(NoSuchElementException e) {
		ApiResponse<String> apiResponse = ApiResponse.make(CafeResponse.found_failed, "data not exist", e.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
	}
}
