package com.mars.rover.service.object;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mars.rover.exceptions.InvalidPositionException;
import com.mars.rover.exceptions.ObstacleFoundException;
import com.mars.rover.exceptions.RoverInvalidMoveException;

public class Rover {

	static final char B = 'b', F = 'f', L ='l', R='r';
	@JsonIgnore
	private Grid grid;
	private Direction direction;
	private Position currentPosition;
	
	public Rover(Direction direction, Position currentPosition, Grid grid) throws InvalidPositionException, ObstacleFoundException{
		this.grid = grid;
		this.direction = direction;
		this.currentPosition = currentPosition;
		grid.isValidPosition(currentPosition);
	}
	
	public Rover() {}
	
	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	public Position getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(Position currentPosition) {
		this.currentPosition = currentPosition;
	}

	public void rotateLeft(){
		direction = direction.rotateLeft();
	}
	
	public void rotateRight(){
		direction = direction.rotateRight();
	}
	
	public void moveForward() throws ObstacleFoundException, InvalidPositionException{
		currentPosition = getNextPosition();	
	}
	
	public void moveBackward() throws ObstacleFoundException, InvalidPositionException{
		currentPosition = getPreviousPosition();
	}
	
	@JsonIgnore
	public Position getNextPosition() throws ObstacleFoundException, InvalidPositionException {
		Position nextPosition = new Position(currentPosition.getxHorizontal() + direction.getXHorizontal(),
											currentPosition.getyVertical() + direction.getYVertical());
		
		return grid.getWrappedPosition(nextPosition);
	}
	
	@JsonIgnore
	public Position getPreviousPosition() throws ObstacleFoundException, InvalidPositionException {
		Position previousPosition = new Position(currentPosition.getxHorizontal() - direction.getXHorizontal(),
												currentPosition.getyVertical() - direction.getYVertical());
		return grid.getWrappedPosition(previousPosition);
	}
	//TO Execute the actions sequencially 
	public void move(String actions) throws InvalidPositionException, ObstacleFoundException,RoverInvalidMoveException{
		
		char[] actionsArray = actions.toCharArray();
		
		for (int i=0; i<actionsArray.length ; i++){
			switch (actionsArray[i]) {
			case F:
				moveForward();
				break;
			case B:
				moveBackward();
				break;
			case L:
				rotateLeft();
				break;
			case R:
				rotateRight();
				break;
			default:
				throw new RoverInvalidMoveException("Move not recognized. Command = "+actionsArray[i]);
			}
		}
	}
	
	@Override
	public String toString(){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("Rover Current Position: ")
			.append(currentPosition)
			.append(" Rover Direction ")
			.append(direction);
		
		
		return sb.toString();
		
	}
}

