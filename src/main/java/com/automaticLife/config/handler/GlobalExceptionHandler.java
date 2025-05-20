package com.automaticLife.config.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.automaticLife.config.response.ErrorResponse;
import com.automaticLife.exception.ChatGPTServiceException;
import com.automaticLife.exception.ObjectNotFoundException;
import com.automaticLife.exception.TwilioServiceException;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

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

		logger.error("Incorrect field(s): {}.", listError.toString());
		finishedLog();
		return new ResponseEntity<>(
				ErrorResponse.createErrorResponse("ValidationException", "Incorrect field(s): " + listError.toString()),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(TwilioServiceException.class)
	public ResponseEntity<Object> handleMethodServiceTwilioException(TwilioServiceException e) {

		logger.error("Twilio Service unavailable.");
		finishedLog();
		return new ResponseEntity<>(ErrorResponse.createErrorResponse("TwilioServiceException", "Service unavailable"),
				HttpStatus.SERVICE_UNAVAILABLE);
	}

	@ExceptionHandler(ChatGPTServiceException.class)
	public ResponseEntity<Object> handleMethodServiceChatGPTException(ChatGPTServiceException e) {

		logger.error("ChatGPT Service unavailable.");
		finishedLog();
		return new ResponseEntity<>(ErrorResponse.createErrorResponse("ChatGPTServiceException", "Service unavailable"),
				HttpStatus.SERVICE_UNAVAILABLE);
	}

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<Object> handleMethodObjectNotFoundException(ObjectNotFoundException e) {

		logger.error("{} not found by id.", e.getMessage());
		finishedLog();
		return new ResponseEntity<>(
				ErrorResponse.createErrorResponse("ObjectNotFoundException", e.getMessage() + " not found by id."),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Object> handleMethodRuntimeException(RuntimeException e) {

		logger.error("Unknow exception occurred.");
		finishedLog();
		return new ResponseEntity<>(ErrorResponse.createErrorResponse("RuntimeException", "Unknow exception occurred."),
				HttpStatus.SERVICE_UNAVAILABLE);
	}

	private void finishedLog() {
		logger.info("Process finished with error.");
	}

}