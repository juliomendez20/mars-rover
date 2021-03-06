package com.mars.rover.service;

import java.util.Scanner;

import com.mars.rover.exceptions.InvalidPositionException;
import com.mars.rover.exceptions.ObstacleFoundException;
import com.mars.rover.service.object.Direction;
import com.mars.rover.service.object.Grid;
import com.mars.rover.service.object.Obstacle;
import com.mars.rover.service.object.Position;
import com.mars.rover.service.object.Rover;

import static com.mars.rover.service.util.ScannerUtil.getInteger;
import static com.mars.rover.service.util.ScannerUtil.getValidStringEntry;


public class MarsRover {

    public static void main(String[] args) {
       
    	Scanner reader = new Scanner(System.in);
    	
    	//Get Horizontal Axis Grid
        int sizex = getInteger(reader,0,"Insert horizontal grid size:");
        
        //Get Vertical Axis Grid
        int sizey = getInteger(reader,0,"Insert vertical grid size:");

        //Create grid
        Grid grid = new Grid(sizex,sizey);
        
        
        int obstaclesNumber = getInteger(reader,-1,"Insert number of obstacles:");

        int x;
        int y;

        for (int i = 0; i < obstaclesNumber; i++) {
            System.out.println("Obstacle " + i);
            x = getInteger(reader,-1,"Insert coordinate x:");
            y = getInteger(reader,-1,"Insert coordinate y:");
            try {
            	grid.addObstacle(new Obstacle(new Position(x,y)));
            }catch (InvalidPositionException e) {
				System.out.println(e.getMessage());
				--i;
			}catch (ObstacleFoundException e) {
				System.out.println(e.getMessage());
				--i;
			}
        }
        
        //Rover creation
        Rover rover = null;
        do {
	        int roverx = getInteger(reader, -1, "Insert horizontal initial rover position:");
	        int rovery = getInteger(reader, -1, "Insert vertical initial rover position:");
	        String roverz = getValidStringEntry(reader, "[news]", "Insert initial rover direction (n = north, e = east, w = west, s = south):","Invalid Rover Direction."); //n = north, e = east, w = west, s = south
	        
        	try {
                rover = new Rover(Direction.valueOf(roverz.toUpperCase()), new Position(roverx,rovery),grid);
			} catch (InvalidPositionException e) {
				System.out.println(e.getMessage());
			} catch (ObstacleFoundException e) {
				System.out.println(e.getMessage());
			}
        
        } while (null == rover);
        
        do {
            String command = getValidStringEntry(reader, "[fblr]*", "Insert command (f = forward, b = backward, l = turn left, r = turn right):", "Invalid Command Movement");
            try {
    			rover.move(command);
    		}catch (ObstacleFoundException e) {
    			System.out.println(e.getMessage());
    		} catch (Exception e) {
    			
    		}
            System.out.println(rover.toString());
        }while(true);

    }
    
}
