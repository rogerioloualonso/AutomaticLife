package com.automaticLife.exception;

public class TwilioServiceException extends RuntimeException {

	private static final long serialVersionUID = -7789599950513487794L;

	public TwilioServiceException(Throwable cause) {
		super(cause);
	}
}
