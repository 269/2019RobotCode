/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.OI;
import frc.robot.RobotMap;

//Command for Running Elevator commands in subsys

public class elevatorManualLift extends Command { // 6.5 off the ground 2'4" from center of each port and 8" radius all calculations in inches
  private static final double JOYSTICK_THRESHOLD = 0.05;
  private static final double LEVEL_ONE_HEIGHT_HATCH = 0;
  private static final double LEVEL_TWO_HEIGHT_HATCH = 16;
  private static final double LEVEL_THREE_HEIGHT_HATCH = 43;
  private static final double LEVEL_ONE_HEGHT_CARGO = 13;
  private static final double LEVEL_TWO_HEGHT_CARGO = 33;
  private static final double LEVEL_THREE_HEGHT_CARGO = 53;
  private static final double TOP_LIMIT = 55;
  private static final double BOTTOM_LIMIT = 0;
  private static final double CARGO_PICKUP = 12;
  private static final double HATCH_PICKUP = 3;


  private static enum LiftStates {
    MANUAL, PID
  }
  private static LiftStates liftStates;

  private double liftRequestedValue = 0;

  public elevatorManualLift() {
    requires(Robot.elevator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double liftSpeed = 0;
    String elevatorPosition;

    //Going to add a toggle on POV buttons

    if (Robot.m_oi.getLeftJoystickX(Robot.m_oi.intakeController) > Math.abs(JOYSTICK_THRESHOLD)) {
      liftStates = LiftStates.MANUAL;
      elevatorPosition = "M";
    } else if (Robot.m_oi.xButton.get()) { // Lift State 1
      liftStates = LiftStates.PID;
      liftRequestedValue = LEVEL_ONE_HEIGHT_HATCH;
      elevatorPosition = "1H";
    } else if (Robot.m_oi.yButton.get()) {
      liftStates = LiftStates.PID;
      liftRequestedValue = LEVEL_TWO_HEIGHT_HATCH;
      elevatorPosition = "2H";
    } else if (Robot.m_oi.aButton.get()) {
      liftStates = LiftStates.PID;
      liftRequestedValue = LEVEL_THREE_HEIGHT_HATCH;
      elevatorPosition = "3H";
    } else if (Robot.m_oi.aButton.get()) {
      liftStates = LiftStates.PID;
      liftRequestedValue = LEVEL_ONE_HEGHT_CARGO;
      elevatorPosition = "1C";
    } else if (Robot.m_oi.aButton.get()) {
      liftStates = LiftStates.PID;
      liftRequestedValue = LEVEL_TWO_HEGHT_CARGO;
      elevatorPosition = "2C";
    } else if (Robot.m_oi.aButton.get()) {
      liftStates = LiftStates.PID;
      liftRequestedValue = LEVEL_THREE_HEGHT_CARGO;
      elevatorPosition = "3C";
    } else if (Robot.m_oi.aButton.get()) {
      liftStates = LiftStates.PID;
      liftRequestedValue = TOP_LIMIT;
      elevatorPosition = "TOP";
    } else if (Robot.m_oi.aButton.get()) {
      liftStates = LiftStates.PID;
      liftRequestedValue = BOTTOM_LIMIT;
      elevatorPosition = "BOT";
    } else if (Robot.m_oi.aButton.get()) {
      liftStates = LiftStates.PID;
      liftRequestedValue = CARGO_PICKUP;
      elevatorPosition = "CPU";
    } else if (Robot.m_oi.aButton.get()) {
      liftStates = LiftStates.PID;
      liftRequestedValue = HATCH_PICKUP;
      elevatorPosition = "HPU";
    } else{
      elevatorPosition = "?";
    }
    SmartDashboard.putString("elevatorPos", elevatorPosition);

    switch (liftStates) {
      case MANUAL:
        liftSpeed = Robot.m_oi.intakeController.getRawAxis(RobotMap.LEFT_JOYSTICK_Y);
        Robot.elevator.move(liftSpeed);
        break;

      case PID:
        Robot.elevator.PIDControl(liftRequestedValue);
        break;
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
Robot.elevator.move(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  end();
  }
}
