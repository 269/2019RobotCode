/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.driveWithJoysticks_command;

/**
 * Add your docs here.
 */
public class driveTrain_subsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

WPI_TalonSRX frontRightMotor = null;
WPI_TalonSRX frontLeftMotor = null;
WPI_TalonSRX rearRightMotor = null;
WPI_TalonSRX rearLeftMotor = null;
MecanumDrive mecDrive = null;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new driveWithJoysticks_command());
  }

  public driveTrain_subsystem() {
    frontRightMotor = new WPI_TalonSRX(RobotMap.FRONT_RIGHT_MOTOR);
    frontLeftMotor = new WPI_TalonSRX(RobotMap.FRONT_LEFT_MOTOR);
    rearRightMotor = new WPI_TalonSRX(RobotMap.REAR_RIGHT_MOTOR);
    rearLeftMotor = new WPI_TalonSRX(RobotMap.REAR_LEFT_MOTOR);
    mecDrive = new MecanumDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
    rearLeftMotor.setInverted(false);
    frontLeftMotor.setInverted(false);


    
  }

  public void drive(double yspeed, double xspeed, double zspeed ){
    mecDrive.driveCartesian(xspeed, yspeed, zspeed, Robot.navx.getYaw());
  }
}
