package com.cats.retailer.exception;

public class ResouceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String message;

	public ResouceNotFoundException(String msg) {
		super();
		this.message = msg;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
