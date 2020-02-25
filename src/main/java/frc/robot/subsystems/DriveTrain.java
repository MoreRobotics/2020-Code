/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;
import frc.robot.commands.TankDrive;

public class DriveTrain extends SubsystemBase {
  WPI_TalonFX falconFrontRight, falconRearRight, falconFrontLeft, falconRearLeft;
  TalonSRX gyroController;
  SpeedControllerGroup rightDrive, leftDrive;
  DifferentialDrive drive;
  XboxController driverController = new XboxController(Constants.DRIVER_CONTROLLER_PORT);
  PigeonIMU gyro;
  DifferentialDriveOdometry odometry;
  

  /**
   * Creates a new DriveTrain.
   */
  public DriveTrain() {
   //Instantiates the drive motors
   falconFrontRight = new WPI_TalonFX(Constants.DRIVE_TRAIN_FRONT_RIGHT_ID);
   falconRearRight = new WPI_TalonFX(Constants.DRIVE_TRAIN_REAR_RIGHT_ID);
   rightDrive = new SpeedControllerGroup(falconFrontRight, falconRearRight);
   falconFrontLeft = new WPI_TalonFX(Constants.DRIVE_TRAIN_FRONT_LEFT_ID);
   falconRearLeft = new WPI_TalonFX(Constants.DRIVE_TRAIN_REAR_LEFT_ID);
   leftDrive = new SpeedControllerGroup(falconFrontLeft, falconRearLeft);
   drive = new DifferentialDrive(rightDrive, leftDrive);
   gyroController = new TalonSRX(Constants.SHOOTER_FEEDER_MOTOR_ID);
   gyro = new PigeonIMU(gyroController);
   resetEncoders();
   odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));
   this.setDefaultCommand(new TankDrive(this));
   zeroHeading();
   
   //falconFrontLeft.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
   //falconFrontRight.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);

   //falconFrontLeft.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, 70, 25, 1.0));
   falconFrontLeft.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 70, 15, 0.5));
   falconFrontRight.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 70, 15, 0.5));
   falconRearLeft.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 70, 15, 0.5));
   falconRearRight.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 70, 15, 0.5));

   falconFrontLeft.setSensorPhase(true);
   falconFrontRight.setSensorPhase(true);

   //falconRearLeft.follow(falconFrontLeft);
   //falconRearRight.follow(falconFrontRight);
   
  }

  public void zeroHeading() {
    gyro.setCompassAngle(0.0);
  }

  public void resetEncoders() {
    falconFrontLeft.setSelectedSensorPosition(0);
    falconFrontRight.setSelectedSensorPosition(0);
  }

  public Pose2d getPose() {
    return odometry.getPoseMeters();
  }

  public double getHeading() {
    double [] ypr = new double[3];
    gyro.getYawPitchRoll(ypr);
    System.out.println("Yaw " + ypr[0]);
    return Math.IEEEremainder(ypr[0], 360);
  }

  @Override
  public void periodic() {
    //drive();
    // This method will be called once per scheduler run
    odometry.update(Rotation2d.fromDegrees(getHeading()), getLeftDistance(), getRightDistance());
    SmartDashboard.putNumber("Heading ", getHeading());
    SmartDashboard.putNumber("Left Distance ", Units.metersToInches(getLeftDistance()));
    SmartDashboard.putNumber("Right Distance ", Units.metersToInches(getRightDistance()));
    SmartDashboard.putNumber("Get pose X ", Units.metersToInches(getPose().getTranslation().getX()));
    SmartDashboard.putNumber("Get pose Y ", Units.metersToInches(getPose().getTranslation().getY()));
    SmartDashboard.putNumber("Left Sensor Velocity ", falconFrontLeft.getSelectedSensorVelocity());
    SmartDashboard.putNumber("Right Sensor Velocity ", falconFrontRight.getSelectedSensorVelocity());
  }

  public double getLeftDistance() {
    return falconFrontLeft.getSelectedSensorPosition() / Constants.EDGES_PER_REVOLUTION * Constants.WHEEL_DIAMETER;
  }

  public double getRightDistance() {
    return -falconFrontRight.getSelectedSensorPosition() / Constants.EDGES_PER_REVOLUTION * Constants.WHEEL_DIAMETER;
  }

  public void tankDriveVolts(final double leftVolts, final double rightVolts) {
    leftDrive.setVoltage(leftVolts);
    rightDrive.setVoltage(-rightVolts);
    drive.feed();
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(falconRearLeft.getSelectedSensorVelocity() / Constants.EDGES_PER_REVOLUTION * Constants.WHEEL_DIAMETER ,
    falconFrontRight.getSelectedSensorVelocity() / Constants.EDGES_PER_REVOLUTION * Constants.WHEEL_DIAMETER );
  }

  public void resetOdometry(Pose2d pose2d) {
    resetEncoders();
    odometry.resetPosition(pose2d, Rotation2d.fromDegrees(getHeading()));
  }

  //Drives the robot depending on the thumbstick inputs
  public void drive() {
    drive.curvatureDrive(driverController.getY(Hand.kLeft), driverController.getX(Hand.kRight), false);
  }
  
}
