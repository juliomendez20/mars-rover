package com.mars.rover.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoverAtPositionException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 571438989878617586L;

	public RoverAtPositionException(String message) {
		super(message);
	}

}
