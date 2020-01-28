/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

//TODO: Display velocity (RPM) on Dashboard
//TODO: Document PID Tuning

public class Shooter extends SubsystemBase { 
  //Declares the motor controllers for the wheels in the shooter
  WPI_TalonSRX wheelLeftMaster, wheelLeftSlave, wheelRight, extraMotorController;

  //Declares the shooter pneumatic double solenoid
  DoubleSolenoid shooterSolenoid;

  //Declares and/or initializes the variables for the shooter PID loop
  double P = Constants.P;
  double I = Constants.I;
  double D = Constants.D;
  double integral, derivative, previousError = 0.0;
  double error;

  
  /**
   * Creates a new Shooter.
   */
  //TODO: Make right motor spin at -1, the others at 1
  
  public Shooter() {
    //Instantiates the shooter wheels
    wheelLeftMaster = new WPI_TalonSRX(Constants.SHOOTER_LEFT_MOTOR_MASTER_ID);
    wheelLeftSlave = new WPI_TalonSRX(Constants.SHOOTER_LEFT_MOTOR_SLAVE_ID);
    wheelRight = new WPI_TalonSRX(Constants.SHOOTER_RIGHT_MOTOR_ID);
    //Extra added by Andrew's request
    extraMotorController = new WPI_TalonSRX(Constants.SHOOTER_EXTRA_MOTOR_ID);

    shooterSolenoid = new DoubleSolenoid(Constants.SHOOTER_SOLENOID_FORWARD_CHANNEL, Constants.SHOOTER_SOLENOID_REVERSE_CHANNEL);

    //Sets the master motor controller to percent output, makes the other motors slaves to it
    wheelLeftSlave.set(ControlMode.Follower, Constants.SHOOTER_LEFT_MOTOR_MASTER_ID);
    extraMotorController.set(ControlMode.Follower, Constants.SHOOTER_LEFT_MOTOR_MASTER_ID);
  }

  //Turns the shooter on
  public void startShooter() {
    wheelLeftMaster.set(ControlMode.PercentOutput, -Constants.SHOOTER_SPEED);
    wheelRight.set(ControlMode.PercentOutput, Constants.SHOOTER_SPEED);

  }

  //Turns the shooter on, using a PID loop to reach targetVelocity
  public void startShooterPID(double targetVelocityRPM) {
    //PID variable declaration/initialization
    double power;
    double currentVelocityUnitsPer100Ms = wheelLeftMaster.getSensorCollection().getQuadratureVelocity();
    double currentVelocityRPM = currentVelocityUnitsPer100Ms * Constants.ENCODER_UNITS_PER_100_MS_TO_REV_PER_MIN;

    //PID calculations
    error = targetVelocityRPM - currentVelocityRPM;
    integral += error * 0.02;
    derivative = (error - previousError) / 0.02;
    power = (P * error) + (I * integral) + (D * derivative);
    previousError = error;

    wheelLeftMaster.set(ControlMode.PercentOutput, power);
  }

  //Turns the shooter off
  public void stopShooter() {
    wheelLeftMaster.set(ControlMode.PercentOutput, 0);
    wheelRight.set(ControlMode.PercentOutput, 0);
  }

  //Gets the current position of the left wheel
  public double getMasterWheelPosition() {
    double currentPositionEncoderUnits = wheelLeftMaster.getSensorCollection().getQuadraturePosition();
    double currentPositionDegrees = currentPositionEncoderUnits / Constants.ENCODER_UNITS_TO_DEGREES;

    return currentPositionDegrees;
  }

  //Zeroes the wheels' positions
  public void zeroWheels() {
    wheelLeftMaster.setSelectedSensorPosition(0);
  }

  //Pneumatically sets the hood angle up
  public void hoodAngleUp() {
    shooterSolenoid.set(Value.kForward);
  }

  //Pneumatically sets the hood angle down
  public void hoodAngleDown() {
    shooterSolenoid.set(Value.kReverse);
  }

  @Override
  public void periodic() {

    // This method will be called once per scheduler run
  }
}
