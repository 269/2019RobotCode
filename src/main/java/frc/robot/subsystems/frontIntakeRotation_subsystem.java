/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.frontIntakeRotateCommand;

/**
 * This makes the intake rotate 
 */
public class frontIntakeRotation_subsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public WPI_TalonSRX frontIntakeRotate = null;
  public int encoderStart = 0;

  public frontIntakeRotation_subsystem() {

  frontIntakeRotate = new WPI_TalonSRX(RobotMap.FRONT_INTAKE_ROTATE);
  encoderStart = getEncoder();
  }
  double tolerance = 0.05;
  boolean upperLimit = false, lowerLimit = false;
/**
 * 
 * @param motorSpeed the speed of the rotation (-1.0 to 1.0)
 */
  public void motorSpeed( double motorSpeed){
    if(motorSpeed >= -tolerance && motorSpeed <= tolerance){
      frontIntakeRotate.set(0);
    }
    /*else if((!upperLimit && motorSpeed > 0) || (!lowerLimit && motorSpeed < 0)){
      frontIntakeRotate.set(motorSpeed);
    }
    */
    else{
      frontIntakeRotate.set(motorSpeed);
    }
    if (RobotMap.DEBUG) {
    System.out.println("rotation: " + motorSpeed);
    }
  }

  public int getEncoder() {
    int encoder = frontIntakeRotate.getSelectedSensorPosition();
    if(encoderStart <= 0){
      encoder = encoder + encoderStart;
    }else{
      encoder = encoder - encoderStart;
    }
    SmartDashboard.putNumber("Front Intake Rotation Encoder", encoder);
    return encoder;
  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new frontIntakeRotateCommand());

  }
}
