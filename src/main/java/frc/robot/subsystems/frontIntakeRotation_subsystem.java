/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.frontIntakeRotateCommand;

/**
 * This makes the intake rotate 
 */
public class frontIntakeRotation_subsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  WPI_TalonSRX frontIntakeRotate = null;

  public frontIntakeRotation_subsystem() {

  frontIntakeRotate = new WPI_TalonSRX(RobotMap.FRONT_INTAKE_ROTATE);

  }
  double tolerance = 0.1;
  boolean upperLimit = false, lowerLimit = false;
/**
 * 
 * @param motorSpeed the speed of the rotation (-1.0 to 1.0)
 */
  public void motorSpeed( double motorSpeed){
    if(motorSpeed >= -tolerance && motorSpeed <= tolerance){
      frontIntakeRotate.set(0);
    }
    else if((!upperLimit && motorSpeed > 0) || (!lowerLimit && motorSpeed < 0)){
      frontIntakeRotate.set(motorSpeed);
    }
    else{
      frontIntakeRotate.set(0);
    }
  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new frontIntakeRotateCommand());

  }
}
