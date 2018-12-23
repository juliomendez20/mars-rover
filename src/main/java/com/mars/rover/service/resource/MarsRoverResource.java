package com.mars.rover.service.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mars.rover.exceptions.GridAlreadyCreatedException;
import com.mars.rover.exceptions.GridNotYetCreatedException;
import com.mars.rover.exceptions.RoverAlreadyCreatedException;
import com.mars.rover.exceptions.RoverAtPositionException;
import com.mars.rover.exceptions.RoverNotFoundException;
import com.mars.rover.service.dao.RoverDaoService;
import com.mars.rover.service.object.Grid;
import com.mars.rover.service.object.Obstacle;
import com.mars.rover.service.object.Rover;

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
		
		rover.move(command);
		
		Resource<Rover> resource = new Resource<Rover>(rover);
		return resource;
	}

	
	@PostMapping(path="/mars-rover/grid")
	public ResponseEntity<Object> createGrid(@Valid @RequestBody Grid gridParam){
		
		Grid grid = roverDaoService.getGrid();
		
		if(null != grid) {
			throw new GridAlreadyCreatedException("Grid has been already created with size (" + grid.getxHorizontalMax() + ","+ grid.getyVerticalMax()+")");
		}
		
		roverDaoService.createGrid(gridParam);
		
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().build()
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PostMapping(path="/mars-rover/rover")
	public ResponseEntity<Object> createRover(@RequestBody Rover rover){
		
		Grid grid = roverDaoService.getGrid();
		
		if(null == grid) {
			throw new GridNotYetCreatedException("Cannot create rover. Grid has not been created.");
		}
		
		if(null != roverDaoService.getRover()) {
			throw new RoverAlreadyCreatedException("Rover has been alreade created. "+ roverDaoService.getRover());
		}
		
		roverDaoService.createRover(rover);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().build()
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PostMapping(path="/mars-rover/grid/obstacles")
	public ResponseEntity<Object> addObstacle(@RequestBody Obstacle obstacle){
		
		Grid grid = roverDaoService.getGrid();
		Rover rover = roverDaoService.getRover();
		if(null == grid) {
			throw new GridNotYetCreatedException("Cannot add Obstacle. Grid has not been created.");
		}
		
		if(null != rover && obstacle.getPosition().equals(rover.getCurrentPosition())) {
			throw new RoverAtPositionException("Cannot add obstacle. Rover is in that position.");
		}
		
		roverDaoService.addObstacle(obstacle);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().build()
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping(path="/mars-rover/grid")
	public Resource<Grid> getGrid(){
		
		Grid grid = roverDaoService.getGrid();
		
		if(null == grid) {
			throw new GridNotYetCreatedException("The grid has not been created yet.");
		}
		
		Resource<Grid> resource = new Resource<Grid>(grid);
		
		return resource;
	}
	
	@GetMapping(path="/mars-rover/grid/obstacles")
	public List<Obstacle> getObstacles(){
		
		Grid grid = roverDaoService.getGrid();
		
		if(null == grid) {
			throw new GridNotYetCreatedException("The grid has not been created yet.");
		}
		
		return grid.getObstacles();
	}
	
	
}
