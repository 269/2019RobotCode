/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.buttons.*;

/**
 * This is a demo program showing how to use Mecanum control with the RobotDrive
 * class.
 */
public class Robot extends TimedRobot {
  private static final int kFrontLeftChannel = 3;
  private static final int kRearLeftChannel = 7;
  private static final int kFrontRightChannel = 8;
  private static final int kRearRightChannel = 6;

  private static final int kJoystickChannel = 0;

  private MecanumDrive m_robotDrive;
  private Joystick m_stick;
  private Button rBumper;

  @Override
  public void robotInit() {
    WPI_TalonSRX frontLeft = new WPI_TalonSRX(kFrontLeftChannel);
    WPI_TalonSRX rearLeft = new WPI_TalonSRX(kRearLeftChannel);
    WPI_TalonSRX frontRight = new WPI_TalonSRX(kFrontRightChannel);
    WPI_TalonSRX rearRight = new WPI_TalonSRX(kRearRightChannel);

    // Invert the left side motors.
    // You may need to change or remove this to match your robot.
    frontLeft.setInverted(false);
    rearLeft.setInverted(false);

    m_robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);

    m_stick = new Joystick(kJoystickChannel);
  }

  @Override
  public void teleopPeriodic() {
    // Use the joystick X axis for lateral movement, Y axis for forward
    // movement, and Z axis for rotation.

    double joystickXspeed;
    double joystickYspeed;
    double joystickZspeed;
    double tolerance = 0.1;
   //X
    if(-m_stick.getX() < tolerance && -m_stick.getX() > -tolerance ){
      joystickXspeed = 0.0;
    }
    else {
      joystickXspeed = -m_stick.getX();
    }
    //Y
    if(m_stick.getY() < tolerance && m_stick.getY() > -tolerance){
      joystickYspeed = 0.0;
    }
    else {
      joystickYspeed = m_stick.getY();
    }
    //Z
    if(m_stick.getRawAxis(5) < tolerance && m_stick.getRawAxis(5) > -tolerance){
      joystickZspeed = 0.0;
    }
    else {
      joystickZspeed = m_stick.getRawAxis(5);
    }

    Button lBumper = new JoystickButton(m_stick, 5);
  
      if(lBumper.get() == true){
        joystickXspeed = joystickXspeed*0.5;
        joystickYspeed = joystickYspeed*0.5;
        joystickZspeed = joystickZspeed*0.5;

        System.out.println("true");

      /*  System.out.println(joystickXspeed);
        System.out.println(joystickYspeed);
        System.out.println(joystickZspeed);
        */
      }


    m_robotDrive.driveCartesian(joystickXspeed, joystickYspeed,
        joystickZspeed, 0.0);
/*
        System.out.println(joystickXspeed);
        System.out.println(joystickYspeed);
        System.out.println(joystickZspeed);
        */
  }
}
