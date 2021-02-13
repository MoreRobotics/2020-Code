/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;
import frc.robot.commands.TurnTurret;

public class Turret extends SubsystemBase {
  private final DriveTrain driveTrain;
  // Declares the motor controller for the turret motor
  TalonSRX turretMotor;

  // Declares the operator controller
  XboxController operatorController;
  NetworkTableEntry yaw;
  NetworkTableEntry pitch;
  NetworkTableEntry isDriverMode;
  double targetHeightFromCamera = Constants.TARGET_HEIGHT - Constants.CAMERA_HEIGHT;
  double targetPosition = 0 * Constants.ENCODER_UNITS_TO_DEGREES;
  double degreesOffTarget;

  /**
   * Creates a new Turret.
   */
  public Turret(DriveTrain driveTrain) {
    this.driveTrain = driveTrain;
    turretMotor = new TalonSRX(Constants.TURRET_MOTOR_ID);
    operatorController = new XboxController(Constants.OPERATOR_CONTROLLER_PORT);

    turretMotor.configFactoryDefault();
    turretMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx,
        Constants.kTimeoutMs);
    turretMotor.setSensorPhase(true);

    turretMotor.configNominalOutputForward(0, Constants.kTimeoutMs);
    turretMotor.configNominalOutputReverse(0, Constants.kTimeoutMs);
    turretMotor.configPeakOutputForward(1, Constants.kTimeoutMs);
    turretMotor.configPeakOutputReverse(-1, Constants.kTimeoutMs);

    turretMotor.config_kF(Constants.kPIDLoopIdx, Constants.k_Gains_Turret_Position.kF, Constants.kTimeoutMs);
    turretMotor.config_kP(Constants.kPIDLoopIdx, Constants.k_Gains_Turret_Position.kP, Constants.kTimeoutMs);
    turretMotor.config_kI(Constants.kPIDLoopIdx, Constants.k_Gains_Turret_Position.kI, Constants.kTimeoutMs);
    turretMotor.config_kD(Constants.kPIDLoopIdx, Constants.k_Gains_Turret_Position.kD, Constants.kTimeoutMs);

    turretMotor.configMotionCruiseVelocity(800 / 3, Constants.kTimeoutMs);
    turretMotor.configMotionAcceleration(800 / 3, Constants.kTimeoutMs);

    // turretMotor.setSelectedSensorPosition(0, Constants.kPIDLoopIdx,
    // Constants.kTimeoutMs);

    this.setDefaultCommand(new TurnTurret(this, driveTrain));

    int absolutePosition = turretMotor.getSensorCollection().getPulseWidthPosition() - 8000;
    absolutePosition &= 0xFFF;
    absolutePosition *= -1;
    turretMotor.setSelectedSensorPosition(0);

    NetworkTableInstance table = NetworkTableInstance.getDefault();
    NetworkTable cameraTable = table.getTable("chameleon-vision").getSubTable("Pi Camera");
    yaw = cameraTable.getEntry("targetYaw");
    pitch = cameraTable.getEntry("targetPitch");
    isDriverMode = cameraTable.getEntry("driver_mode");

  }

  public void rotateToTurretAngle(Rotation2d rotationAngle) {
    rotateToTarget(rotationAngle.getDegrees() * Constants.ENCODER_UNITS_TO_DEGREES);
  }

  public void rotateToTarget(double target) {
    targetPosition = target;
    if (targetPosition > Constants.TURRET_MAX_ROTATION) {
      targetPosition = Constants.TURRET_MAX_ROTATION;
    } else if (targetPosition < -Constants.TURRET_MAX_ROTATION) {
      targetPosition = -Constants.TURRET_MAX_ROTATION;
    }
    turretMotor.set(ControlMode.MotionMagic, targetPosition);

  }

  public void turnTurretAuto() {
    NetworkTableInstance table = NetworkTableInstance.getDefault();
    NetworkTable cameraTable = table.getTable("chameleon-vision").getSubTable("Pi Camera");
    yaw = cameraTable.getEntry("targetYaw");
    degreesOffTarget = yaw.getDouble(0.0);
    rotateToTarget(targetPosition - degreesOffTarget * Constants.ENCODER_UNITS_TO_DEGREES);
  }

  // Turns the turret
  public void turnTurret(DriveTrain driveTrain) {
    double x = operatorController.getX(Hand.kRight);
    // Deadband Check
    if (Math.abs(x) <= 0.1) {
      x = 0;
    }
    double y = operatorController.getY(Hand.kRight);
    // Deadband Check
    if (Math.abs(y) <= 0.1) {
      y = 0;
    }
    Rotation2d turretRotationAngle = new Rotation2d(-y, x);
    rotateToTurretAngle(turretRotationAngle);
    // Get gyro yaw
    driveTrain.getHeading();
    // Calculate turret relative angle of joystick and gyro

  }

  public void turretToAngleAbsolute() {
    // Angle want to turn to from joystick

  }

  // Stops the turret
  public void stopTurret() {
    turretMotor.set(ControlMode.MotionMagic, 0);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Yaw", yaw.getDouble(0.0));
    double distanceFromTarget = targetHeightFromCamera / Math.tan(pitch.getDouble(0.0) + Constants.CAMERA_ANGLE);
    SmartDashboard.putNumber("Distance to Target", distanceFromTarget);
    SmartDashboard.putNumber("Target Position", targetPosition);
  }
}
