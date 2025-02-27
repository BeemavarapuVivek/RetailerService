package com.cats.retailer.exception;

public class NoSuchRecordAvailable extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	
	public NoSuchRecordAvailable(String msg) {
		super();
		this.message = msg;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}

	
	
	
	
}
