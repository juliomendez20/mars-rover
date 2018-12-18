package com.mars.rover.service.object;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mars.rover.exceptions.GridAlreadyCreatedException;
import com.mars.rover.exceptions.GridNotYetCreatedException;
import com.mars.rover.exceptions.ObstacleFoundException;
import com.mars.rover.exceptions.RoverInvalidMoveException;
import com.mars.rover.exceptions.RoverNotFoundException;
import com.mars.rover.service.dao.RoverDaoService;

@RestController
public class MarsRoverResource {

	@Autowired
	RoverDaoService roverDaoService;
	
	@GetMapping(path="/mars-rover/rover")
	public Resource<Rover> getRoverPosition(){
		
		Rover rover = roverDaoService.getRover();
		if(null == rover)
			throw new RoverNotFoundException("Rover has not been created.");
		
		Resource<Rover> resource = new Resource<Rover>(rover);
		return resource;
	}
	
	@PostMapping(path="/mars-rover/rover/move/{command}")
	public Resource<Rover> moveRover(@PathVariable String command){
		
		Rover rover = roverDaoService.getRover();
		if(null == rover)
			throw new RoverNotFoundException("Rover has not been created.");
		try {
			rover.move(command);
		}catch(ObstacleFoundException e) {
			throw e;
		}catch (Exception e) {
			
			throw new RoverInvalidMoveException("Rover Invalid Move Exception");
		}
		
		Resource<Rover> resource = new Resource<Rover>(rover);
		return resource;
	}
	
	@PostMapping(path="/mars-rover/grid")
	public Resource<Grid> createGrid(@RequestBody Grid gridParam){
		
		Grid grid = roverDaoService.getGrid();
		
		if(null != grid) {
			throw new GridAlreadyCreatedException("Grid has been already created with size (" + grid.getxHorizontalMax() + ","+ grid.getyVerticalMax()+")");
		}
		
		roverDaoService.createGrid(gridParam);
		
		grid = roverDaoService.getGrid();
		Resource<Grid> resource = new Resource<Grid>(grid);
		return resource;
	}
	
	@PostMapping(path="/mars-rover/rover")
	public Resource<Rover> createRover(@RequestBody Rover rover){
		
		Grid grid = roverDaoService.getGrid();
		
		if(null == grid) {
			throw new GridNotYetCreatedException("Grid has not been created. Create Grig before Rover.");
		}
		
		roverDaoService.createRover(rover);
		
		Resource<Rover> resource = new Resource<Rover>(rover);
		return resource;
	}
	
	@GetMapping(path="/mars-rover/grid")
	public Grid getGrid(){
		
		Grid grid = roverDaoService.getGrid();
		
		if(null == grid) {
			throw new GridNotYetCreatedException("The grid has not been created yet.");
		}
		
		//Resource<Grid> resource = new Resource<Grid>(grid);
		return grid;
	}
	
	@GetMapping(path="/mars-rover/grid/obstacles")
	public List<Obstacle> getObstacles(){
		
		Grid grid = roverDaoService.getGrid();
		
		if(null == grid) {
			throw new GridNotYetCreatedException("The grid has not been created yet.");
		}
		
		//Resource<Grid> resource = new Resource<Grid>(grid);
		return grid.getObstacles();
	}
	
	
}
