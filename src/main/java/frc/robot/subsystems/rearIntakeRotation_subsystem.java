/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import frc.robot.RobotMap;
import frc.robot.commands.rearIntakeRotation_command;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class rearIntakeRotation_subsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

WPI_TalonSRX rearIntakeRightRotation = null;
WPI_TalonSRX rearIntakeLeftRotation = null;

public rearIntakeRotation_subsystem(){
rearIntakeRightRotation = new WPI_TalonSRX(RobotMap.RIGHT_REAR_INTAKE_ROTATION);
rearIntakeLeftRotation = new WPI_TalonSRX(RobotMap.LEFT_REAR_INTAKE_ROTATION);
}

boolean bottomLimit = false; 
boolean topLimit = false;
//the rear intake adjustment moves in a 150° range


/**
 * 
 * @param speed 0.0 to -1.0 moves elevator down & 0.0to 1.0 moves elevator up
 */
public void rotate(double speed){//rotating the rear intake up or down depending on the speed (negative or positive)
  if(speed <= -0.1 && !bottomLimit){ //move down
      //stops motors rotating to far based on encoders
      rearIntakeRightRotation.set(speed);
      rearIntakeLeftRotation.set(speed);
  }else if(speed >= 0.1 && !topLimit ){//move up
   //stops motors from rotating too far based on encoders
      rearIntakeRightRotation.set(speed);
      rearIntakeLeftRotation.set(speed);
    }else{
    rearIntakeRightRotation.set(0);
    rearIntakeLeftRotation.set(0);}
  } 

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
     setDefaultCommand(new rearIntakeRotation_command());
  }
}