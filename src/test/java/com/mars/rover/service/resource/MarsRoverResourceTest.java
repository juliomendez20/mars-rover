package com.mars.rover.service.resource;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mars.rover.service.dao.RoverDaoService;
import com.mars.rover.service.object.Direction;
import com.mars.rover.service.object.Grid;
import com.mars.rover.service.object.Obstacle;
import com.mars.rover.service.object.Position;
import com.mars.rover.service.object.Rover;

@RunWith(SpringRunner.class)
@WebMvcTest(value=MarsRoverResource.class)
public class MarsRoverResourceTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	RoverDaoService roverServiceDao;
	
	Grid grid;
	Rover rover;
	Obstacle obstacle;
	
	@Before
	public void setUp() {
		
		grid = new Grid(5,7);
		rover = new Rover(Direction.N,new Position(0,0),grid);
		obstacle = new Obstacle(new Position(0,0));
	}
	
	
	@Test
	public void createGridTestSuccess() throws Exception {
		
		when(roverServiceDao.getGrid()).thenReturn(null);
		
	   String uri = "/mars-rover/grid";
	   String inputJson = mapToJson(grid);
	   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
	      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(201, status);
	}
	
	@Test
	public void createGridTest_MethodArgumentNotValidException() throws Exception {
		
		Grid grid = new Grid(1,1);
		
	   String uri = "/mars-rover/grid";
	   String inputJson = mapToJson(grid);
	   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
	      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(400, status);
	}
	
	@Test
	public void addObstacleTestSuccess() throws Exception {
		
		when(roverServiceDao.getGrid()).thenReturn(grid);
		
	   String uri = "/mars-rover/grid/obstacles";
	   String inputJson = mapToJson(obstacle);
	   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
	      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(201, status);
	}
	
	
	
	@Test
	public void addObstacleTest_GridNotYetCreatedException() throws Exception {
				
		when(roverServiceDao.getGrid()).thenReturn(null);
		
	   String uri = "/mars-rover/grid/obstacles";
	   String inputJson = mapToJson(obstacle);
	   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
	      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(404, status);
	}
	
	@Test
	public void addObstacleTest_RoverAtPositionFound() throws Exception {
				
		when(roverServiceDao.getGrid()).thenReturn(grid);
		when(roverServiceDao.getRover()).thenReturn(rover);

	   String uri = "/mars-rover/grid/obstacles";
	   String inputJson = mapToJson(obstacle);
	   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
	      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(404, status);
	}
	
	@Test
	public void createRoverTestSuccess() throws Exception {
		
		when(roverServiceDao.getGrid()).thenReturn(grid);
		when(roverServiceDao.getRover()).thenReturn(null);
		
	   String uri = "/mars-rover/rover";
	   String inputJson = mapToJson(rover);
	   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
	      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(201, status);
		
	}
	
	
	protected String mapToJson(Object obj) throws JsonProcessingException {
	      ObjectMapper objectMapper = new ObjectMapper();
	      return objectMapper.writeValueAsString(obj);
	   }
	
	protected <T> T mapFromJson(String json, Class<T> clazz)
	    throws JsonParseException, JsonMappingException, IOException {
	      
	   ObjectMapper objectMapper = new ObjectMapper();
	   return objectMapper.readValue(json, clazz);
	   }
}
