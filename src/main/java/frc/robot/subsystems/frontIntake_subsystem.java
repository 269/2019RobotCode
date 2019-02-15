/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.frontIntakeRollers;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * Add your docs here.
 */
public class frontIntake_subsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  WPI_TalonSRX spinFrontIntake = null;

  public frontIntake_subsystem() {

    spinFrontIntake = new WPI_TalonSRX(RobotMap.SPIN_FRONT_INTAKE);
 
  }

  double tolerance = 0.1;

  public void rollerSpeed( double rollerSpeed){

    if(rollerSpeed >= -tolerance && rollerSpeed <= tolerance){
      rollerSpeed = 0;
      spinFrontIntake.set(0);
    }
    else{
      spinFrontIntake.set(rollerSpeed);
    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new frontIntakeRollers());
  }
}
