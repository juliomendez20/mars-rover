package com.mars.rover.service.object;

public class Position {

	private int xHorizontal;
	private int yVertical;
	
	protected Position() {
	}
	
	public Position(int xHorizontal,int yVertical) {
		super();
		this.xHorizontal = xHorizontal;
		this.yVertical = yVertical;
	}
	
	public int getxHorizontal() {
		return xHorizontal;
	}
	
	public void setxHorizontal(int xHorizontal) {
		this.xHorizontal = xHorizontal;
	}
	
	public int getyVertical() {
		return yVertical;
	}
	
	public void setyVertical(int yVertical) {
		this.yVertical = yVertical;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(")
			.append(this.xHorizontal)
			.append(",")
			.append(this.yVertical)
			.append(")");
		return sb.toString();
	}
	
}
