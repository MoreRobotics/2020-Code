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

public class Climber extends SubsystemBase {
  //Declares the motor controllers for the climber motors
  TalonSRX climberMotorLeft, climberMotorRight; 
  XboxController operatorController;
  /**
   * Creates a new Climber.
   */
  public Climber() {
    climberMotorLeft = new TalonSRX(Constants.CLIMBER_LEFT_MOTOR_ID);
    climberMotorRight = new TalonSRX(Constants.CLIMBER_RIGHT_MOTOR_ID);
    operatorController = new XboxController(Constants.OPERATOR_CONTROLLER_PORT);

    climberMotorRight.follow(climberMotorLeft);
  }

  //Manually raises the climber to height
  public void climberManual() {
    double climbSpeed = operatorController.getY(Hand.kRight);
    climberMotorLeft.set(ControlMode.PercentOutput, climbSpeed);
  }

  //Retracts climber 
  public void climberRetract() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
