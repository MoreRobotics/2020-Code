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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;
import frc.robot.commands.TurnTurret;

public class Turret extends SubsystemBase {
  //Declares the motor controller for the turret motor
  TalonSRX turretMotor;

  //Declares the operator controller
  XboxController operatorController;
  NetworkTableEntry yaw;
  NetworkTableEntry pitch;
  NetworkTableEntry isDriverMode;
  double targetHeightFromCamera = Constants.TARGET_HEIGHT - Constants.CAMERA_HEIGHT;
  double targetPosition = 0 * Constants.ENCODER_UNITS_TO_DEGREES * Constants.TURRET_GEAR_RATIO;

  /**
   * Creates a new Turret.
   */
  public Turret() {
    turretMotor = new TalonSRX(Constants.TURRET_MOTOR_ID);
    operatorController = new XboxController(Constants.OPERATOR_CONTROLLER_PORT);

    turretMotor.configFactoryDefault();
    turretMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
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

    //turretMotor.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);

    this.setDefaultCommand(new TurnTurret(this));
    
    int absolutePosition = turretMotor.getSensorCollection().getPulseWidthPosition() - 2121;
    absolutePosition &= 0xFFF;
    absolutePosition *= -1;
    turretMotor.setSelectedSensorPosition(absolutePosition, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    
    NetworkTableInstance table = NetworkTableInstance.getDefault();
    NetworkTable cameraTable = table.getTable("chameleon-vision").getSubTable("Pi Camera");
    yaw = cameraTable.getEntry("targetYaw");
    pitch = cameraTable.getEntry("targetPitch");
    isDriverMode = cameraTable.getEntry("driver_mode");

  }
  
  //Turns the turret
  public void turnTurret() {
    double power = operatorController.getX(Hand.kLeft);
    //Deadband Check
    if(Math.abs(power) <= 0.05) {
      power = 0;
      
    }
    targetPosition += power * 0.1 * Constants.TURRET_MAX_ROTATION;
    if (targetPosition > Constants.TURRET_MAX_ROTATION) {
      targetPosition = Constants.TURRET_MAX_ROTATION;
    }
    else if (targetPosition < -Constants.TURRET_MAX_ROTATION) {
      targetPosition = -Constants.TURRET_MAX_ROTATION;
    }
    //System.out.println("TurretPosition" + targetPosition);
    turretMotor.set(ControlMode.MotionMagic, targetPosition);
    //turretMotor.set(ControlMode.PercentOutput, 1);
    //System.out.println(targetPosition);
  }

  //Stops the turret
  public void stopTurret() {
    turretMotor.set(ControlMode.MotionMagic, 0);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Yaw", yaw.getDouble(0.0));
    double distanceFromTarget = targetHeightFromCamera / Math.tan(pitch.getDouble(0.0) + Constants.CAMERA_ANGLE);
    SmartDashboard.putNumber("Distance to Target", distanceFromTarget);
  }
}
