/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  WPI_TalonFX falconFrontRight, falconRearRight, falconFrontLeft, falconRearLeft;
  SpeedControllerGroup rightDrive, leftDrive;
  DifferentialDrive drive;
  XboxController driverController = new XboxController(Constants.DRIVER_CONTROLLER_PORT);
  /**
   * Creates a new DriveTrain.
   */
  public DriveTrain() {
   //Instantiates the drive motors
   falconFrontRight = new WPI_TalonFX(Constants.DRIVE_TRAIN_FRONT_RIGHT_ID);
   falconRearRight = new WPI_TalonFX(Constants.DRIVE_TRAIN_REAR_RIGHT_ID);
   rightDrive = new SpeedControllerGroup(falconFrontRight, falconRearRight);
   falconFrontLeft = new WPI_TalonFX(Constants.DRIVE_TRAIN_FRONT_LEFT_ID);
   falconRearLeft = new WPI_TalonFX(Constants.DRIVE_TRAIN_REAR_LEFT_ID);
   leftDrive = new SpeedControllerGroup(falconFrontLeft, falconRearLeft);
   drive = new DifferentialDrive(rightDrive, leftDrive);
  
   //falconRearLeft.follow(falconFrontLeft);
   //falconRearRight.follow(falconFrontRight);
   
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // falconFrontLeft.set(ControlMode.PercentOutput, (Math.abs(driverController.getX(Hand.kRight)) < 0.1 ? 0 : driverController.getX(Hand.kRight)) - (Math.abs(driverController.getY(Hand.kLeft)) < 0.1 ? 0 : driverController.getY(Hand.kLeft)));
    // falconFrontRight.set(ControlMode.PercentOutput, (Math.abs(driverController.getX(Hand.kRight)) < 0.1 ? 0 : driverController.getX(Hand.kRight)) + (Math.abs(driverController.getY(Hand.kLeft)) < 0.1 ? 0 : driverController.getY(Hand.kLeft)));
  }

  //Drives the robot depending on the thumbstick inputs
  public void drive() {
    drive.curvatureDrive(driverController.getY(Hand.kLeft), driverController.getX(Hand.kRight), true);
  }
}
