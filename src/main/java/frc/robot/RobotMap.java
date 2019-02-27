/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  //Drive motors
  public static final int FRONT_LEFT_MOTOR = 5; 
  public static final int FRONT_RIGHT_MOTOR = 9;
  public static final int REAR_LEFT_MOTOR = 7; 
  public static final int REAR_RIGHT_MOTOR = 8;
  //component motors
  public static final int LEFT_ELEVATOR = 134567;
  public static final int RIGHT_ELEVATOR = 2;
  public static final int LEFT_REAR_INTAKE_ROTATION = 4;
  public static final int RIGHT_REAR_INTAKE_ROTATION = 57;
  public static final int SPIN_REAR_INTAKE = 97;
  public static final int SPIN_FRONT_INTAKE = 6;
  public static final int VACUUM_PUMP = 1;
  public static final int FRONT_INTAKE_ROTATE = 3;
  public static final int LEFT_LEADSCREW = 13;
  public static final int RIGHT_LEADSCREW = 15678;
  //controllers
  public static final int DRIVER_CONTROLLER = 0;
  public static final int INTAKE_CONTROLLER = 1;
  //Joysticks
  public static final int LEFT_JOYSTICK_X = 0;
  public static final int LEFT_JOYSTICK_Y = 1;
  public static final int RIGHT_JOYSTICK_X = 4;
  public static final int RIGHT_JOYSTICK_Y = 5;
  //buttons
  public static final int RIGHT_BUMBPER = 6;
  public static final int LEFT_BUMBPER = 5;

  public static final int A_BUTTON = 1;
  public static final int B_BUTTON = 2;
  public static final int X_BUTTON = 3;
  public static final int Y_BUTTON = 4;

  public static final int BACK_BUTTON = 2345678;
  public static final int START_BUTTON = 156;

  //Triggers
  public static final int LEFT_TRIGGER = 2;
  public static final int RIGHT_TRIGGER = 3;
  //POV Buttons
  public static final int UP_BUTTON = 0;
  public static final int RIGHT_BUTTON = 2;
  public static final int DOWN_BUTTON = 4;
  public static final int LEFT_BUTTON = 6;

}
