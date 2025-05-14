package com.automaticLife.config.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.automaticLife.config.response.Error;
import com.automaticLife.config.response.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		List<String> listError = new ArrayList<String>();

		e.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName;
			try {
				fieldName = ((FieldError) error).getField();
			} catch (ClassCastException ex) {
				fieldName = error.getObjectName();
			}
			listError.add(fieldName);
		});

		ErrorResponse error = new ErrorResponse(
				new Error("ValidationException", "Incorrect field(s): " + listError.toString()));

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}