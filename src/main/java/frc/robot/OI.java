/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import frc.robot.commands.RotateGyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());

  //declarations
    //none at the momment

  public OI(){
    OI.yButton.whenPressed(new RotateGyro(0.65, -90));  
    OI.bButton.whenPressed(new RotateGyro(0.65, 90));
    //OI.upButton.whenPressed(new TurnToAngle(0.35, 0));
    //OI.rightButton.whenPressed(new TurnToAngle(0.35, 90));
    //OI.downButton.whenPressed(new TurnToAngle(0.35, 180));
    //OI.leftButton.whenPressed(new TurnToAngle(0.35, 270));

  }   

  //methods  
  public double getLeftJoystickX(Joystick joy){       //gets the left joysticks corrected x axis
    return joy.getRawAxis(RobotMap.LEFT_JOYSTICK_X);
  }
  public double getLeftTriggerAxis(Joystick joy){      //gets the left triggers corrected axis
    return joy.getRawAxis(RobotMap.LEFT_TRIGGER);
  }
  public double getRightTriggerAxis(Joystick joy){     //gets the right triggers corrected axis (inverted)
    return -1*joy.getRawAxis(RobotMap.RIGHT_TRIGGER);
  }

  //creating controllers NOTE: in wpilib joystick = controller
  public static Joystick driverController = new Joystick(RobotMap.DRIVER_CONTROLLER);
  public static Joystick intakeController = new Joystick(RobotMap.INTAKE_CONTROLLER);

  //creating buttons
  public static JoystickButton rightBumbper = new JoystickButton(driverController, RobotMap.RIGHT_BUMBPER);
  public static JoystickButton leftBumbper = new JoystickButton(driverController, RobotMap.LEFT_BUMBPER);

  public static JoystickButton xButton = new JoystickButton(driverController, RobotMap.X_BUTTON);
  public static JoystickButton bButton = new JoystickButton(driverController, RobotMap.B_BUTTON);
  public static JoystickButton yButton = new JoystickButton(driverController, RobotMap.Y_BUTTON);
  public static JoystickButton aButton = new JoystickButton(driverController, RobotMap.A_BUTTON);

  public static JoystickButton xButton1 = new JoystickButton(intakeController, RobotMap.X_BUTTON);
  public static JoystickButton bButton1 = new JoystickButton(intakeController, RobotMap.B_BUTTON);
  public static JoystickButton yButton1 = new JoystickButton(intakeController, RobotMap.Y_BUTTON);
  public static JoystickButton aButton1 = new JoystickButton(intakeController, RobotMap.A_BUTTON);

  public static POVButton upButton = new POVButton(driverController, 0, RobotMap.UP_BUTTON);
  public static POVButton rightButton = new POVButton(driverController, 90, RobotMap.RIGHT_BUTTON);
  public static POVButton downButton = new POVButton(driverController, 180, RobotMap.DOWN_BUTTON);
  public static POVButton leftButton = new POVButton(driverController, 270, RobotMap.LEFT_BUTTON);

  public static POVButton upButton1 = new POVButton(intakeController, 0, RobotMap.UP_BUTTON);
  public static POVButton rightButton1 = new POVButton(intakeController, 90, RobotMap.RIGHT_BUTTON);
  public static POVButton downButton1 = new POVButton(intakeController, 180, RobotMap.DOWN_BUTTON);
  public static POVButton leftButton1 = new POVButton(intakeController, 270, RobotMap.LEFT_BUTTON);

}


