package com.mars.rover.service.object;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.mars.rover.exceptions.InvalidPositionException;
import com.mars.rover.exceptions.ObstacleFoundException;

public class GridTest {

	static Grid grid = null;
	
	@Before
	public void setUp() {
		grid = new Grid(5,7);
		grid.addObstacle(new Obstacle(new Position(2,2)));
	}
	
	@Test
	public void testIsValidPosition_ok() {
		Position position = new Position(3,3);
		assertTrue(grid.isValidPosition(position));		
	}
	
	@Test(expected=InvalidPositionException.class)
	public void testIsValidPosition_InvalidPositionException() {
		Position position = new Position(grid.getxHorizontalMax(),grid.getyVerticalMax());
		grid.isValidPosition(position);		
	}
	
	@Test(expected=ObstacleFoundException.class)
	public void testIsValidPosition_ObjectFoundException() {
		Position position = new Position(2,2);
		grid.isValidPosition(position);		
	}
	
	@Test
	public void testAddObstacle_ok() {
		Obstacle obstacle = new Obstacle(new Position(2,6));
		grid.addObstacle(obstacle);
		assertNotNull(grid.getGridObstacle()[2][6]);
	}
	
	@Test(expected=InvalidPositionException.class)
	public void testAddObstacle_InvalidPositionException(){
		Obstacle obstacle = new Obstacle(new Position(grid.getxHorizontalMax(),grid.getyVerticalMax()));
		grid.addObstacle(obstacle);	
	}
	
	@Test(expected=ObstacleFoundException.class)
	public void testAddObstacle_ObstacleFoundException(){
		Obstacle obstacle = new Obstacle(new Position(2,2));
		grid.addObstacle(obstacle);
		
	}
	
	@Test
	public void testGetWrappedPosition() {
		Position position = new Position(grid.getxHorizontalMax(),grid.getyVerticalMax());
		Position wrappedPosition = grid.getWrappedPosition(position);
		
		assertEquals(wrappedPosition, new Position(0,0));
	}

}
