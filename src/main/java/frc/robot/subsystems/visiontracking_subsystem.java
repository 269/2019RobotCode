/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.drive.MecanumDrive;
//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
//import frc.robot.Robot;
//import frc.robot.RobotMap;


/**
 * Add your docs here.
 */
public class visiontracking_subsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
/*
  WPI_TalonSRX frontRightMotor = null;
  WPI_TalonSRX frontLeftMotor = null;
  WPI_TalonSRX rearRightMotor = null;
  WPI_TalonSRX rearLeftMotor = null;
  MecanumDrive mecDrive = null;
  double xpos = Robot.targetValue.getDouble(0.0);
  double tolerance = 3.0;

  public void visionTracking_subsystem(){
    frontRightMotor = new WPI_TalonSRX(RobotMap.FRONT_RIGHT_MOTOR);
    frontLeftMotor = new WPI_TalonSRX(RobotMap.FRONT_LEFT_MOTOR);
    rearRightMotor = new WPI_TalonSRX(RobotMap.REAR_RIGHT_MOTOR);
    rearLeftMotor = new WPI_TalonSRX(RobotMap.REAR_LEFT_MOTOR);
    mecDrive = new MecanumDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);

  }
  
  public void drive(double yspeed, double xspeed, double zspeed){
    mecDrive.driveCartesian(xspeed, yspeed, -zspeed, -0.0);

    if(Robot.m_oi.aButton.get()){
      if((xpos - tolerance) < xpos && xpos < (xpos + tolerance)){
        mecDrive.driveCartesian(0, 0, -0, -0.0);
      }
      else if(xpos < 157 - tolerance){
        mecDrive.driveCartesian(0, -.25, -0, -0.0);
      }
      else if(xpos > 157 + tolerance){
        mecDrive.driveCartesian(0, .25, -0, -0.0);
      }
    }
  }
  */

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
