package com.cats.retailer.exception;

public class DataSourceException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	private final String message;

	public DataSourceException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
	
	
}
