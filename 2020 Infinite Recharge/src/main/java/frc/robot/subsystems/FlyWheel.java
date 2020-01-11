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

public class FlyWheel extends SubsystemBase {
  TalonSRX wheelLeft;
  TalonSRX wheelRight;
    
  
  /**
   * Creates a new FlyWheel.
   */
  public FlyWheel(int left, int right) {
      wheelLeft = new TalonSRX(left);
      wheelRight = new TalonSRX(right);
  }

  @Override
  public void periodic() {
    wheelLeft.set(ControlMode.Velocity, 1.1);
    wheelRight.set(ControlMode.Velocity, 1.1);
    // This method will be called once per scheduler run
  }
}
