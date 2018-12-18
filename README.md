# Mars rover

##Mars Rover Implementation

This my approach to solve the mars Rover Api problem. As I did not know how did you would like to interact with the software, I have created 2 different approaches, both using Java 8.

- The entry point is MarsRover and it uses the Scanner for the console to request for the input data, similar to the sample you have sent. I have added validation to the inputs so we end up with a valid grid, a set of valid obstacles and a valid initial position for the rover. 

- The entry point is MarsRoverService, it is a simple SpringBoot application that enable the interaction with the rover via http, I have added the swagger configuration to produce certain documentation on the use of it, generated several exceptions and exception handlers for those.

##Problems I ran into. 

Understanding the wrapping concept. I was confuse by the phrase (planets are spheres after all). I have developed the wrapping functionality for a 2D grid.

##Things that can be improved

- Validations for both entry point can be standardize better.
- JUnit Tests are a must in my opinion to any deliverable to Production (up to 80% Coverage), but in the interest of time I did not included. 
- Implementation is static, only one rove and one grid at any point of time, functions to reset rover, delete rover can be developed.
- commands smart recognition to avoid unnecessary movements in example: llll will result in same position, then no need to move... fbfbfbfbfbfb (if no obstacle encountered then same position). etc...
- For the Rest Api implementation, concurrency should be properly managed. 

## How to run
 All dependencies are managed by maven. Simply download the code, imported as a maven project and run as Java Application from the desired Entry point.
 
 To interact with the application via Rest, you can use postman and select Json (did not developed a client for this). Easiest way to understand the different exposed functions is to start the service and access following link
 
 http://localhost:8080/swagger-ui.html#/
 
 In there a description for all functions is specified, naming is suggesting what each api do. But the proper order should be:
 
 1.- POST /mars-rover/grid   (Create a grid) -- Request body should be:
 	{
    		"xHorizontalMax": 8,
    		"yVerticalMax": 8
	}
 
 2.- POST /mars-rover/grid/obstacles (add obstacles to the grid) -- Request body should be:
	{
        "position": {
            "xHorizontal": 3,
            "yVertical": 3
        }
    	}

 3.- POST /mars-rover/rover (create a rover for the existing grid) -- Request body:
 
	 {
	    "direction": "N",
	    "currentPosition": {
	        "xHorizontal": 0,
	        "yVertical": 0
	    }
	}

 4.- POST /mars-rover/rover/move/{command} (move rover, {command} should be a string containing only (l r f b):
 		/mars-rover/rover/lrbf 
 
## Rest end points Calls


##----------------------------------------------------------##

##Description

Develop an api that moves a rover around on a grid.

- You will have to provide the map size
- You will have to provide the number of obstacles and their position
- You will have to provide the initial starting point (x,y) of a rover and the direction (N,S,E,W) it is facing.
- The rover receives a character array of commands.
- Implement commands that move the rover forward/backward (f,b).
- Implement commands that turn the rover left/right (l,r).
- Implement wrapping from one edge of the grid to another. (planets are spheres after all)
- Implement obstacle detection before each move to a new square. If a given sequence of commands encounters an obstacle, the rover moves up to the last possible point and reports the obstacle.

## How to
We want to see your changes but not make them public. In order to do so create a new repo on bitbucket based on this project, make all the changes and give us access to see them ;).

We need the commit list in order to check the evolution, naming and changes you made during the process.

## What we expect
Our intern have made as good as it could but not as we would. 

- Feel free to change as much code as you want, but you'll have to make it readable and maintainable.
- Use any JVM language.
- Code should be self-executable and self-compiled. This includes any dependency manager we will need (hint: maven or gradle)
- Feel free to use any pattern, framework or whatever you want to.
- Bug free will be a plus.
- Include in your readme:
  A brief text explaining what tech stack you used, architecture, design patterns...
  Any problem you ran into while doing the code.
  What you think could be improved in case there is any improvement to your own code.
