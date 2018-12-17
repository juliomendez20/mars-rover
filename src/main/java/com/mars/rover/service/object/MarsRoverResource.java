package com.mars.rover.service.object;

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
import com.mars.rover.service.dao.RoverDaoService;

@RestController
public class MarsRoverResource {

	@Autowired
	RoverDaoService roverDaoService;
	
	@GetMapping(path="/mars-rover/move/{command}")
	public Resource<Rover> getRoverPosition(@PathVariable String command){
		
		Rover rover = roverDaoService.getRover();
		
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
			throw new GridAlreadyCreatedException("Grid has been already created " + grid);
		}
		
		roverDaoService.createGrid(gridParam);
		
		Resource<Grid> resource = new Resource<Grid>(grid);
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
	
}
