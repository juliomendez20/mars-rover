package com.mars.rover.service.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mars.rover.exceptions.InvalidPositionException;
import com.mars.rover.exceptions.ObstacleFoundException;
import com.mars.rover.service.object.Grid;
import com.mars.rover.service.object.Obstacle;
import com.mars.rover.service.object.Rover;

@Component
public class RoverDaoService {

	private static Rover rover;
	private static Grid grid;
	
	public List<Obstacle> findAllObstacles(){
		return grid.getObstacles();
	}
	
	public void addObstacle(Obstacle obstacle) throws InvalidPositionException, ObstacleFoundException{
		grid.addObstacle(obstacle);
	}
	
	public Rover getRover() {
		return rover;
	}
	
	public Grid getGrid() {
		return grid;
	}

	public void createGrid(Grid gridParam) {
		grid = gridParam;
		grid.initializedGridObstacle();
	}
	
	public void createRover(Rover roverParam) throws ObstacleFoundException, InvalidPositionException {
		rover = new Rover(roverParam.getDirection(), roverParam.getCurrentPosition(), grid);
	}
}
