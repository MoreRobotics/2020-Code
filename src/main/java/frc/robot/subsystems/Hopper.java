/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class Hopper extends SubsystemBase {
  //Declares the wheels in the hopper
  TalonSRX wheelFrontMaster, wheelFeeder;
  /**
   * Creates a new Hopper.
   */
  public Hopper() {
    //Instantiates shooter motors
    wheelFrontMaster = new TalonSRX(Constants.HOPPER_FRONT_MASTER_MOTOR_ID);
    wheelFeeder = new TalonSRX(Constants.HOPPER_FEEDER_MOTOR_ID);
  
  }

  //Starts the front two hopper motors
  public void startFront() {
    wheelFrontMaster.set(ControlMode.PercentOutput, Constants.HOPPER_SPEED);
  }

  //Stops the front two hopper motors
  public void stopFront() {
    wheelFrontMaster.set(ControlMode.PercentOutput, 0);
  }

  //Starts the hopper feeder motor
  public void startFeeder() {
    wheelFeeder.set(ControlMode.PercentOutput, 1);
  }

  //Stops the hopper feeder motor
  public void stopFeeder() {
    wheelFeeder.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
