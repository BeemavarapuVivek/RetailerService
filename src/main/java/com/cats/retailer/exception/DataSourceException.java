package com.cats.retailer.exception;

public class DataSourceException extends RuntimeException{
	
	private String msg;

	public DataSourceException(String msg) {
		super();
		this.msg = msg;
	}
	
	
}
