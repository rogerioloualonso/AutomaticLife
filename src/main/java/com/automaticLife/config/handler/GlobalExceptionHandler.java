package com.automaticLife.config.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		StringBuilder strBuilder = new StringBuilder();

		e.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName;
			try {
				fieldName = ((FieldError) error).getField();

			} catch (ClassCastException ex) {
				fieldName = error.getObjectName();
			}
			String message = error.getDefaultMessage();
			strBuilder.append(String.format("%s: %s\n", fieldName, message));
		});

		// Montar objeto de erro: "Campos inválidos[campo1, campo2]

		return new ResponseEntity<>(strBuilder, HttpStatus.BAD_REQUEST);
	}

}