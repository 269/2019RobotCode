/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class rearIntakeRollers extends Command {

  double tolerance = 0.1;

  public rearIntakeRollers() {
    // Use requires() here to declare subsystem dependencies
     requires(Robot.rearIntakeRollers_subsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  //double rollerSpeedIn = 0.5; 
  //boolean rollerIn = Robot.m_oi.xButton1.get();  //x button at 0.5 speed

  if(Robot.m_oi.xButton1.get()){
  Robot.rearIntakeRollers_subsystem.rearIntakeRollerIn();
  }
  if(Robot.m_oi.yButton1.get()){
  Robot.rearIntakeRollers_subsystem.rearIntakeRollerOut();
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
    Robot.rearIntakeRollers_subsystem.rollerSpeed(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
