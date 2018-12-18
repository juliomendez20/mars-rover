package com.mars.rover.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mars.rover.service.object.Obstacle;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ObstacleFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4068675827229260138L;

	private Obstacle obstacle;
	
	public ObstacleFoundException(String message, Obstacle obstacle) {
		super(message);
		this.obstacle = obstacle;
	}

	public Obstacle getObstacle() {
		return obstacle;
	}
	
}
