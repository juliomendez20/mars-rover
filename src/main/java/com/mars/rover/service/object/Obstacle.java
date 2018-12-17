package com.mars.rover.service.object;

public class Obstacle {

	Position position;
	
	protected Obstacle() {
	}
	
	public Obstacle(Position position) {
		super();
		this.position = position;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	
	
}
