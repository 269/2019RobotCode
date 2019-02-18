# 2019RobotCode

TODO:
- create a fusedheading rest upon starting of a match(teleop init & auto init)
- create a rear intake roller subsystem and command
- create a rotate to angle command from pov buttons
- setup camera & driverstation layout for driver
- add upper and lower limit to lead screws subsystem
- add upper and lower limit to rearIntakeRotation_subsystem
- add all sensors into robotmap
- add all sensor code into each subsystems upper and lower limits
- impliment PID MotionMagic for Elevator then other components & drive
- create a method in the vacuum_subustem that returns true or false if the vacume is currently on or off.
    - check vacuumButton command. do you really want the vacum to shut off and drop the hatch when ever its intrupted??
    - if not that perhaps add code to deterimne when the command is run if the vacume is currently on or off
- fix the toggle in the vacumButtons command - see my toggle example bellow
- clean up code

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