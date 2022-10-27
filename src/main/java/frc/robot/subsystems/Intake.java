/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  //Declares the motor controller for the wheel in the intake
  TalonSRX intakeWheel;
  //Declares the intake double solenoid
  DoubleSolenoid intakeSolenoid;

  /**
   * Creates a new Intake.
   */
  public Intake() {
    //Instantiates the intake wheel
    intakeWheel = new TalonSRX(Constants.INTAKE_MOTOR_ID);
    
    intakeSolenoid = new DoubleSolenoid(Constants.INTAKE_SOLENOID_FORWARD_CHANNEL, Constants.INTAKE_SOLENOID_REVERSE_CHANNEL); 
  }

  //Starts intake motors
  public void intakeStart() {
    intakeWheel.set(ControlMode.PercentOutput, Constants.INTAKE_SPEED);
  }

  public void intakeReverse() {
    intakeWheel.set(ControlMode.PercentOutput, -Constants.INTAKE_SPEED*.5);
  }

  //Stops intake motors
  public void intakeStop() {
    intakeWheel.set(ControlMode.PercentOutput, 0);
  }

  //Pneumatically sets intake down
  public void intakePushDown() {
    intakeSolenoid.set(Value.kReverse);
  }
  //Pneumatically seyt intake up
  public void intakePullUp() {
    intakeSolenoid.set(Value.kForward);
  }



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
