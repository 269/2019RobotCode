/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.driveTrain_subsystem;

public class driveWithJoysticks_command extends Command {
  public driveWithJoysticks_command() {
    // Use requires() here to declare subsystem dependencies
     requires(Robot.driveTrain_subsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double Y_speed = Robot.m_oi.driverController.getRawAxis(RobotMap.LEFT_JOYSTICK_Y);
    double X_speed = -1*Robot.m_oi.getLeftJoystickX(Robot.m_oi.driverController);
    double Z_rotatingSpeed = -1*Robot.m_oi.driverController.getRawAxis(RobotMap.RIGHT_JOYSTICK_X);
    Robot.driveTrain_subsystem.drive(Y_speed, X_speed, Z_rotatingSpeed);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveTrain_subsystem.drive(0, 0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
