
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.TrajectoryManager;

public class BouncePath extends CommandBase {
  private final DriveTrain driveTrain;
  private final TrajectoryManager trajectoryManager;

  public BouncePath(DriveTrain driveTrain, TrajectoryManager trajectoryManager) {
    this.driveTrain = driveTrain;
    this.trajectoryManager = trajectoryManager;
  }

  @Override
  public void initialize() {
    driveTrain.getRamseteCommand(trajectoryManager.bouncePath);
  }

  @Override
  public void execute() {

  }

  @Override
  public void end(boolean interrupted) {

  }

  @Override
  public boolean isFinished() {
    return false;
  }
}