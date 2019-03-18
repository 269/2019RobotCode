/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Conversions;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Command to move elevator to preset positions for cargo
 */
public class elevatorPIDCargoPos extends Command {
  private double liftRequestedValue;
  public String direction;

  /**
   * @param direction The direction to increament elevator position ("up" or "down")
   */
  public elevatorPIDCargoPos(String direction) {
    this.direction = direction;
    requires(Robot.elevator_subsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    String elevatorPosition = "?";
    final int MAX_LEVEL = 3;
    final int MIN_LEVEL = 0;
    int level = MIN_LEVEL;

    //convert ClosestPosition to level to start at closest position
    switch (Robot.elevator_subsystem.getClosestPosition()) {
      case "TOP":
        level = MAX_LEVEL;
        break;
      case "CPU":
        level = 0;
      break;
      case "C1":
        level = 1;
        break;
      case "C2":
        level = 2;
        break;
      case "C3":
        level = 3;
        break;
      case "HPU":
        level = 0;
      break;
      case "H1":
        level = 1;
        break;
      case "H2":
        level = 2;
        break;
      case "H3":
        level = 3;
        break;
      case "BOT":
        level = MIN_LEVEL;
        break;
      default:
        level = MIN_LEVEL;
        break;
    }

    //inc or dec level based on direction
    if(direction == "up"){
      if(level < MAX_LEVEL){
        level++;
      }
    }
    if(direction == "down"){
      if(level > MIN_LEVEL){
        level--;
      }
    }

    //Cargo Positions
    switch (level) {
      default:
      case 0:
        liftRequestedValue = Robot.elevator_subsystem.CARGO_PICKUP;
        elevatorPosition = "CPU";
      break;
      case 1:
        liftRequestedValue = Robot.elevator_subsystem.LEVEL_ONE_HEIGHT_CARGO;
        elevatorPosition = "C1";
        break;
      case 2:
        liftRequestedValue = Robot.elevator_subsystem.LEVEL_TWO_HEIGHT_CARGO;
        elevatorPosition = "C2";
        break;
      case 3:
        liftRequestedValue = Robot.elevator_subsystem.LEVEL_THREE_HEIGHT_CARGO;
        elevatorPosition = "C3";      
        break;
    }
    SmartDashboard.putString("elevatorTargetPosition", elevatorPosition);

    liftRequestedValue = Conversions.inchesToTicks(liftRequestedValue, Conversions.Subsystem.ELEVATOR);
    Robot.elevator_subsystem.PIDControl(liftRequestedValue);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    SmartDashboard.putString("elevatorTargetPosition", "N/A");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
