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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class Hopper extends SubsystemBase {
  // Declares the wheels in the hopper
  TalonSRX wheelFrontMaster, wheelFeeder;
  private final Shooter shooter;
  /**
   * Creates a new Hopper.
   */
  public Hopper(Shooter shooter) {
    this.shooter = shooter;
    // Instantiates shooter motors
    wheelFrontMaster = new TalonSRX(Constants.HOPPER_FRONT_MASTER_MOTOR_ID);
    wheelFeeder = new TalonSRX(Constants.HOPPER_FEEDER_MOTOR_ID);
    wheelFrontMaster.setInverted(true);
    

  }

  // Starts the front two hopper motors
  /*
   * I basically implemented the thing Riley made where the feeder motors don't go
   * until the flywheel rpm is at a cetain threshold, but for the hopper. That
   * way, the hopper won't ty to shove balls into the feeder without the feeder
   * running. Let me know if I screwed anything up. -Samuel
   */
  public void startFront() {
    if (SmartDashboard.getNumber("Shooter RPM", 0) >= Constants.SHOOTER_DEFAULT_TARGET_RPM * .75) {
      wheelFrontMaster.set(ControlMode.PercentOutput, Constants.HOPPER_SPEED);
    }
  }

  // Stops the front two hopper motors
  public void stopFront() {
    wheelFrontMaster.set(ControlMode.PercentOutput, 0);
  }

  // Starts the hopper feeder motor
  public void startFeeder() {
    if (SmartDashboard.getNumber("Shooter RPM", 0) >= Constants.SHOOTER_DEFAULT_TARGET_RPM * .75) {
      wheelFeeder.set(ControlMode.PercentOutput, -1);
    }
  }

  // Stops the hopper feeder motor
  public void stopFeeder() {
    wheelFeeder.set(ControlMode.PercentOutput, 0);
  }

  // Starts the hopper feeder motor in the reverse direction
  public void reverseFeeder() {
    wheelFeeder.set(ControlMode.PercentOutput, 1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
