
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
//import edu.wpi.first.wpilibj.buttons.POVButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  public Joystick joystick0 =  new Joystick(0), joystick1 = new Joystick(1);
  //joystick0/ the first joystick
  public Button A0 = new JoystickButton(joystick0, 0);
  public Button B0 = new JoystickButton(joystick0, 1);
  public Button X0 = new JoystickButton(joystick0, 2);
  public Button Y0 = new JoystickButton(joystick0, 3);
  //joystick1/ the second joystick
  public Button A1 = new JoystickButton(joystick1, 0);
  public Button B1 = new JoystickButton(joystick1, 1);
  public Button X1 = new JoystickButton(joystick1, 2);
  public Button Y1 = new JoystickButton(joystick1, 3);
  //public POVButton Lpov = new POVButton(joystick1, 270);
  //public POVButton Upov = new POVButton(joystick, angle, povNumber);
  //public POVButton Rpov = new POVButton(joystick, angle);


  public double getJoystick0X(){
    return joystick0.getX();
  }
  public double getJoystick1X(){
    return joystick1.getX();
  }
  public double getJoystick0Y(){
    return joystick0.getY();
  }


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
}
