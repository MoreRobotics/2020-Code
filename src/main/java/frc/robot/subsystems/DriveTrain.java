/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class DriveTrain extends SubsystemBase {
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
   

   falconRearLeft.follow(falconFrontLeft);
   falconRearRight.follow(falconFrontRight);
   
  }
  public void Drive(/*double speed1, double speed2, double speed3, double speed4*/) {
    /*falconFrontRight.set(ControlMode.Velocity, speed1);
    falconRearRight.set(ControlMode.Velocity, speed2);
    falconFrontLeft.set(ControlMode.Velocity, speed3);
    falconRearLeft.set(ControlMode.Velocity, speed4);*/
  }
  @Override
  public void periodic() {
    drive.curvatureDrive(operatorController.getY(Hand.kLeft), operatorController.getX(Hand.kRight), false);
    //drive.tankDrive(operatorController.getY(Hand.kRight), operatorController.getY(Hand.kLeft));
    // This method will be called once per scheduler run
    falconFrontLeft.set(ControlMode.PercentOutput, (Math.abs(operatorController.getX(Hand.kRight)) < 0.1 ? 0 : operatorController.getX(Hand.kRight)) - (Math.abs(operatorController.getY(Hand.kLeft)) < 0.1 ? 0 : operatorController.getY(Hand.kLeft)));
    falconFrontRight.set(ControlMode.PercentOutput, (Math.abs(operatorController.getX(Hand.kRight)) < 0.1 ? 0 : operatorController.getX(Hand.kRight)) + (Math.abs(operatorController.getY(Hand.kLeft)) < 0.1 ? 0 : operatorController.getY(Hand.kLeft)));
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
