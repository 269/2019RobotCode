/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.OI;
import frc.robot.RobotMap;

//Command for Running Elevator commands in subsys

public class elevatorManualLift extends Command {
  private static final double JOYSTICK_THRESHOLD = 0.05;
  private static final double LEVEL_ONE_HEIGHT = 100;
  private static final double LEVEL_TWO_HEIGHT = 200;
  private static final double LEVEL_THREE_HEIGHT = 300;
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

    if (Robot.m_oi.getLeftJoystickX(Robot.m_oi.intakeController) > Math.abs(JOYSTICK_THRESHOLD)) {
      liftStates = LiftStates.MANUAL;
    } else if (Robot.m_oi.xButton.get()) { // Lift State 1
      liftStates = LiftStates.PID;
      liftRequestedValue = LEVEL_ONE_HEIGHT;
    } else if (Robot.m_oi.yButton.get()) {
      liftStates = LiftStates.PID;
      liftRequestedValue = LEVEL_TWO_HEIGHT;
    } else if (Robot.m_oi.aButton.get()) {
      liftStates = LiftStates.PID;
      liftRequestedValue = LEVEL_THREE_HEIGHT;
    } 

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

  private double PIDControl() {
    return 0;
  }
}
