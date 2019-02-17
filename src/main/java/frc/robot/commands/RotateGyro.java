/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDBase.Tolerance;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.driveTrain_subsystem;

public class RotateGyro extends Command {

  public final double YAWTOLERANCE = 1.25; //the tolerance to avoid overshooting
  public double goalYaw; //the goal angle
  public double turnYaw; // the Yaw needed to get to goal
  public double startingYaw; //the angle the robot is at
  public double speed; //speed for turning
  public double currentYaw;
  public boolean turnCW;//it's true when turning clockwise
  public final double MINSPEED = 0.2;//min speed can turn
  public final double SLOWYAW = 60;// angle we want to start slowing down
  public double initialSpeed;//starting speed

  public RotateGyro() {
    requires(Robot.driveTrain_subsystem);
  }

  public RotateGyro( double speed, double goalYaw) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
      this.speed = speed;
      this.initialSpeed = speed;
      this.goalYaw = goalYaw;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() { //here is an error somewhere
    startingYaw = Robot.navx.getFusedHeading();
    System.out.println("Starting Heading: " + startingYaw);
     }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
    if(goalYaw >= 0) { // turning clockwise
      turnYaw = startingYaw + goalYaw;
      turnCW = true;
      if( turnYaw > 360){
        turnYaw = turnYaw - 360;
      }
    }
    else if (goalYaw < 0) { //turning counterclockwise
      turnYaw = startingYaw + 360 + goalYaw;
      turnCW = false;
      if( turnYaw >= 360){
        turnYaw = turnYaw - 360;
      }
    }
/*
      if( currentYaw < turnYaw){
        speed = (turnYaw - currentYaw) / SLOWYAW * speed;
      } 
  
      if( currentYaw > turnYaw){
        speed = -1 * ((turnYaw - currentYaw) / SLOWYAW * speed);
      }
*/
   /* if(currentYaw <= (turnYaw*0.5)){
      speed = speed;
    }
    */
    if(currentYaw < turnYaw){
      if(currentYaw >= (startingYaw + goalYaw*0.5)){
        speed = speed*0.85;
      }
      else{
        speed = initialSpeed;
      }
    }
    if(currentYaw > turnYaw){
      if(startingYaw + (goalYaw*0.5) > 360){
       if(currentYaw >= (startingYaw + goalYaw*0.5) - 360){
        speed = speed*0.85;
       }
      }
      else if(startingYaw +(goalYaw*0.5) < 360) {
        if(currentYaw >= ( 360 -(startingYaw + goalYaw*0.5))){
          speed = speed*0.85;
        }
      }
      else {
        speed = initialSpeed;
      }
    }

    if(speed < MINSPEED){
      speed = MINSPEED;
    }

    if(turnCW) { // if the robot is turning clockwise
        Robot.driveTrain_subsystem.drive( 0 ,0 , speed );
    }
    else if(turnCW == false) { // if the robot is turning counterclockwise
      Robot.driveTrain_subsystem.drive( 0 ,0 , -speed );
    }
      
    System.out.println("speed : " + speed );
    //currentYaw = Robot.navx.getFusedHeading();
    //System.out.println("current Yaw : " + currentYaw);
    //System.out.println("Fused heading: " + Robot.navx.getFusedHeading());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    currentYaw = Robot.navx.getFusedHeading();
    if(turnCW){ //decides if its done rotating or not if in the range
      if( currentYaw <= turnYaw + YAWTOLERANCE && currentYaw >= turnYaw - YAWTOLERANCE) {
        return true;
      } else {
        return false;
      }
    } else {
      if( currentYaw <= turnYaw + YAWTOLERANCE && currentYaw >= turnYaw - YAWTOLERANCE) {
        return true;
    } else{
      return false;
    }
  }
}

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveTrain_subsystem.drive(0, 0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
