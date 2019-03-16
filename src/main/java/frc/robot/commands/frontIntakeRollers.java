/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

//Controlling Front Intake Motors
public class frontIntakeRollers extends Command {

  double tolerance = 0.1;

  public frontIntakeRollers() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.frontIntake);

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
     
    boolean rollerSpeedIn = Robot.m_oi.aButton1.get();
    boolean rollerSpeedOut = Robot.m_oi.bButton1.get();

    if(rollerSpeedIn){
      Robot.frontIntake.rollerSpeed(0.5);
    }
    else if (rollerSpeedOut){
      Robot.frontIntake.rollerSpeed(-0.5);
    }
    else{
      Robot.frontIntake.rollerSpeed(0);
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
    Robot.frontIntake.rollerSpeed(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
