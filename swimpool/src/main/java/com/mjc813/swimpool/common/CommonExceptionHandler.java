package com.mjc813.swimpool.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<CommonResponseDto<String>> exceptionHandler(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
//			new CommonResponseDto<String>(CommonResponseCode.F0002
//					, CommonResponseCode.F0002.getMessage()
//					, ex.getMessage())
			CommonResponseDto.make(CommonResponseCode.F0002, ex.getMessage())
		);
	}
}
