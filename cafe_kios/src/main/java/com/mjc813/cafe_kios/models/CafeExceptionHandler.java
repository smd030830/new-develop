package com.mjc813.cafe_kios.models;

import com.mjc813.cafe_kios.ResponseCode;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CafeExceptionHandler {

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<ApiResponse<String>> ThrowableHandler(Throwable ex) {
		ApiResponse<String> apiResponse = ApiResponse.<String>builder()
				.code(ResponseCode.ServerError)
				.message(ex.getMessage())
				.responseData(ex.toString())
				.build();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ApiResponse<String>> DataIntegrityViolationExceptionHandler(DataIntegrityViolationException ex) {
		ApiResponse<String> apiResponse = ApiResponse.<String>builder()
				.code(ResponseCode.ServerError)
				.message("Input data is not valid !!!")
				.responseData(null)
				.build();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
	}
}
