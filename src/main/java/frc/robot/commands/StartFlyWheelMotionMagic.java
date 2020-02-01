/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class StartFlyWheelMotionMagic extends CommandBase {
  private final Shooter flyWheel;
  
  /**
   * Creates a new StartFlyWheelAnalog.
   */ 
  public StartFlyWheelMotionMagic(Shooter flyWheel) {
    this.flyWheel = flyWheel;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(flyWheel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    flyWheel.startShooterMotionMagic();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    flyWheel.startShooterMotionMagic();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //Stops the shooter
    flyWheel.stopShooter();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
