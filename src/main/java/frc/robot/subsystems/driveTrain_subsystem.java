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
 * This subsystem makes the robot drive field oriented for 4 wheels
 * mecanumdrive
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
  boolean released = true;
  boolean normDrive = false;
  /**
   * 
   * @param yspeed the motor speed for y axis (-1.0 to 1.0)
   * @param xspeed the motor speed for x axis (-1.0 to 1.0)
   * @param zspeed the rotation motor speed on x axis (-1.0 to 1.0)
   */
  public void drive(double yspeed, double xspeed, double zspeed){
    if(Robot.m_oi.leftJoyPress.get() && released){
      normDrive = !normDrive;
      released = false;
    } else if(!Robot.m_oi.leftJoyPress.get()){
        released = true;
    }
    if(normDrive){
      mecDrive.driveCartesian(xspeed, yspeed, -zspeed, -0.0);
    }
    else{
      mecDrive.driveCartesian(xspeed, yspeed, -zspeed, -Robot.navx.getFusedHeading());
    }
  }
}
