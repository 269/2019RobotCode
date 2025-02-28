/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class rearIntakeRotation_command extends Command {
  public rearIntakeRotation_command() {
    requires(Robot.rearIntakeRotation);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double rotateSpeedIn = Robot.m_oi.getRightTriggerAxis(Robot.m_oi.intakeController);
    double rotateSpeedOut = Robot.m_oi.getLeftTriggerAxis(Robot.m_oi.intakeController);

    Robot.rearIntakeRotation.rotate(rotateSpeedIn);
    Robot.rearIntakeRotation.rotate(rotateSpeedOut);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.rearIntakeRotation.rotate(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
