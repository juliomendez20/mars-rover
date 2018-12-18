package com.mars.rover.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidPositionException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2335494775574948709L;

	public InvalidPositionException(String message) {
		super(message);
	}

}
