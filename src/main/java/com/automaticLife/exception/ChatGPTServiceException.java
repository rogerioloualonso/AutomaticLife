package com.automaticLife.exception;

public class ChatGPTServiceException extends RuntimeException {

	private static final long serialVersionUID = 5058971974281806501L;

	public ChatGPTServiceException(Throwable cause) {
		super(cause);
	}
}
