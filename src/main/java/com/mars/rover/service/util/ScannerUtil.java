package com.mars.rover.service.util;

import java.util.Scanner;

public class ScannerUtil {

	public static int getInteger(Scanner reader, int baseInt, String valueDescription) {
    
    	int result;
    	boolean entryIsIncorrect = true;
    	
	    do{
	    	System.out.println(valueDescription);
	    	while(!reader.hasNextInt()) {
	    		System.out.println("Not an Integer!!!");
	    		reader.next();
	    	}
	    
	    	result = reader.nextInt();
	    	entryIsIncorrect = result<=baseInt;
	    	
	    	if(entryIsIncorrect)
	    		System.out.println("Please an Integer greater or equal to " + baseInt);
	    	
	    }while(entryIsIncorrect);
	    
	    return result;

    }
    
    public static String getValidStringEntry(Scanner reader, String regex, String valueDescription, String invalidDescriptio) {
        
    	String result;
    	boolean entryIsIncorrect = true;
    	
	    do{
	    	System.out.println(valueDescription);
	    	while(!reader.hasNext(regex)) {
	    		System.out.println(invalidDescriptio);
	    		System.out.println(valueDescription);
	    		reader.next();
	    	}
	    
	    	result = reader.next();
	    	entryIsIncorrect = false;
	    	
	    }while(entryIsIncorrect);
	    
	    return result;

    }
}
