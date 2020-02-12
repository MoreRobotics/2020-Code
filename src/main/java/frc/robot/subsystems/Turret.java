/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class Turret extends SubsystemBase {
  //Declares the motor controller for the turret motor
  TalonSRX turretMotor;

  //Declares the operator controller
  XboxController operatorController;

  /**
   * Creates a new Turret.
   */
  public Turret() {
    turretMotor = new TalonSRX(Constants.TURRET_MOTOR_ID);
    operatorController = new XboxController(Constants.OPERATOR_CONTROLLER_PORT);
  }
  
  //Turns the turret
  public void turnTurret() {
    double power = operatorController.getX(Hand.kLeft);
    //Deadband check
    if(Math.abs(power) <= 0.05) {
      power = 0;
    }
    turretMotor.set(ControlMode.PercentOutput, power);
    //?double amountRotatedPer100PercentOutput = Constants.ENCODER_UNITS_TO_DEGREES * Constants.TURRET_GEAR_RATIO;
  }

  //Stops the turret
  public void stopTurret() {
    turretMotor.set(ControlMode.PercentOutput, 0);
 
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
