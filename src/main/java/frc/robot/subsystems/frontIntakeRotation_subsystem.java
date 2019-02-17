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

/**
 * Add your docs here.
 */
public class frontIntakeRotation_subsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  WPI_TalonSRX frontIntakeRotate = null;

  public frontIntakeRotation_subsystem() {

  frontIntakeRotate = new WPI_TalonSRX(RobotMap.FRONT_INTAKE_ROTATE);

  }
  double tolerance = 0.1;

  public void motorSpeed( double motorSpeed){

    if(motorSpeed >= -tolerance && motorSpeed <= tolerance){
      motorSpeed = 0;
      frontIntakeRotate.set(0);
    }
    else{
      frontIntakeRotate.set(motorSpeed);
    }
  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new frontIntakeRotateCommand());

  }
}
