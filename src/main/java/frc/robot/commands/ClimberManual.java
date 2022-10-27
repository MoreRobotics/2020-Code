/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Climber;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class ClimberManual extends CommandBase {
  private final Climber climber ;
  JoystickButton operatorLeftJoystickButton;
  /**
   * Creates a new StartFlyWheel.
   */ 
  public ClimberManual(Climber climber) {
    this.climber = climber;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climber);
    XboxController operatorController = new XboxController(Constants.OPERATOR_CONTROLLER_PORT);
    operatorLeftJoystickButton = new JoystickButton(operatorController, XboxController.Button.kStickLeft.value);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      //Starts the front two motors of the hopper
      //climber.climberManual();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    climber.climberManual();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      //Stops the front two motors of the hopper
      
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
