/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.DriveTrain;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  //Declares the robot objects
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;
//Declares the chooser objects
  private SendableChooser<Integer> autoPathChooser;
  public static int chosenAutoPath;
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();    
    //Puts the target shooter RPM on the shuffleboard
    SmartDashboard.putNumber("Shooter Target RPM", Constants.SHOOTER_DEFAULT_TARGET_RPM);
    LiveWindow.disableAllTelemetry();
    m_robotContainer.trajectoryManager.LoadAllPaths();

    //Instantiates the auto path chooser and adds its options
    autoPathChooser = new SendableChooser<Integer>();
    autoPathChooser.setDefaultOption("testPath", 0);
    autoPathChooser.addOption("barrelRacingPath", 1);
    autoPathChooser.addOption("bouncePath", 2);
    autoPathChooser.addOption("galacticSearchA", 3);
    autoPathChooser.addOption("galacticSearchB", 4);
    autoPathChooser.addOption("slalomPath", 5);
    autoPathChooser.addOption("galacticSearchARed", 6);
    autoPathChooser.addOption("galacticSearchBRed", 7);
    autoPathChooser.addOption("bouncePathNew", 8);
    SmartDashboard.putData("Selected Path", autoPathChooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    chosenAutoPath = autoPathChooser.getSelected();
    if(chosenAutoPath == 3) {
      if(m_robotContainer.getDriveTrain().isBallPresent()) {
        chosenAutoPath = 6;
      }
    }
    if(chosenAutoPath == 4) {
      if(m_robotContainer.getDriveTrain().isBallPresent()) {
        chosenAutoPath = 7;
      }
    }
    Translation2d autoTranslation2d = new Translation2d(-1,-1);
    if(chosenAutoPath == 0) {
      autoTranslation2d = new Translation2d(1.0, 3.572);
    }
    if(chosenAutoPath == 1) {
      autoTranslation2d = new Translation2d(0.57, 2.1406491013335054);
    }
    if(chosenAutoPath == 2) {
      autoTranslation2d = new Translation2d(1.2230110923987334, 2.302863659164286);
    }
    if(chosenAutoPath == 3) {
      autoTranslation2d = new Translation2d(0.7286354701686436, 2.3023361101631967);
    }
    if(chosenAutoPath == 4) {
      autoTranslation2d = new Translation2d(0.7459829396831342, 2.9049971112062414);
    }
    if(chosenAutoPath == 5) {
      autoTranslation2d = new Translation2d(0.526326354441796, 0.421178895831992);
    }
    if(chosenAutoPath == 6) {
      autoTranslation2d = new Translation2d(0.7286354701686436, 1.670864716165513);
    }
    if(chosenAutoPath == 7) {
      autoTranslation2d = new Translation2d(0.7057997147435722, 1.6420957559628646);
    } 
    if(chosenAutoPath == 8) {
      autoTranslation2d = new Translation2d(0.22325426786059388, 2.190130258326355);
    }
    Pose2d autoPose2d = new Pose2d(autoTranslation2d, new Rotation2d(0));
    m_robotContainer.getDriveTrain().resetOdometry(autoPose2d);
    //m_robotContainer.getDriveTrain().zeroHeading();
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }

    //Gets the path selected from the auto path chooser
    
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /*
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

    }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }  

}
