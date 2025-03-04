package com.cats.retailer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;



@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = ResouceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNoSuchRecordException(ResouceNotFoundException ex,WebRequest req){
		
		return ResponseEntity.ok(new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
	}
	
	@ExceptionHandler(value= BadRequestException.class)
	public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex,WebRequest req) {
		return ResponseEntity.ok(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));	
	}
	
	@ExceptionHandler(DataSourceException.class)
	public ResponseEntity<ErrorResponse> handleException(DataSourceException dse,WebRequest req){
		return ResponseEntity.ok(new ErrorResponse( HttpStatus.BAD_GATEWAY.value(),dse.getMessage()));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleInternalServerException(Exception e){
		return ResponseEntity.ok(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unexpected error occured::"+e.getMessage()));
	}
	
}
