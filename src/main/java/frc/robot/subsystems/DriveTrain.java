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

public class DriveTrain extends SubsystemBase {
  private TalonSRX motorLeft1 = new TalonSRX(0);
  private TalonSRX motorLeft2 = new TalonSRX(1);
  private TalonSRX motorRight1 = new TalonSRX(2);
  private TalonSRX motorRight2 = new TalonSRX(3);
  /**
   * Creates a new DriveTrain.
   */
  public DriveTrain() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setLeftMotors(double speed) {
    motorLeft1.set(ControlMode.PercentOutput, -speed); 
    motorLeft2.set(ControlMode.PercentOutput, -speed);
  }
  public void setRightMotors(double speed) {
    motorRight1.set(ControlMode.PercentOutput, speed); 
    motorRight2.set(ControlMode.PercentOutput, speed);
  }
}
