/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
<<<<<<< Updated upstream
=======
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.StartFlyWheelVelocityPID;
import frc.robot.commands.FeedPowerCells;
import frc.robot.commands.StagePowerCells;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;
>>>>>>> Stashed changes

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
<<<<<<< Updated upstream
=======
  private Command m_autonomousShooterCommand;

>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
    autoPathChooser.setDefaultOption("Test Path", 0);
    autoPathChooser.addOption("Line to Trench", 1);
    autoPathChooser.addOption("Line to Three Center Balls", 2);
=======
    autoPathChooser.setDefaultOption("AutoPath-test", 0);
    autoPathChooser.addOption("barrelRacingPath", 1);
    autoPathChooser.addOption("bouncePath", 2);
    autoPathChooser.addOption("galacticSearchA", 3);
    autoPathChooser.addOption("galacticSearchB", 4);
    autoPathChooser.addOption("slalomPath", 5);
    autoPathChooser.addOption("galacticSearchARed", 6);
    autoPathChooser.addOption("galacticSearchBRed", 7);
    autoPathChooser.addOption("bouncePathNew", 8);
    SmartDashboard.putData("Selected Path", autoPathChooser);
>>>>>>> Stashed changes
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
    m_autonomousShooterCommand = new ParallelCommandGroup(new StartFlyWheelVelocityPID(m_robotContainer.shooter), new FeedPowerCells(m_robotContainer.hopper), new StagePowerCells(m_robotContainer.hopper));

    chosenAutoPath = autoPathChooser.getSelected();
<<<<<<< Updated upstream
    
=======
    if (chosenAutoPath == 3) {
      if (m_robotContainer.getDriveTrain().isBallPresent() == false) {
        chosenAutoPath = 6;
      }
    }
    if (chosenAutoPath == 4) {
      if (m_robotContainer.getDriveTrain().isBallPresent() == false) {
        chosenAutoPath = 7;
      }
    }
    Translation2d autoTranslation2d = new Translation2d(-1, -1);
    Pose2d autoPose2d = new Pose2d(autoTranslation2d, new Rotation2d(0));
    // Gets the path selected from the auto path chooser
    // Numbers come from each path's json files starting position
    switch (chosenAutoPath) {
      case 0: 
        autoPose2d = new Pose2d(new Translation2d(1.0, 3.572), new Rotation2d(0)); 
        break;
      case 1: 
        autoPose2d = new Pose2d(new Translation2d(0.57, 2.1406491013335054), new Rotation2d(0));
        break;
      case 2: 
        autoPose2d = new Pose2d(new Translation2d(0.57, 2.1406491013335054), new Rotation2d(0));
        break;
      case 3: 
        autoPose2d = new Pose2d(new Translation2d(0.7428064162855117, 2.8519407331057143), new Rotation2d(0));
        break;
      case 4: 
        autoPose2d = new Pose2d(new Translation2d(0.7304361270372994, 2.5364983572763), new Rotation2d(0));
        break;
      case 5: 
        autoPose2d = new Pose2d(new Translation2d(0.526326354441796, 0.421178895831992), new Rotation2d(0));
        break;
      case 6: 
        autoPose2d = new Pose2d(new Translation2d(0.7180658377890871, 2.1282788120852936), new Rotation2d(0));
        break;
      case 7:  
        autoPose2d = new Pose2d(new Translation2d(0.6995104039167684, 1.9179838948656838), new Rotation2d(0));
        break;
      case 8: 
        autoPose2d = new Pose2d(new Translation2d(0.22325426786059388, 2.190130258326355), new Rotation2d(0));
        break;
      case 9: 
        autoPose2d = new Pose2d(new Translation2d(3.126133599984564, 5.890856109636205), new Rotation2d(0.41450687458478436));
        break;
      default: 
        autoPose2d = new Pose2d(new Translation2d(1.0, 3.572), new Rotation2d(0));
        break;
    }

    m_robotContainer.getDriveTrain().resetOdometry(autoPose2d);
    // m_robotContainer.getDriveTrain().zeroHeading();
>>>>>>> Stashed changes
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();
    Command m_autoSequentialGroup = new SequentialCommandGroup(m_autonomousCommand, new ParallelDeadlineGroup(new WaitCommand(1.0), new StartFlyWheelVelocityPID(m_robotContainer.shooter)), m_autonomousShooterCommand); 
    
    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
<<<<<<< Updated upstream
      m_autonomousCommand.schedule();
    }

    //Gets the path selected from the auto path chooser
    
=======
      // m_autonomousCommand.schedule();
      m_autoSequentialGroup.schedule();

      
    }
>>>>>>> Stashed changes
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
      m_autonomousShooterCommand.cancel();
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

  // //Encapsulates the shooter hood commands
  // public void shooterHoodHandler() {
  //   operatorBButton.whenPressed(new HoodAngleUp(shooter));
  //   operatorXButton.whenPressed(new HoodAngleDown(shooter));
  // }

  // public void hopperHandler() {
  //   operatorRBumper.whenHeld(new StagePowerCells(hopper));
  //   operatorYButton.whenHeld(new FeedPowerCells(hopper));
  // }

  // public void controlPanelHandler() {
  //   driverLBumper.whenHeld(new TurnControlPanelLeft(controlPanel));
  //   driverRBumper.whenHeld(new TurnControlPanelRight(controlPanel));
  // }
  

}
