package com.automaticLife.exception;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5206063429711695567L;

	public ObjectNotFoundException(Throwable cause) {
		super(cause);
	}

	public ObjectNotFoundException(String objeto) {
		super(objeto);
	}
}
