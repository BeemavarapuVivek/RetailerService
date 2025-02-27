package com.cats.retailer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;



@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = NoSuchRecordAvailable.class)
	public ResponseEntity<ErrorResponse> handleNoSuchRecordException(NoSuchRecordAvailable ex){
		
		return ResponseEntity.ok(new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
	}
	
	@ExceptionHandler(DataSourceException.class)
	@ResponseStatus(value=HttpStatus.BAD_GATEWAY)
	public ErrorResponse handleException(DataSourceException dse,WebRequest req){
		return new ErrorResponse( HttpStatus.BAD_GATEWAY.value(),dse.getMessage());
	}
}
