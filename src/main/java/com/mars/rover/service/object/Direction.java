package com.mars.rover.service.object;

public enum Direction {

	N("NORTH",0,0,1),
	E("EAST",1,1,0),
	S("SOUTH",2,0,-1),
	W("WEST",3,-1,0);
	
	private final int xHorizontal, yVertical, rotation;
	private final String direction; 
	
	private Direction(String direction, int rotation, int xHorizontal, int yVertical){
		this.direction = direction;
		this.rotation = rotation;
		this.xHorizontal = xHorizontal;
		this.yVertical = yVertical;
	}
	
	public Direction rotateRight(){
		return values()[(rotation + 1)%4];
	}
	
	public Direction rotateLeft(){
		return values()[(rotation + 3)%4];
	}
	
	public int getXHorizontal(){
		return xHorizontal;
	}
	
	public int getYVertical(){
		return yVertical;
	}
	
	public String getDirection(){
		
		return direction;
	}
}
