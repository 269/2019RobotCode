/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.commands.leadScrewJoystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * controls the lead screws going up and down
 */
public class leadScrew_subsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  WPI_TalonSRX leftLeadScrew = null;
  WPI_TalonSRX rightLeadScrew = null;

  public leadScrew_subsystem(){

    leftLeadScrew = new WPI_TalonSRX(RobotMap.LEFT_LEADSCREW);
    rightLeadScrew = new WPI_TalonSRX(RobotMap.RIGHT_LEADSCREW);
  }

  double tolerance = 0.1;
/**
 * 
 * @param motorSpeed the motor speed of the lead screws (-1.0 to 1.0)
 */
  public void motorSpeed( double motorSpeed){
    if(motorSpeed >= -tolerance && motorSpeed < tolerance){
      leftLeadScrew.set(0);
      rightLeadScrew.set(0);
    }
    else{
      leftLeadScrew.set(motorSpeed);
      rightLeadScrew.set(motorSpeed);
    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new leadScrewJoystick());
  }
}
