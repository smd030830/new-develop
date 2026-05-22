package com.mjc813.petapp.common;

import com.mjc813.petapp.pet.PetResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Throwable.class)
    public ResponseEntity<PetResponseDto> ExceptionHandler(Throwable e) {
		PetResponseDto result = new PetResponseDto(-999, "throwable error", e.getMessage());
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(result);
	}

	@ExceptionHandler(value = NoSuchElementException.class)
	public ResponseEntity<PetResponseDto> NoSuchElementExceptionHandler(NoSuchElementException e) {
		PetResponseDto result = new PetResponseDto(-977, "not found error", e.getMessage());
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(result);
	}

	@ExceptionHandler(value = NumberFormatException.class)
	public ResponseEntity<PetResponseDto> NumberFormatExceptionHandler(NumberFormatException e) {
		PetResponseDto result = new PetResponseDto(-912, "number format error", e.getMessage());
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(result);
	}

	@ExceptionHandler(value = NoHandlerFoundException.class)
	public ResponseEntity<PetResponseDto> NoHandlerFoundExceptionHandler(NoHandlerFoundException e) {
		PetResponseDto result = new PetResponseDto(-998, "not found url", e.getMessage());
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(result);
	}

	@ExceptionHandler(value = MyException.class)
	public ResponseEntity<PetResponseDto> MyExceptionHandler(MyException e) {
		PetResponseDto result = new PetResponseDto(-999, "data error", e.getData());
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(result);
	}
}
