package com.mars.rover.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomizedResponseEntittyExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions
	(Exception ex, WebRequest request) throws Exception {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), null,ex.getMessage(), request.getDescription(true));
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		return responseEntity;
	}
	
	@ExceptionHandler(ObstacleFoundException.class)
	public final ResponseEntity<Object> handleObstacleFoundException
	(ObstacleFoundException ex, WebRequest request) throws Exception {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getObstacle(), ex.getMessage(), request.getDescription(true));
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
		return responseEntity;
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),null, ex.getMessage(), ex.getBindingResult().toString());
		
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);

		return responseEntity;
	}
}
