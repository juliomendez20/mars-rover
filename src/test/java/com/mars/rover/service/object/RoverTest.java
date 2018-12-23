package com.mars.rover.service.object;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.mars.rover.exceptions.RoverInvalidMoveException;

public class RoverTest {

	Rover rover;
	Grid grid;
	Position position;
	
	@Before
	public void setUp() {
		grid = new Grid(5,7);
		position = new Position(0,0);
		rover = new Rover(Direction.N,position,grid);
	}
	
	@Test
	public void testRotateLeft() {
		rover.rotateLeft();
		assertEquals(rover.getDirection(),Direction.W);
	}
	
	@Test
	public void testRotateRight() {
		rover.rotateRight();
		assertEquals(rover.getDirection(),Direction.E);
	}
	
	@Test
	public void testGetNextPosition() {
		Position nextPosition = new Position(0,1);
		assertEquals(rover.getNextPosition(),nextPosition);
	}
	
	@Test
	public void testGetPreviousPosition() {
		Position previousPosition = new Position(0,6);
		assertEquals(rover.getPreviousPosition(),previousPosition);
	}
	
	@Test
	public void testMoveForward() {
		Position nextPosition = rover.getNextPosition();
		rover.moveForward();
		assertEquals(rover.getCurrentPosition(),nextPosition);
	}

	@Test
	public void testMoveBackward() {
		Position previousPosition = rover.getPreviousPosition();
		rover.moveBackward();
		assertEquals(rover.getCurrentPosition(),previousPosition);
	}
	
	@Test(expected=RoverInvalidMoveException.class)
	public void testMove_RoverInvalidMoveException() {
		rover.move("ffflbbrffx");
	}
	
	@Test()
	public void testMove_ok() {
		rover.move("ffrfflbbb");
		assertEquals(rover.getCurrentPosition(),new Position(2,6));
	}

}
