/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;
import frc.robot.commands.TurnTurret;

public class Turret extends SubsystemBase {
  //Declares the motor controller for the turret motor
  TalonSRX turretMotor;

  //Declares the operator controller
  XboxController operatorController;
  NetworkTableEntry yaw;
  double targetHeightFromCamera = 1;
  double cameraAngleToTarget = 1;

  /**
   * Creates a new Turret.
   */
  public Turret() {
    turretMotor = new TalonSRX(Constants.TURRET_MOTOR_ID);
    operatorController = new XboxController(Constants.OPERATOR_CONTROLLER_PORT);
    turretMotor.selectProfileSlot(Constants.GAINS_INDEX, Constants.PIDLOOP_INDEX);
    turretMotor.config_kF(Constants.GAINS_INDEX, Constants.TURRET_GAINS.kF, Constants.TURRET_TIMEOUT);
		turretMotor.config_kP(Constants.GAINS_INDEX, Constants.TURRET_GAINS.kP, Constants.TURRET_TIMEOUT);
		turretMotor.config_kI(Constants.GAINS_INDEX, Constants.TURRET_GAINS.kI, Constants.TURRET_TIMEOUT);
    turretMotor.config_kD(Constants.GAINS_INDEX, Constants.TURRET_GAINS.kD, Constants.TURRET_TIMEOUT);
    this.setDefaultCommand(new TurnTurret(this));
  }
  
  //Turns the turret
  public void turnTurret() {
    /**double power = operatorController.getX(Hand.kLeft);
    turretMotor.selectProfileSlot(Constants.GAINS_INDEX, Constants.PIDLOOP_INDEX);
    turretMotor.config_kF(Constants.GAINS_INDEX, Constants.TURRET_GAINS.kF, Constants.TURRET_TIMEOUT);
		turretMotor.config_kP(Constants.GAINS_INDEX, Constants.TURRET_GAINS.kP, Constants.TURRET_TIMEOUT);
		turretMotor.config_kI(Constants.GAINS_INDEX, Constants.TURRET_GAINS.kI, Constants.TURRET_TIMEOUT);
		turretMotor.config_kD(Constants.GAINS_INDEX, Constants.TURRET_GAINS.kD, Constants.TURRET_TIMEOUT);
    //Deadband check
    if(Math.abs(power) <= 0.05) {
      power = 0;
      
    }
    double degreesPerMotorRotation = 360/10;
    double targetPosition = power * 4096 * Constants.TURRET_GEAR_RATIO;
    turretMotor.set(ControlMode.MotionMagic, targetPosition);
    //?double amountRotatedPer100PercentOutput = Constants.ENCODER_UNITS_TO_DEGREES * Constants.TURRET_GEAR_RATIO;
    */
    double power = operatorController.getX(Hand.kLeft);
    //Deadband Check
    if(Math.abs(power) <= 0.05) {
      power = 0;
      
    }
    double targetPosition = power * 4096 * Constants.TURRET_GEAR_RATIO;
    turretMotor.set(ControlMode.MotionMagic, targetPosition);
    turretMotor.set(ControlMode.PercentOutput, 1);
    //System.out.println(targetPosition);
  }

  //Stops the turret
  public void stopTurret() {
    turretMotor.set(ControlMode.MotionMagic, 0);

  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    NetworkTableInstance table = NetworkTableInstance.getDefault();
    //Deadband check
    NetworkTable cameraTable = table.getTable("chameleon-vision").getSubTable("Pi Camera");
    yaw = cameraTable.getEntry("targetYaw");
    double distanceFromTarget = targetHeightFromCamera / Math.tan(cameraAngleToTarget);
    turnTurret();    
    
  }
}
