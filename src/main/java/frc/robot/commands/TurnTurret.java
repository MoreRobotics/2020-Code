/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Turret;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class TurnTurret extends CommandBase {
  private final Turret turret;
  private final DriveTrain driveTrain;
  
  /**
   * Creates a new StartFlyWheel.
   */ 
  public TurnTurret(Turret turret, DriveTrain driveTrain) {
    this.turret = turret;
    this.driveTrain = driveTrain;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(turret);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    turret.turnTurret(driveTrain);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    turret.turnTurret(driveTrain);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      turret.stopTurret();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
