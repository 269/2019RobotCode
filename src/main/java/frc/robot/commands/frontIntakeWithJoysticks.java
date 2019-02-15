/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class frontIntakeWithJoysticks extends Command {

  double tolerance = 0.1;

  public frontIntakeWithJoysticks() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.frontIntake_subsystem);

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
   
    double rollerSpeedIn = Robot.m_oi.getLeftTriggerAxis(Robot.m_oi.intakeController);
    double rollerSpeedOut = Robot.m_oi.getRightTriggerAxis(Robot.m_oi.intakeController);

    if(rollerSpeedIn >= -tolerance && rollerSpeedIn <= tolerance){
      rollerSpeedIn = 0;
    }
    if(rollerSpeedOut >= -tolerance && rollerSpeedOut >= tolerance){
      rollerSpeedOut = 0;
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
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
