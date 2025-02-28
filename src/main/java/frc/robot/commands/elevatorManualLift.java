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
import frc.robot.RobotMap;

//Command for Running Elevator commands in subsys

public class elevatorManualLift extends Command { // 6.5 off the ground 2'4" from center of each port and 8" radius all calculations in inches
  private static final double JOYSTICK_THRESHOLD = 0.05;

  public elevatorManualLift() {
    requires(Robot.elevator_subsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double liftSpeed = - Robot.m_oi.getLeftJoystickY(Robot.m_oi.intakeController);
    
    int encoder = Robot.elevator_subsystem.getEncoder();

    if (liftSpeed > JOYSTICK_THRESHOLD || liftSpeed < -JOYSTICK_THRESHOLD) {
      //elevatorPosition = "M";
      SmartDashboard.putString("elevatorTargetPosition", "M");
      if (liftSpeed < 0){
        liftSpeed = 0.2 * liftSpeed;
     } else {
      liftSpeed = 0.5 * liftSpeed;
     }
     Robot.elevator_subsystem.move(liftSpeed);
    }
    else{
      Robot.elevator_subsystem.move(0);
    }
    if (RobotMap.DEBUG) {
    System.out.println("LiftSpeed" + liftSpeed);
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
    Robot.elevator_subsystem.move(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
