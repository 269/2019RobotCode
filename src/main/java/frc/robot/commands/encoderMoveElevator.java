/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class encoderMoveElevator extends Command {

  public int wantedValue;
  public int tolerance = 200;
  public final double DOWNSPEED = -0.15;
  public final double UPSPEED = 0.30;
  public boolean done = false;

  /**
   * 
   * @param wantedValue the value from the encoder you want to go to
   */
  public encoderMoveElevator(int wantedValue) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.elevator_subsystem);
    this.wantedValue = wantedValue;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    int currentValue = Robot.elevator_subsystem.getEncoder();
//elevator encoder is inverted (goes positive to negative)
    if(currentValue > wantedValue + tolerance){ //going up
      Robot.elevator_subsystem.move(UPSPEED);
    }
    else if(currentValue < wantedValue - tolerance){ //going down
      Robot.elevator_subsystem.move(DOWNSPEED);
    }
    else{ //stop
      Robot.elevator_subsystem.move(0);
      done = true;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    System.out.println("Ran encoderMoveElevator");
    if((Robot.m_oi.getLeftJoystickY(Robot.m_oi.intakeController) < -0.15 || Robot.m_oi.getLeftJoystickY(Robot.m_oi.intakeController) > 0.15) || done == true){
      return true;
    }else{
      return false;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.elevator_subsystem.move(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
