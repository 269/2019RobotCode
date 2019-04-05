/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class encoderMoveIntake extends Command {

  public int wantedValue;
  public int tolerance = 300;
  public final double SETSPEED = 0.25;
  public double speed;
  public boolean done = false;

/**
 * 
 * @param wantedValue the wanted value for the encoder
 */
  public encoderMoveIntake(int wantedValue) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.frontIntakeRotation);
    this.wantedValue = wantedValue;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    int currentValue = Robot.frontIntakeRotation.getEncoder();

    if(currentValue < wantedValue - tolerance){ //going up
      speed = SETSPEED;
    }
    else if(currentValue > wantedValue + tolerance){ //going down
      speed = -0.3;
    }
    else{ //stop
      speed = 0.0;
      done = true;
    }
    if(currentValue < -900) {
      speed = speed + 0.15;
    }
    Robot.frontIntakeRotation.motorSpeed(speed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    System.out.println("Ran encoderMoveIntake");
    if((Robot.m_oi.getRightJoystickY(Robot.m_oi.intakeController) > 0.15 || Robot.m_oi.getRightJoystickY(Robot.m_oi.intakeController) < -0.15) || Robot.m_oi.leftBumbper1.get() == true || Robot.m_oi.rightBumbper1.get() == true || done == true){
      return true;
    }else{
      return false;
    } 
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.frontIntakeRotation.motorSpeed(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
