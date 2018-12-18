package com.mars.rover.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class RoverAlreadyCreatedException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1431600659208145447L;
	
	public RoverAlreadyCreatedException(String message) {
		super(message);
	}

}
