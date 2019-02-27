# 2019RobotCode

PRIORITIES:
1. create a simple working master code of all manual control
2. add limit code to elevator
3. ENSURE that the manual control works on robot, limits work on robot, & camera works on robot
4. add encoder code to other limits using MATH!
5. do fused heading stuff


TODO:
- create a fusedheading reset upon starting of a match(teleop init & auto init)
- create a single method to be accessed by anything that returns the current robots angle(fusedheading or yaw can be swaped but return same values when rotating same direction)
- ~~create a rear intake roller subsystem and command --Bence~~
- ~~create a rotate to angle command from pov buttons --Grace~~
- setup camera & driverstation layout for driver
- add upper and lower limit to lead screws subsystem
- add upper and lower limit to rearIntakeRotation_subsystem
- add all sensors into robotmap
- add all sensor code into each subsystems upper and lower limits
- impliment PID MotionMagic for Elevator then other components & drive
- create a method in the vacuum_subustem that returns true or false if the vacume is currently on or off.
    - check vacuumButton command. do you really want the vacum to shut off and drop the hatch when ever its intrupted??
    - if not that perhaps add code to deterimne when the command is run if the vacume is currently on or off
- ~~fix the toggle in the vacumButtons command - see my toggle example bellow~~
- clean up code
- vision tracking code

#toggle code example:
``` java
//declare these outside of loop
boolean buttonPressed;
boolean active = false; //by default is off
boolean previouslyReleased = true; //by default not pressed

//get button state
buttonPressed = Robot.m_oi.rightBumbper.get();

//toggle boolean on or off
if(buttonPressed && previouslyReleased) { //buton pressed
    active = !active;
    previouslyReleased = false; 
}else if (!buttonPressed) { //buton released
    previouslyReleased = true;
}

if (active) {
    //run the motor or device
} else {
    //stop the motor or device
}
```
