/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Vacuum_subsystem;
/**
 * @command turns hatch panel vacume on and off when a buton is pressed
 */
public class VacuumButtons extends Command {
  boolean buttonPressed;
  boolean previouslyPressed = true;
  boolean active = false;

  public VacuumButtons() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.vacuum);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    //toggle the vacuum on and off on button release
    buttonPressed = Robot.m_oi.rightBumbper.get();
    if (buttonPressed && previouslyPressed) { 
      active = !active;
      previouslyPressed = false;
    } 
    else if(!buttonPressed){
      previouslyPressed = true;
     }
    if(active){
      Robot.vacuum.vacuumSucktion(true);
    }
    else{
      Robot.vacuum.vacuumSucktion(false); 
    }
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
    Robot.vacuum.vacuumSucktion(false); 
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
