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
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

//TODO: Display velocity (RPM) on Dashboard
//TODO: Document PID Tuning

public class Shooter extends SubsystemBase { 
  //Declares the motor controllers for the wheels in the shooter
  WPI_TalonSRX wheelLeftMaster, wheelLeftSlave, wheelRightMaster, wheelRightSlave;

  //Declares the shooter pneumatic double solenoid
  DoubleSolenoid shooterSolenoid;

  //Declares the operator controller
  XboxController operatorController;

  /**
   * Creates a new Shooter.
   */
  
  public Shooter() {
    //Instantiates the shooter objects
    wheelLeftMaster = new WPI_TalonSRX(Constants.SHOOTER_LEFT_MASTER_MOTOR_ID);
    wheelLeftSlave = new WPI_TalonSRX(Constants.SHOOTER_LEFT_SLAVE_MOTOR_ID);
    wheelRightMaster = new WPI_TalonSRX(Constants.SHOOTER_RIGHT_MASTER_MOTOR_ID);
    wheelRightSlave = new WPI_TalonSRX(Constants.SHOOTER_RIGHT_SLAVE_MOTOR_ID);
    shooterSolenoid = new DoubleSolenoid(Constants.SHOOTER_SOLENOID_FORWARD_CHANNEL, Constants.SHOOTER_SOLENOID_REVERSE_CHANNEL);
    operatorController = new XboxController(Constants.OPERATOR_CONTROLLER_PORT);

    //Sets the master motor controller to percent output, makes the other motors slaves to it
    wheelLeftSlave.set(ControlMode.Follower, Constants.SHOOTER_LEFT_MASTER_MOTOR_ID);
    wheelRightSlave.set(ControlMode.Follower, Constants.SHOOTER_RIGHT_MASTER_MOTOR_ID);

    wheelRightMaster.configFactoryDefault();
    wheelRightMaster.setInverted(false);
    wheelRightMaster.setSensorPhase(true);
  }

  //Turns the shooter on
  public void startShooter() {
    
  }

  //Runs the shooter up to the target velocity using PID
    public void startShooterVelocityPID() {
      double targetVelocityRPM = SmartDashboard.getNumber("Shooter Target RPM", Constants.SHOOTER_DEFAULT_TARGET_RPM);
      double targetVelocityEncoderUnitsPer100ms = targetVelocityRPM * Constants.RPM_TO_ENCODER_UNITS_PER_100_MS;
      
      //TODO: Figure out why the set lines won't cause the motors to spin
      wheelLeftMaster.set(ControlMode.Velocity, -targetVelocityEncoderUnitsPer100ms);
      wheelRightMaster.set(ControlMode.Velocity, targetVelocityEncoderUnitsPer100ms);
    }

  //Turns the shooter off
  public void stopShooter() {
    wheelLeftMaster.set(ControlMode.PercentOutput, 0);
    wheelRightMaster.set(ControlMode.PercentOutput, 0);
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
