/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class IntakePowerCells extends CommandBase {
  private final Intake intake;
<<<<<<< Updated upstream
  
=======
  XboxController operatorController = new XboxController(Constants.OPERATOR_CONTROLLER_PORT);
  JoystickButton operatorLBumper = new JoystickButton(operatorController, XboxController.Button.kBumperLeft.value);
>>>>>>> Stashed changes
  /**
   * Creates a new StartFlyWheel.
   */ 
  public IntakePowerCells(Intake intake) {
    this.intake = intake;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      //Starts the hopper feeder motor
      intake.intakeStart();
<<<<<<< Updated upstream
=======
      //intake.intakePushDown();
>>>>>>> Stashed changes
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
<<<<<<< Updated upstream
      //Stops the hopper feeder motor
      intake.intakeStop();
=======
    //Stops the hopper feeder motor
    if (!operatorLBumper.get()) {
      intake.intakeStop();
      //intake.intakePullUp();
    }
>>>>>>> Stashed changes
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
<<<<<<< Updated upstream
    return false;
=======
    return !operatorLBumper.get();
>>>>>>> Stashed changes
  }
}
