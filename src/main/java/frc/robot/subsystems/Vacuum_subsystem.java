/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Direction;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.VacuumButtons;
/**
 * mechanism for picking up hatch panels 
 */
public class Vacuum_subsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  Relay vacuum = null;

  public Vacuum_subsystem(){
    vacuum = new Relay(RobotMap.VACUUM_PUMP, Direction.kForward);
  }
/**
 * 
 * @param vacuumSuck on or off from command
 */
  public void vacuumSucktion(boolean vacuumSuck){
    if(vacuumSuck == true){
      vacuum.set(Relay.Value.kOn);
    }
    else{
      vacuum.set(Relay.Value.kOff);
    }
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new VacuumButtons());
  }
}
