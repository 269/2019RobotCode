/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.rearIntakeRollers;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * Add your docs here.
 */
public class rearIntakeRollers_subsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  WPI_TalonSRX spinRearIntake = null;
  
public rearIntakeRollers_subsystem(){

 spinRearIntake = new WPI_TalonSRX(RobotMap.SPIN_REAR_INTAKE);

}

double tolerance = 0.1;
/**
 * 
 * @param rollerSpeed the speed of the motors (-1.0 to 1.0)
 */
public void rollerSpeed( double rollerSpeed){
  if(rollerSpeed >= -tolerance && rollerSpeed <= tolerance){
    spinRearIntake.set(0);
  }
  else{
    spinRearIntake.set(rollerSpeed);
  }
}

double rollerSpeed;

public double rearIntakeRollerIn(){
  rollerSpeed = 0.5; 
  return rollerSpeed;
}
public double rearIntakeRollerOut(){
  rollerSpeed = -0.5;
  return rollerSpeed;
}



  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
