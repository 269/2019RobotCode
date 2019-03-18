/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.driveTrain_subsystem;
import frc.robot.subsystems.rearIntakeRotation_subsystem;
import frc.robot.subsystems.elevator_subsystem;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.networktables.NetworkTable;
import frc.robot.subsystems.frontIntake_subsystem;
import frc.robot.subsystems.frontIntakeRotation_subsystem;
import frc.robot.subsystems.Vacuum_subsystem;
import frc.robot.subsystems.leadScrew_subsystem;
import frc.robot.subsystems.rearIntakeRollers_subsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static final boolean DEBUG = true;    //turns on certian debugable console outputs
  public static OI m_oi;                       
  public static AHRS navx;                     //the kauailabs navx navigation sensor
  public static boolean errStatus;               //stores error status of navx initalization
  public static NetworkTableEntry targetValue; //idk something to do with getting pixycam traget value

  //declare subsystems
  public static driveTrain_subsystem driveTrain_subsystem = null;
  public static rearIntakeRotation_subsystem rearIntakeRotation = null;
  public static elevator_subsystem elevator_subsystem = null;
  public static frontIntake_subsystem frontIntake = null;
  public static frontIntakeRotation_subsystem frontIntakeRotation = null;
  public static rearIntakeRollers_subsystem rearIntakeRollers_subsystem = null;
  public static Vacuum_subsystem vacuum = null;
  public static leadScrew_subsystem leadScrews = null;

  public Robot(){

    //initalize navx over roborio MXP SPI port
    try{
      navx = new AHRS(SPI.Port.kMXP);
      System.out.println("it's trying its best fam");
      errStatus = false;
    } catch (RuntimeException ex ) {
        System.out.println("Error instantiating navX-MXP:  " + ex.getMessage());
        errStatus = true;
    }
    //add code to check error status and determin if there was an error     
  }

  //gets auto mode from dashboard
  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    //calling our subystems to run
    
    driveTrain_subsystem = new driveTrain_subsystem();
    rearIntakeRollers_subsystem = new rearIntakeRollers_subsystem();
    rearIntakeRotation = new rearIntakeRotation_subsystem();
    elevator_subsystem = new elevator_subsystem();
    frontIntake = new frontIntake_subsystem();
    frontIntakeRotation = new frontIntakeRotation_subsystem();
    vacuum = new Vacuum_subsystem();
    leadScrews = new leadScrew_subsystem();
    m_oi = new OI();

    //adds options of commands to run to smart dashboard for auto modes
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);

    //get pixie camera target x pos of vision tape
    NetworkTableInstance inst = NetworkTableInstance.getDefault();
    NetworkTable table = inst.getTable("pixieCamera");
    targetValue = table.getEntry("targetXPOS"); //returns the x position on the screen of where the middle of the target is
    System.out.println("targetvalueXPOS " + targetValue);

    elevator_subsystem.elevatorLeft.configFactoryDefault();
    elevator_subsystem.elevatorRight.configFactoryDefault();
    m_oi.bind(); //bind the buttons to commands
  }
   
  /**
   * 
   * @return returns 360 Yaw vs the 180 to -180
   */
  public static double getFullYaw() {
    if(Robot.navx.getYaw() <= 0)
      return -Robot.navx.getYaw();
    else
      return 360 - Robot.navx.getYaw();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override 
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    //add code to reset the fusedHeading and add delay so it finishes resetting
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    //only reset fusedheading if not already reset from autoinit
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    
    if(DEBUG){
      // System.out.println("Compass head : " + navx.getCompassHeading());
      System.out.println("Fused Heading : " + navx.getFusedHeading());
      //System.out.println("Yaw : " + getFullYaw());
      System.out.println("Target Value: " + targetValue);
    }
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

}
