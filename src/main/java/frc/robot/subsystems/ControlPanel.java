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

import frc.robot.Constants;

public class ControlPanel extends SubsystemBase {
  //Declares the control panel motor
  TalonSRX controlPanelMotor;

  /**
   * Creates a new ControlPanel.
   */
  public ControlPanel() {
    controlPanelMotor = new TalonSRX(Constants.CONTROL_PANEL_MOTOR_ID);
  }
  
  //Turns the control panel left
  public void turnControlPanelLeft() {
    controlPanelMotor.setInverted(true);
    controlPanelMotor.set(ControlMode.PercentOutput, Constants.CONTROL_PANEL_SPEED);
  }

  //Turns the control panel right
  public void turnControlPanelRight() {
    controlPanelMotor.setInverted(false);
    controlPanelMotor.set(ControlMode.PercentOutput, Constants.CONTROL_PANEL_SPEED);
  }


  //Stops the control panel
  public void stopControlPanel() {
    controlPanelMotor.set(ControlMode.PercentOutput, 0);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
