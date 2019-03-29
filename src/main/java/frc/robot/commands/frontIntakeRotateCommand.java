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
//More intake commands
public class frontIntakeRotateCommand extends Command {
  public frontIntakeRotateCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.frontIntakeRotation);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double rotateSpeed = -0.6 *Robot.m_oi.intakeController.getRawAxis(RobotMap.RIGHT_JOYSTICK_Y);

    if(Robot.m_oi.leftBumbper1.get()){
        rotateSpeed = 0.15;
        Robot.frontIntakeRotation.motorSpeed(rotateSpeed);
    }
    if(Robot.m_oi.rightBumbper1.get()){
      rotateSpeed = 0.105;
      Robot.frontIntakeRotation.motorSpeed(rotateSpeed);
    }else{
      Robot.frontIntakeRotation.motorSpeed(rotateSpeed);
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
    Robot.frontIntakeRotation.motorSpeed(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
