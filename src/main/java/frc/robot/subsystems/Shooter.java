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
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.NeutralMode;

//TODO: Display velocity (RPM) on Dashboard
//TODO: Document PID Tuning

public class Shooter extends SubsystemBase { 
  //Declares the motor controllers for the wheels in the shooter
  TalonSRX wheelLeftMaster;
  VictorSPX wheelLeftSlave, wheelRightMaster, wheelRightSlave;

  //Declares the shooter pneumatic double solenoid
  DoubleSolenoid shooterSolenoid;

  //Declares the operator controller
  XboxController operatorController;

  /**
   * Creates a new Shooter.
   */
  
  public Shooter() {
    //Instantiates the shooter objects
    wheelLeftMaster = new TalonSRX(Constants.SHOOTER_LEFT_MASTER_MOTOR_ID);
    wheelLeftSlave = new VictorSPX(Constants.SHOOTER_LEFT_SLAVE_MOTOR_ID);
    wheelRightMaster = new VictorSPX(Constants.SHOOTER_RIGHT_MASTER_MOTOR_ID);
    wheelRightSlave = new VictorSPX(Constants.SHOOTER_RIGHT_SLAVE_MOTOR_ID);
    shooterSolenoid = new DoubleSolenoid(Constants.SHOOTER_SOLENOID_FORWARD_CHANNEL, Constants.SHOOTER_SOLENOID_REVERSE_CHANNEL);
    operatorController = new XboxController(Constants.OPERATOR_CONTROLLER_PORT);

    //Sets the master motor controller to percent output, makes the other motors slaves to it
    wheelLeftMaster.configFactoryDefault();
    wheelLeftSlave.configFactoryDefault();
    wheelRightMaster.configFactoryDefault();
    wheelRightSlave.configFactoryDefault();
    wheelLeftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
    wheelRightMaster.setInverted(false);
    wheelLeftMaster.setSensorPhase(true);
    //wheelLeftMaster.configFactoryDefault();
    wheelLeftMaster.setInverted(true);
    //wheelLeftMaster.setSensorPhase(false);
    wheelLeftSlave.setInverted(true);

    //wheelLeftSlave.set(ControlMode.Follower, Constants.SHOOTER_LEFT_MASTER_MOTOR_ID);
    wheelRightSlave.follow(wheelRightMaster);
    wheelLeftSlave.follow(wheelLeftMaster);
    wheelRightMaster.follow(wheelLeftMaster);

    wheelLeftMaster.setNeutralMode(NeutralMode.Coast);
    wheelRightMaster.setNeutralMode(NeutralMode.Coast);
    wheelLeftSlave.setNeutralMode(NeutralMode.Coast);
    wheelRightSlave.setNeutralMode(NeutralMode.Coast);

    wheelLeftMaster.configNominalOutputForward(0, Constants.kTimeoutMs);
    wheelLeftMaster.configNominalOutputReverse(0, Constants.kTimeoutMs);
    wheelLeftMaster.configPeakOutputForward(1, Constants.kTimeoutMs);
    wheelLeftMaster.configPeakOutputReverse(-1, Constants.kTimeoutMs);

    wheelLeftMaster.config_kF(0, Constants.kGains_Shooter_Velocity.kF, Constants.kTimeoutMs);
    wheelLeftMaster.config_kP(0, Constants.kGains_Shooter_Velocity.kP, Constants.kTimeoutMs);
    wheelLeftMaster.config_kI(0, Constants.kGains_Shooter_Velocity.kI, Constants.kTimeoutMs);
    wheelLeftMaster.config_kD(0, Constants.kGains_Shooter_Velocity.kD, Constants.kTimeoutMs);
    
  }

  //Turns the shooter on
  public void startShooter() {
    wheelLeftMaster.set(ControlMode.PercentOutput, Constants.SHOOTER_SPEED);
    //wheelRightMaster.set(ControlMode.PercentOutput, Constants.SHOOTER_SPEED);

  }

  //Runs the shooter up to the target velocity using PID
  public void startShooterVelocityPID() {
    double targetVelocityRPM = SmartDashboard.getNumber("Shooter Target RPM", Constants.SHOOTER_DEFAULT_TARGET_RPM);
    double targetVelocityEncoderUnitsPer100ms = targetVelocityRPM * Constants.RPM_TO_ENCODER_UNITS_PER_100_MS;
      
    //TODO: Figure out why the set lines won't cause the motors to spin
    wheelLeftMaster.set(ControlMode.Velocity, targetVelocityEncoderUnitsPer100ms);
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
