package com.automaticLife.config.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

	private Error error;

	public static ErrorResponse createErrorResponse(String type, String description) {

		return new ErrorResponse(new Error(type, description));
	}

}
