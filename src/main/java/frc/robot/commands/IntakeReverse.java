/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class IntakeReverse extends CommandBase {
  private final Intake intake;
  private final Intake solenoid;
  XboxController operatorController = new XboxController(Constants.OPERATOR_CONTROLLER_PORT);
  JoystickButton operatorStartButton = new JoystickButton(operatorController, XboxController.Button.kStart.value);
  /**
   * Creates a new StartFlyWheel.
   */ 
  public IntakeReverse(Intake intake, Intake solenoid) {
    this.intake = intake;
    this.solenoid = solenoid;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      //Starts the hopper feeder motor
      intake.intakeReverse();
      //intake.intakePushDown();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //Stops the hopper feeder motor
    if (operatorStartButton.get() == false) {
      intake.intakeStop();
      //intake.intakePullUp();
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (operatorStartButton.get() == true) {
      return false;
    } else {
      return true;
    }
    
    
         
  }
}