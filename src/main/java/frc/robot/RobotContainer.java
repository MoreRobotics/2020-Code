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
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
//import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj.util.Units;
import frc.robot.Robot;
import frc.robot.Constants;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  // private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  // Instantiates the subsystems
  Shooter shooter = new Shooter();
  DriveTrain driveTrain = new DriveTrain();
  Hopper hopper = new Hopper(shooter);
  Intake intake = new Intake();
  Turret turret = new Turret(driveTrain);
  ControlPanel controlPanel = new ControlPanel();
  Climber climber = new Climber();
  TrajectoryManager trajectoryManager = new TrajectoryManager();

  // private final ExampleCommand m_autoCommand = new
  // ExampleCommand(m_exampleSubsystem);

  // Instantiates the controllers and its associated buttons
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
  POVButton operatorDPadUp = new POVButton(operatorController, 0);
  POVButton operatorDPadRight = new POVButton(operatorController, 90);
  POVButton operatorDPadDown = new POVButton(operatorController, 180);
  POVButton operatorDPadLeft = new POVButton(operatorController, 270);
  

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // operatorAButton.whenHeld(new StartFlyWheelVelocityPID(shooter));
    driverLBumper.whenHeld(new TurnControlPanelLeft(controlPanel));
    driverRBumper.whenHeld(new TurnControlPanelRight(controlPanel));
    operatorRBumper.whenHeld(new ParallelCommandGroup(new IntakePushDown(intake), new IntakePowerCells(intake, intake)));

    operatorAButton.whenHeld(new ParallelCommandGroup(new StartFlyWheelVelocityPID(shooter), new FeedPowerCells(hopper),
        new StagePowerCells(hopper)));
    operatorBButton.whenPressed(new HoodAngleDown(shooter));
    operatorXButton.whenPressed(new HoodAngleUp(shooter));
    operatorDPadUp.whenPressed(new SetHighTargetRPM());
    operatorDPadRight.whenPressed(new SetMediumTargetRPM());
    operatorDPadDown.whenPressed(new SetLowTargetRPM());
    operatorDPadLeft.whenPressed(new ResetTargetRPM());

    

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    var autoVoltageConstraint = new DifferentialDriveVoltageConstraint(new SimpleMotorFeedforward(Constants.ksVolts,
        Constants.kvVoltSecondsPerMeter, Constants.kaVoltSecondsSquaredPerMeter), Constants.kDriveKinematics, 10);

    // Create config for trajectory
    TrajectoryConfig config = new TrajectoryConfig(Constants.kMaxSpeedMetersPerSecond,
        Units.metersToInches(Constants.kMaxAccelerationMetersPerSecondSquared))
            // Add kinematics to ensure max speed is actually obeyed
            .setKinematics(Constants.kDriveKinematics)
            // Apply the voltage constraint
            .addConstraint(autoVoltageConstraint);

    // An example trajectory to follow. All units in meters.
    Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
        // Start at the origin facing the +X direction
        new Pose2d(0, 0, new Rotation2d(0)),
        // Pass through these two interior waypoints, making an 's' curve path
        List.of(new Translation2d(1, 1), new Translation2d(2, -1)),
        // End 3 meters straight ahead of where we started, facing forward
        new Pose2d(3, 0, new Rotation2d(0)),
        // Pass config
        config);

    switch (Robot.chosenAutoPath) {
      case 0:
        return new TestAutonomous(driveTrain, exampleTrajectory);

      case 1:
        return new TestAutonomous(driveTrain, trajectoryManager.lineToTrench);

      case 2:
        return new TestAutonomous(driveTrain, trajectoryManager.linetoThreeCenterBalls);

      default:
        return new TestAutonomous(driveTrain, trajectoryManager.testPath);
    }
  }
}
