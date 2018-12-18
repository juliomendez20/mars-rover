# Mars rover

##Mars Rover Implementation

This my approach to solve the mars Rover Api problem. As I did not know how did you would like to interact with the software, I have created 2 different approaches.

- The entry point is MarsRover and it uses the Scanner for the console to request for the input data, similar to the sample you have sent. I have added validation to the inputs so we end up with a valid grid, a set of valid obstacles and a valid initial position for the rover. 

- The entry point is MarsRoverService, it is a simple SpringBoot application that enable the interaction with the rover via http, I have added the swagger configuration to produce certain documentation on the use of it, generated several exceptions and exception handlers for those.

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
