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

public class elevatorPIDLift extends Command {
  private double liftRequestedValue;
  public String position;

  public elevatorPIDLift(String position) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.position = position;
    requires(Robot.elevator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    String elevatorPosition = Robot.elevator.getClosestPosition();
    int levelC = 1;
    int levelH = 1;
    final int MAX_LEVEL = 4;
    final int MIN_LEVEL = 1;
    


    
    if(position == "up"){
      if(levelH < MAX_LEVEL){
        levelH++;
      }
    }
    if(position == "down"){
      if(levelH > MIN_LEVEL){
        levelH--;
      }
    }
    if(position == "left"){
      if(levelC < MAX_LEVEL){
        levelC++;
      }
    }
    if(position == "right"){
      if(levelC > MIN_LEVEL){
        levelC--;
      }
    }
      if (levelH == 2) { 
      liftRequestedValue = Robot.elevator.LEVEL_ONE_HEIGHT_HATCH;
      elevatorPosition = "H1";
    } else if (levelH == 3) {
      liftRequestedValue = Robot.elevator.LEVEL_TWO_HEIGHT_HATCH;
      elevatorPosition = "H2";
    } else if (levelH == 4) {
      liftRequestedValue = Robot.elevator.LEVEL_THREE_HEIGHT_HATCH;
      elevatorPosition = "H3";
    } else if (levelC == 2) {
      liftRequestedValue = Robot.elevator.LEVEL_ONE_HEIGHT_CARGO;
      elevatorPosition = "C1";
    } else if (levelC == 3) {
      liftRequestedValue = Robot.elevator.LEVEL_TWO_HEIGHT_CARGO;
      elevatorPosition = "C2";
    } else if (levelC == 4) {
      liftRequestedValue = Robot.elevator.LEVEL_THREE_HEIGHT_CARGO;
      elevatorPosition = "C3";
    } else if (levelC == 1) {
      liftRequestedValue = Robot.elevator.CARGO_PICKUP;
      elevatorPosition = "CPU";
    } else if (levelH == 1) { 
      liftRequestedValue = Robot.elevator.HATCH_PICKUP;
      elevatorPosition = "HPU";
    } else{
      elevatorPosition = "?";
    }
    SmartDashboard.putString("elevatorTargetPosition", elevatorPosition);

    liftRequestedValue = Conversions.inchesToTicks(liftRequestedValue, Conversions.Subsystem.ELEVATOR);
    Robot.elevator.PIDControl(liftRequestedValue);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.elevator.PIDControl(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
