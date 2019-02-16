/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.elevatorManualLift;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * Add your docs here.
 */
public class elevator_subsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  WPI_TalonSRX elevatorLeft = null;
  WPI_TalonSRX elevatorRight = null; 

public elevator_subsystem(){
elevatorRight = new WPI_TalonSRX(RobotMap.ELEVATOR_RIGHT);
elevatorLeft = new WPI_TalonSRX(RobotMap.ELEVATOR_LEFT);
}

boolean bottomLimit = false;
boolean topLimit = false;

/**
 * 
 * @param speed 0.0 to -1.0 moves elevator down & 0.0to 1.0 moves elevator up
 */
public void move(double speed){//moving the elevator up or down depending on the speed (positive or negative)
if(speed <= -0.1 && !bottomLimit){ //move down
    //stops elevator going to low based on encoders
    elevatorLeft.set(speed);
    elevatorRight.set(speed);
}else if(speed >= 0.1 && !topLimit ){//move up
 //stops elevator going to high based on encoders
    elevatorLeft.set(speed);
    elevatorRight.set(speed);
  }else{
  elevatorLeft.set(0);
  elevatorRight.set(0);
  } 

//WE HAVEN'T PROGRAMMED ENCODERS YET, GOTTA DO THAT!!!

}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
     setDefaultCommand(new elevatorManualLift());
  }
}
