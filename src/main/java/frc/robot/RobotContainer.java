/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.List;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import frc.robot.Robot;
import frc.robot.Constants;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  //Instantiates the subsystems
  Shooter shooter = new Shooter();
  DriveTrain driveTrain = new DriveTrain();
  Hopper hopper = new Hopper();
  Intake intake = new Intake();
  Turret turret = new Turret();
  ControlPanel controlPanel = new ControlPanel();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  XboxController driverController = new XboxController(Constants.DRIVER_CONTROLLER_PORT);
  JoystickButton driverLBumper = new JoystickButton(driverController, XboxController.Button.kBumperLeft.value);
  JoystickButton driverRBumper = new JoystickButton(driverController, XboxController.Button.kBumperRight.value);

  XboxController operatorController = new XboxController(Constants.OPERATOR_CONTROLLER_PORT);
  JoystickButton operatorAButton = new JoystickButton(operatorController, XboxController.Button.kA.value);
  JoystickButton operatorBButton = new JoystickButton(operatorController, XboxController.Button.kB.value);
  JoystickButton operatorXButton = new JoystickButton(operatorController, XboxController.Button.kX.value);
  JoystickButton operatorYButton = new JoystickButton(operatorController, XboxController.Button.kY.value);
  JoystickButton operatorLBumper = new JoystickButton(operatorController, XboxController.Button.kBumperLeft.value);
  JoystickButton operatorRBumper = new JoystickButton(operatorController, XboxController.Button.kBumperRight.value);
  JoystickButton operatorBackButton = new JoystickButton(operatorController, XboxController.Button.kBack.value);


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    operatorAButton.whenHeld(new StartFlyWheelVelocityPID(shooter));
    operatorLBumper.whenHeld(new IntakePowerCells(intake));
    shooterHoodHandler();
    hopperHandler();
    controlPanelHandler();

    
  }
  //Encapsulates the shooter hood commands
  public void shooterHoodHandler() {
    operatorBButton.whenPressed(new HoodAngleUp(shooter));
    operatorXButton.whenPressed(new HoodAngleDown(shooter));
  }

  public void hopperHandler() {
    operatorRBumper.whenHeld(new StagePowerCells(hopper));
    operatorYButton.whenHeld(new FeedPowerCells(hopper));
  }

  public void controlPanelHandler() {
    driverLBumper.whenHeld(new TurnControlPanelLeft(controlPanel));
    driverRBumper.whenHeld(new TurnControlPanelRight(controlPanel));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    var autoVoltageConstraint = new DifferentialDriveVoltageConstraint(new SimpleMotorFeedforward(Constants.ksVolts, Constants.kvVoltSecondsPerInch, Constants.kaVoltSecondsSquaredPerInch), Constants.kDriveKinematics, 10);

    TrajectoryConfig config = new TrajectoryConfig(Constants.kMaxSpeedInchesPerSecond, Constants.kMaxAccelerationInchesPerSecondSquared).setKinematics(Constants.kDriveKinematics).addConstraint(autoVoltageConstraint);
    
    Trajectory autoOne = TrajectoryGenerator.generateTrajectory(new Pose2d(0, 0, new Rotation2d(0)), 
      List.of(new Translation2d(1, 1), new Translation2d(2, -1)), 
      new Pose2d(3, 0, new Rotation2d(0)), config);
    
    RamseteCommand ramseteCommand = new RamseteCommand(
      autoOne, 
      driveTrain::getPose, 
      new RamseteController(Constants.kRamseteB, Constants.kRamseteZeta), 
      new SimpleMotorFeedforward(Constants.ksVolts, 
                                 Constants.kvVoltSecondsPerInch,
                                 Constants.kaVoltSecondsSquaredPerInch),
      Constants.kDriveKinematics,
      driveTrain::getWheelSpeeds,
      new PIDController(Constants.kPDriveVel, 0, 0),
      new PIDController(Constants.kPDriveVel, 0, 0),
      driveTrain::tankDriveVolts,
      driveTrain);

    return ramseteCommand.andThen(() -> driveTrain.tankDriveVolts(0, 0));
    
  }
}
