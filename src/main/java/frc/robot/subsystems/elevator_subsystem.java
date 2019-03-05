/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
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

boolean bottomLimit = false;
boolean topLimit = false;

/**
 * 
 * @param speed 0.0 to -1.0 moves elevator down & 0.0to 1.0 moves elevator up
 */
public void move(double speed){//moving the elevator up or down depending on the speed (positive or negative)
  if(speed <= -0.1 && !bottomLimit){ //move down
    //stops elevator going to low based on encoders
    elevatorLeft.set(speed);
  }else if(speed >= 0.1 && !topLimit ){//move up
    //stops elevator going to high based on encoders
    elevatorLeft.set(speed);
  }else{
    elevatorLeft.set(0);
  } 
  //System.out.println("Elevator pos: " + elevatorLeft.getSelectedSensorPosition());
}

public void PIDControl(double setpoint) {
  elevatorLeft.set(ControlMode.MotionMagic, setpoint);
}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
     setDefaultCommand(new elevatorManualLift());
  }
}
