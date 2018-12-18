package com.mars.rover.service.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mars.rover.exceptions.InvalidPositionException;
import com.mars.rover.exceptions.ObstacleFoundException;
import com.mars.rover.service.object.Direction;
import com.mars.rover.service.object.Grid;
import com.mars.rover.service.object.Obstacle;
import com.mars.rover.service.object.Position;
import com.mars.rover.service.object.Rover;

@Component
public class RoverDaoService {

	private static Rover rover;
	private static Grid grid;
	static {
		grid = new Grid(5,5);
		
		try {
			grid.addObstacle(new Obstacle(new Position(3,3)));
			grid.addObstacle(new Obstacle(new Position(3,4)));
			//rover = new Rover(Direction.N, new Position(0, 0), grid);
			
		} catch (ObstacleFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public List<Obstacle> findAllObstacles(){
		return grid.getObstacles();
	}
	
	public Rover getRover() {
		return rover;
	}
	
	public Grid getGrid() {
		return grid;
	}

	public void createGrid(Grid gridParam) {
		grid = gridParam;
	}
	
	public void createRover(Rover rover) throws ObstacleFoundException, InvalidPositionException {
		rover = new Rover(rover.getDirection(), rover.getCurrentPosition(), getGrid());
	}
}
