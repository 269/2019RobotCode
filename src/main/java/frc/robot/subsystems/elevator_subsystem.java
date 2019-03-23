/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Conversions;
import frc.robot.RobotMap;
import frc.robot.commands.elevatorManualLift;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * It makes the elevator go up and down
 * 
 */
public class elevator_subsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public WPI_TalonSRX elevatorLeft = null;
  public WPI_TalonSRX elevatorRight = null; 
  public Encoder elevatorEncoder;
  public final double LEVEL_ONE_HEIGHT_HATCH = 0;
  public final double LEVEL_TWO_HEIGHT_HATCH = 16;
  public final double LEVEL_THREE_HEIGHT_HATCH = 43;
  public final double LEVEL_ONE_HEIGHT_CARGO = 13;
  public final double LEVEL_TWO_HEIGHT_CARGO = 33;
  public final double LEVEL_THREE_HEIGHT_CARGO = 53;
  public final double TOP_LIMIT = 55;
  public final double BOTTOM_LIMIT = 0;
  public final double CARGO_PICKUP = 12;
  public final double HATCH_PICKUP = 3;

public elevator_subsystem(){
  elevatorRight = new WPI_TalonSRX(RobotMap.RIGHT_ELEVATOR);
  elevatorLeft = new WPI_TalonSRX(RobotMap.LEFT_ELEVATOR);
  elevatorEncoder = new Encoder(RobotMap.ELEVATOR_ENCODERA, RobotMap.ELEVATOR_ENCODERB);

  elevatorRight.follow(elevatorLeft); // Makes elevatorLeft the primary motor controller (USE EVERYWHERE ELSE PLEASE)

  // Set PID Constant values on motor controller
  elevatorLeft.config_kP(RobotMap.ELEVATOR_CONSTANTS.slotIdx, RobotMap.ELEVATOR_CONSTANTS.kP, RobotMap.ELEVATOR_CONSTANTS.kTimeOutMs);
  elevatorLeft.config_kI(RobotMap.ELEVATOR_CONSTANTS.slotIdx, RobotMap.ELEVATOR_CONSTANTS.kI, RobotMap.ELEVATOR_CONSTANTS.kTimeOutMs);
  elevatorLeft.config_kD(RobotMap.ELEVATOR_CONSTANTS.slotIdx, RobotMap.ELEVATOR_CONSTANTS.kD, RobotMap.ELEVATOR_CONSTANTS.kTimeOutMs);
  elevatorLeft.config_kF(RobotMap.ELEVATOR_CONSTANTS.slotIdx, RobotMap.ELEVATOR_CONSTANTS.kF, RobotMap.ELEVATOR_CONSTANTS.kTimeOutMs);

  elevatorLeft.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, RobotMap.ELEVATOR_CONSTANTS.kPIDLoopIdx,RobotMap.ELEVATOR_CONSTANTS.kTimeOutMs);
  elevatorLeft.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, RobotMap.ELEVATOR_CONSTANTS.kTimeOutMs);
  elevatorLeft.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, RobotMap.ELEVATOR_CONSTANTS.kTimeOutMs);
  elevatorLeft.configNominalOutputForward(0, RobotMap.ELEVATOR_CONSTANTS.kTimeOutMs);
  elevatorLeft.configNominalOutputReverse(0, RobotMap.ELEVATOR_CONSTANTS.kTimeOutMs);
  elevatorLeft.configPeakOutputForward(1, RobotMap.ELEVATOR_CONSTANTS.kTimeOutMs);
  elevatorLeft.configPeakOutputReverse(-1, RobotMap.ELEVATOR_CONSTANTS.kTimeOutMs);
  elevatorLeft.selectProfileSlot(RobotMap.ELEVATOR_CONSTANTS.slotIdx, RobotMap.ELEVATOR_CONSTANTS.kPIDLoopIdx);
  elevatorLeft.configMotionCruiseVelocity(RobotMap.ELEVATOR_CONSTANTS.cruiseVelocity, RobotMap.ELEVATOR_CONSTANTS.kTimeOutMs);
  elevatorLeft.configMotionAcceleration(RobotMap.ELEVATOR_CONSTANTS.acceleration, RobotMap.ELEVATOR_CONSTANTS.kTimeOutMs);
  elevatorLeft.setSelectedSensorPosition(0, RobotMap.ELEVATOR_CONSTANTS.kPIDLoopIdx, RobotMap.ELEVATOR_CONSTANTS.kTimeOutMs);
  elevatorLeft.configForwardSoftLimitThreshold(RobotMap.ELEVATOR_CONSTANTS.forwardSensorLimit, RobotMap.ELEVATOR_CONSTANTS.kTimeOutMs);
  elevatorLeft.configReverseSoftLimitThreshold(RobotMap.ELEVATOR_CONSTANTS.reverseSensorLimit, RobotMap.ELEVATOR_CONSTANTS.kTimeOutMs);
  //elevatorLeft.setSensorPhase(true); //for inverting the sensor
}


