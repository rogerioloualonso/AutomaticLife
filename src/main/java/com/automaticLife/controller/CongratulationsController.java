package com.automaticLife.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Congratulations", description = "Operations for send congratulations")
public interface CongratulationsController {

	@ApiOperation(value = "Send congratulatios to people that make birthday today")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Congratulations send!"),
			@ApiResponse(responseCode = "204", description = "Not birthday today."),
			@ApiResponse(responseCode = "500", description = "Sorry, bad execution...")
			})
	@RequestMapping(value = "/congratulations", method = RequestMethod.POST)
	public ResponseEntity<Void> sendCongratulations();
}