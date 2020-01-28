/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class Hopper extends SubsystemBase {
  //Declares the wheels in the hopper
  WPI_TalonSRX wheelFrontMaster, wheelBackSlave, wheelFeeder;
  /**
   * Creates a new Hopper.
   */
  public Hopper() {
    //Instantiates shooter motors
    wheelFrontMaster = new WPI_TalonSRX(Constants.HOPPER_FRONT_MASTER_MOTOR_ID);
    wheelBackSlave = new WPI_TalonSRX(Constants.HOPPER_BACK_SLAVE_MOTOR_ID);
    wheelFeeder = new WPI_TalonSRX(Constants.HOPPER_FEEDER_MOTOR_ID);

    //Slaves the slave motor controller to the master motor controller
    wheelBackSlave.set(ControlMode.Follower, Constants.HOPPER_FRONT_MASTER_MOTOR_ID);
  }

  //Starts the front two hopper motors
  public void startFront() {
    wheelFrontMaster.set(ControlMode.PercentOutput, 1);
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