/**
 * 
 * @param speed 0.0 to -1.0 moves elevator down & 0.0to 1.0 moves elevator up
 */
public void move(double liftSpeed){//moving the elevator up or down depending on the speed (positive or negative)
  double currentpos = getCurrentPosition();
  elevatorLeft.set(liftSpeed);
  /*if ((Math.abs(liftSpeed) > 0.05) && ((liftSpeed > 0 && currentpos < TOP_LIMIT) || (liftSpeed < 0 && currentpos > BOTTOM_LIMIT))){
      elevatorLeft.set(liftSpeed);
    } else {
     elevatorLeft.set(0);
    }
    */

  System.out.println("Lift speed: " + liftSpeed);
  System.out.println("Encoder Top:" + TOP_LIMIT);
  System.out.println("Encoder Bot:" + BOTTOM_LIMIT);
  System.out.println("current pos:" + currentpos);

  SmartDashboard.putString("elevatorClosestPosition", "M-" + getClosestPosition());
  //System.out.println("Elevator pos: " + elevatorLeft.getSelectedSensorPosition());

}

/**
 * 
 * @return character code for closest position. (CPU,HPU,TOP,BOT,H#,C#) # = the level 1-3
 */
public String getClosestPosition(){
  int currentPos = elevatorLeft.getSelectedSensorPosition();
  double inches = Conversions.ticksToInches(currentPos, Conversions.Subsystem.ELEVATOR);
  double mindiff = Math.abs(LEVEL_ONE_HEIGHT_HATCH - inches);
  String closestPos = "H1";
    if(Math.abs(LEVEL_TWO_HEIGHT_HATCH - inches)< mindiff){
      mindiff = Math.abs(LEVEL_TWO_HEIGHT_HATCH - inches);
      closestPos = "H2";
    }
    if(Math.abs(LEVEL_THREE_HEIGHT_HATCH - inches)< mindiff){
      mindiff = Math.abs(LEVEL_THREE_HEIGHT_HATCH - inches);
      closestPos = "H3";
    }
    if(Math.abs(LEVEL_ONE_HEIGHT_CARGO - inches)< mindiff){
      mindiff = Math.abs(LEVEL_ONE_HEIGHT_CARGO - inches);
      closestPos = "C1";
    }
    if(Math.abs(LEVEL_TWO_HEIGHT_CARGO - inches)< mindiff){
      mindiff = Math.abs(LEVEL_TWO_HEIGHT_CARGO - inches);
      closestPos = "C2";
    }
    if(Math.abs(LEVEL_THREE_HEIGHT_CARGO - inches)< mindiff){
      mindiff = Math.abs(LEVEL_THREE_HEIGHT_CARGO - inches);
      closestPos = "C3";
    }
    if(Math.abs(HATCH_PICKUP - inches)< mindiff){
      mindiff = Math.abs(HATCH_PICKUP - inches);
      closestPos = "HPU";
    }
    if(Math.abs(CARGO_PICKUP - inches)< mindiff){
      mindiff = Math.abs(CARGO_PICKUP - inches);
      closestPos = "CPU";
    }
    if(Math.abs(TOP_LIMIT - inches)< mindiff){
      mindiff = Math.abs(TOP_LIMIT - inches);
      closestPos = "TOP";
    }
    if(Math.abs(BOTTOM_LIMIT - inches)< mindiff){
      mindiff = Math.abs(BOTTOM_LIMIT - inches);
      closestPos = "BOT";
    }
    return closestPos;
}
/**
 * 
 * @return the current elevator position in inches
 */
public double getCurrentPosition(){
  int currentPos = elevatorLeft.getSelectedSensorPosition();
  return Conversions.ticksToInches(currentPos, Conversions.Subsystem.ELEVATOR);
}
/**
 * 
 * @param setpoint the disired potion to set the elevator to in ticks
 */
public void PIDControl(double setpoint) {
  elevatorLeft.set(ControlMode.MotionMagic, setpoint);
  SmartDashboard.putString("elevatorClosestPosition", getClosestPosition());
}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
     setDefaultCommand(new elevatorManualLift());
  }
}
