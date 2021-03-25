/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.TrajectoryManager;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutonomousRun extends SequentialCommandGroup {
  /**
   * Creates a new AutonomousRun.
   */
  public AutonomousRun(Intake intake, Shooter shooter, DriveTrain driveTrain, Hopper hopper, Turret turret, TrajectoryManager trajectory, TrajectoryManager trajectoryManager) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new TurnTurretAndShootCG(turret, shooter), new FeedBallsToShooterCG(intake, hopper), new BallPickupCG(intake, driveTrain, shooter, trajectoryManager), new GoToShootingSpot(trajectory), new SpeedUpShooterAndAlignShot(turret, shooter), new FeedBallsToShooterCG(intake, hopper));
  }
}
