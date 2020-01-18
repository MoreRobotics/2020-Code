/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class DriveTrain extends SubsystemBase {
  WPI_TalonSRX falconFrontRight, falconRearRight, falconFrontLeft, falconRearLeft;
  SpeedControllerGroup rightDrive, leftDrive;
  DifferentialDrive drive;
  XboxController operatorController;
  /**
   * Creates a new DriveTrain.
   */
  public DriveTrain(int falconFrontRightID, int falconRearRightID, int falconFrontLeftID, int falconRearLeftID) {
   falconFrontRight = new WPI_TalonSRX(falconFrontRightID);
   falconRearRight = new WPI_TalonSRX(falconRearRightID);
   rightDrive = new SpeedControllerGroup(falconFrontRight, falconRearRight);
   falconFrontLeft = new WPI_TalonSRX(falconFrontLeftID);
   falconRearLeft = new WPI_TalonSRX(falconRearLeftID);
   leftDrive = new SpeedControllerGroup(falconFrontLeft, falconFrontRight);
   drive = new DifferentialDrive(rightDrive, leftDrive);
   
  }
  public void Drive(/*double speed1, double speed2, double speed3, double speed4*/) {
    /*falconFrontRight.set(ControlMode.Velocity, speed1);
    falconRearRight.set(Con trolMode.Velocity, speed2);
    falconFrontLeft.set(ControlMode.Velocity, speed3);
    falconRearLeft.set(ControlMode.Velocity, speed4);*/
  }
  @Override
  public void periodic() {
    drive.arcadeDrive(operatorController.getX(), operatorController.getY());
    // This method will be called once per scheduler run
  }
}
