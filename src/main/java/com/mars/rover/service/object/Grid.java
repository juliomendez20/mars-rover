package com.mars.rover.service.object;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mars.rover.exceptions.InvalidPositionException;
import com.mars.rover.exceptions.ObstacleFoundException;

public class Grid {

	
	private int xHorizontalMax;
	private int yVerticalMax;
	@JsonIgnore
	List<Obstacle> obstacles = new ArrayList<Obstacle>();
	@JsonIgnore
	private Obstacle[][] gridObstacle;
	
	public Grid(int x, int y){
		this.xHorizontalMax = x;
		this.yVerticalMax = y;
		this.gridObstacle = new Obstacle[x][y];
	}
	
	public Grid() {
		this.gridObstacle = new Obstacle[xHorizontalMax][yVerticalMax];
	};
	
	public void initializedGridObstacle() {
		if(gridObstacle.length == 0) {
			gridObstacle = new Obstacle[xHorizontalMax][yVerticalMax];
		}
	}
	
	public Position getWrappedPosition(Position position) throws ObstacleFoundException, InvalidPositionException{
		Position wrappedPosition = new Position(getWrapHorizontal(position.getxHorizontal()),
				getWrapVertical(position.getyVertical()));
		isValidPosition(wrappedPosition);
		return wrappedPosition;
			
	}
	
	private int getWrapHorizontal(int x) {
	
		if(x<0) {
			return (x + xHorizontalMax);
		}else if(x>=xHorizontalMax){
			return (x % xHorizontalMax);
		}
		return x;
	}
	
	private int getWrapVertical(int y) {
		
		if(y<0) {
			return (y + yVerticalMax);
		}else if(y>=yVerticalMax){
			return (y % yVerticalMax);
		}
		return y;
	}
	
	public int getxHorizontalMax() {
		return xHorizontalMax;
	}

	public void setxHorizontalMax(int xHorizontalMax) {
		this.xHorizontalMax = xHorizontalMax;
	}

	public int getyVerticalMax() {
		return yVerticalMax;
	}

	public void setyVerticalMax(int yVerticalMax) {
		this.yVerticalMax = yVerticalMax;
	}

	public Obstacle[][] getGridObstacle() {
		return gridObstacle;
	}

	public void setGridObstacle(Obstacle[][] gridObstacle) {
		this.gridObstacle = gridObstacle;
	}
	
	public List<Obstacle> getObstacles() {
		return obstacles;
	}

	public void setObstacles(List<Obstacle> obstacles) {
		this.obstacles = obstacles;
	}
	
	public void addObstacle(Obstacle obstacle) throws InvalidPositionException, ObstacleFoundException{
		if(isValidPosition(obstacle.getPosition())) {
			obstacles.add(obstacle);
			gridObstacle[obstacle.getPosition().getxHorizontal()][obstacle.getPosition().getyVertical()] = obstacle;
		}	
	}
	
	public boolean isValidPosition(Position position) throws InvalidPositionException, ObstacleFoundException {
		int x = position.getxHorizontal();
		int y = position.getyVertical();
		if( x< 0 || x >= xHorizontalMax || y<0 || y >= yVerticalMax) {
			throw new InvalidPositionException("Position is invalid for current grid. Grid position bewteen (0,0) "+ this.toString());
		}
		
		if(null != gridObstacle[x][y]) {
			throw new ObstacleFoundException("There is an Obstacle in position " + position, gridObstacle[x][y]);
		}
		
		return true;
	}
	
	@Override
	public String toString(){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("(").append(xHorizontalMax - 1).append(",").append(yVerticalMax - 1).append(")");
		
		return sb.toString();
	}

	
	
}
