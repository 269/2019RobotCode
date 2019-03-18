/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import frc.robot.commands.RotateGyro;
import frc.robot.commands.elevatorPIDCargoPos;
import frc.robot.commands.elevatorPIDHatchPos;
import frc.robot.commands.turnToAngle;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;

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
    // yButton.whenPressed(new RotateGyro(0.65, -90));  
    //bButton.whenPressed(new RotateGyro(0.65, 90)); 
    upButton.whenPressed(new turnToAngle(0.35, 0));
    rightButton.whenPressed(new turnToAngle(0.35, 90));
    downButton.whenPressed(new turnToAngle(0.35, 180));
    leftButton.whenPressed(new turnToAngle(0.35, 270));

    //elevator preset positions
    upButton1.whenPressed(new elevatorPIDHatchPos("up"));
    downButton1.whenPressed(new elevatorPIDHatchPos("down"));
    rightButton1.whenPressed(new elevatorPIDCargoPos("up"));
    leftButton1.whenPressed(new elevatorPIDCargoPos("down"));

    //Rear Intake
    //xButton1.whenPressed(new rearIntakeRollers());
    //yButton1.whenPressed(new rearIntakeRollers());
  }   

  //methods  
  /**
   * 
   * @param controller the USB controller you want 
   * @return  returns the x axis left joystick (-1.0 - 1.0)
   */
  public double getLeftJoystickX(Joystick controller){       //gets the left joysticks corrected x axis
    return controller.getRawAxis(RobotMap.LEFT_JOYSTICK_X);
  }
    /**
   * 
   * @param controller the USB controller you want 
   * @return  returns the y axis left joystick (-1.0 - 1.0)
   */
  public double getLeftJoystickY(Joystick controller){       //gets the left joysticks corrected y axis
    return controller.getRawAxis(RobotMap.LEFT_JOYSTICK_Y);
  }
/**
 * 
 * @param controller the USB controller you want
 * @return returns the left trigger (0.0 to 1.0)
 */
  public double getLeftTriggerAxis(Joystick controller){      //gets the left triggers corrected axis
    return controller.getRawAxis(RobotMap.LEFT_TRIGGER);
  }
  /**
   * 
   * @param controller the USB controller you want
   * @return returns the right trigger (0.0 to -1.0)
   */
  public double getRightTriggerAxis(Joystick controller){     //gets the right triggers corrected axis (inverted)
    return -1*controller.getRawAxis(RobotMap.RIGHT_TRIGGER);
  }

  //creating controllers NOTE: in wpilib joystick = controller
  public Joystick driverController = new Joystick(RobotMap.DRIVER_CONTROLLER);
  public Joystick intakeController = new Joystick(RobotMap.INTAKE_CONTROLLER);

  //creating buttons
  public JoystickButton leftJoyPress = new JoystickButton(driverController, RobotMap.LEFT_JOY_DOWN);

  public JoystickButton rightBumbper = new JoystickButton(driverController, RobotMap.RIGHT_BUMBPER);
  public JoystickButton leftBumbper = new JoystickButton(driverController, RobotMap.LEFT_BUMBPER);
  public JoystickButton rightBumbper1 = new JoystickButton(intakeController, RobotMap.RIGHT_BUMBPER);
  public JoystickButton leftBumbper1 = new JoystickButton(intakeController, RobotMap.LEFT_BUMBPER);

  public JoystickButton backButton = new JoystickButton(intakeController, RobotMap.BACK_BUTTON);
  public JoystickButton startButton = new JoystickButton(intakeController, RobotMap.START_BUTTON);

  public JoystickButton xButton = new JoystickButton(driverController, RobotMap.X_BUTTON);
  public JoystickButton bButton = new JoystickButton(driverController, RobotMap.B_BUTTON);
  public JoystickButton yButton = new JoystickButton(driverController, RobotMap.Y_BUTTON);
  public JoystickButton aButton = new JoystickButton(driverController, RobotMap.A_BUTTON);

  public JoystickButton xButton1 = new JoystickButton(intakeController, RobotMap.X_BUTTON);
  public JoystickButton bButton1 = new JoystickButton(intakeController, RobotMap.B_BUTTON);
  public JoystickButton yButton1 = new JoystickButton(intakeController, RobotMap.Y_BUTTON);
  public JoystickButton aButton1 = new JoystickButton(intakeController, RobotMap.A_BUTTON);

  public POVButton upButton = new POVButton(driverController, 0, RobotMap.UP_BUTTON);
  public POVButton rightButton = new POVButton(driverController, 90, RobotMap.RIGHT_BUTTON);
  public POVButton downButton = new POVButton(driverController, 180, RobotMap.DOWN_BUTTON);
  public POVButton leftButton = new POVButton(driverController, 270, RobotMap.LEFT_BUTTON);

  public POVButton upButton1 = new POVButton(intakeController, 0, RobotMap.UP_BUTTON);
  public POVButton rightButton1 = new POVButton(intakeController, 90, RobotMap.RIGHT_BUTTON);
  public POVButton downButton1 = new POVButton(intakeController, 180, RobotMap.DOWN_BUTTON);
  public POVButton leftButton1 = new POVButton(intakeController, 270, RobotMap.LEFT_BUTTON);

}


