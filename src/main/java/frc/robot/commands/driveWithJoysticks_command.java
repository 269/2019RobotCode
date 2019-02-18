/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.PIDBase.Tolerance;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.driveTrain_subsystem;

//Driving With Joysticks and such
public class driveWithJoysticks_command extends Command {
  boolean toggleRightBumper = false;
  boolean toggleLeftBumper = false;
  boolean releasedRightBumper = false;
  boolean releasedLeftBumper = false;
  int gear = 0;
  final int MAXGEAR = 3;
  final int MINGEAR = 0;

public driveWithJoysticks_command() {
    // Use requires() here to declare subsystem dependencies
     requires(Robot.driveTrain_subsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }
  
  //Called repeatedly when this Command is scheduled to run
  //Gets Values of Joysticks
  @Override
  protected void execute() {
    double Y_speed = Robot.m_oi.driverController.getRawAxis(RobotMap.LEFT_JOYSTICK_Y);
    double X_speed = -Robot.m_oi.getLeftJoystickX(Robot.m_oi.driverController);
    double Z_rotatingSpeed = Robot.m_oi.driverController.getRawAxis(RobotMap.RIGHT_JOYSTICK_X);

    double tolerance = 0.1;
   //X
    if(X_speed < tolerance && X_speed > -tolerance ){
      X_speed = 0.0;
    }
    //Y
    if(Y_speed < tolerance && Y_speed > -tolerance){
      Y_speed = 0.0;
    }
    //Z
    if(Z_rotatingSpeed < tolerance && Z_rotatingSpeed > -tolerance){
      Z_rotatingSpeed = 0.0;
    }
  //half speed section
  if (Robot.m_oi.rightBumbper.get() == true) {
    releasedRightBumper = false;
  } else {
    if (releasedRightBumper == false) {
        toggleRightBumper = !toggleRightBumper;
        if (gear < MAXGEAR){
         gear++; 
        } 
        releasedRightBumper = true;
    }
  }
  if (Robot.m_oi.leftBumbper.get() == true) {
    releasedLeftBumper = false;
  } else {
    if (releasedLeftBumper == false) {
        toggleLeftBumper = !toggleLeftBumper;
        if( gear > MINGEAR){
          gear--;
        }
        releasedLeftBumper = true;
    }
  }
    if(gear == 0){

    System.out.println("Gear: " + gear);
    }
    if(gear == 1){
    X_speed = X_speed*0.75;
    Y_speed = Y_speed*0.75;
    Z_rotatingSpeed = Z_rotatingSpeed*0.75;

    System.out.println("Gear: " + gear);

    }
    if(gear == 2){
      X_speed = X_speed*0.5;
      Y_speed = Y_speed*0.5;
      Z_rotatingSpeed = Z_rotatingSpeed*0.5;

      System.out.println("Gear: " + gear);

      }
      if(gear == 3){
        X_speed = X_speed*0.25;
        Y_speed = Y_speed*0.25;
        Z_rotatingSpeed = Z_rotatingSpeed*0.25;
  
        System.out.println("Gear: " + gear);
  
        }
      Robot.driveTrain_subsystem.drive(Y_speed, X_speed, Z_rotatingSpeed);
      System.out.println("x Speed: " + X_speed + "\nY Speed: " + Y_speed + "\n Z Speed: " + Z_rotatingSpeed);

      if (Robot.m_oi.xButton.get() == true) {
        Robot.navx.reset();
    }
  }
  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveTrain_subsystem.drive(0, 0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
