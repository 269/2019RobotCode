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
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * Add your docs here.
 */
public class rearIntakeRollers_subsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  WPI_TalonSRX spinRearIntake = null;
  
public rearIntakeRollers_subsystem(){

 spinRearIntake = new WPI_TalonSRX(RobotMap.SPIN_REAR_INTAKE);

}

double rollerSpeed = 0.5;
/**
 * 
 * @param rollerSpeed the speed of the motors (-1.0 to 1.0)
 */
public void rollerSpeed( double rollerSpeed){
  if(Robot.m_oi.xButton1.get()){
    spinRearIntake.set(rollerSpeed);
  }
  if(Robot.m_oi.yButton1.get()){
    spinRearIntake.set(-rollerSpeed);
  }
  else{
    spinRearIntake.set(0);
  }

}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    // setDefaultCommand(new rearIntakeRollers());

  }
}
