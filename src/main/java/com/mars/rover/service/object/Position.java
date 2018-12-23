package com.mars.rover.service.object;

import javax.validation.constraints.Min;

public class Position {
	
	@Min(value=0,message="Position xHorizontal should be greater than 0")
	private int xHorizontal;
	
	@Min(value=0,message="Position yHorizontal should be greater than 0")
	private int yVertical;
	
	protected Position() {
	}
	
	public Position(int xHorizontal,int yVertical) {
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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(")
			.append(this.xHorizontal)
			.append(",")
			.append(this.yVertical)
			.append(")");
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + xHorizontal;
		result = prime * result + yVertical;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (xHorizontal != other.xHorizontal)
			return false;
		if (yVertical != other.yVertical)
			return false;
		return true;
	}

	
	
}
