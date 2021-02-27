/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.trajectory.*;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj.trajectory.constraint.TrajectoryConstraint;
import frc.robot.subsystems.DriveTrain;


/**
 * Add your docs here.
 */
public class TrajectoryManager implements TrajectoryConstraint {
  public DriveTrain driveTrain;
  public Trajectory slalomPath;
  public Trajectory testPath;
  public Trajectory galacticSearchARed;
  public Trajectory galacticSearchABlue;
  public Trajectory galacticSearchBRed;
  public Trajectory galacticSearchBBlue;
  public Trajectory bouncePath;
  public Trajectory barrelRacingPath;
  public static Trajectory excessPath;
  
  public Trajectory LoadTrajectory(String trajectoryFile, DriveTrain driveTrain) {
    TrajectoryConfig trajectoryConfig = new TrajectoryConfig(4, .5);
    trajectoryConfig.addConstraint(new DifferentialDriveVoltageConstraint(driveTrain.simpleMotorFeedForward, Constants.kDriveKinematics, 10.0));
    try {
      Path path = Filesystem.getDeployDirectory().toPath().resolve("output/" + trajectoryFile + ".wpilib.json");
      return TrajectoryUtil.fromPathweaverJson(path);
    } 
    catch (IOException ex) {
      DriverStation.reportError("Unable to open trajectory: " + trajectoryFile, ex.getStackTrace());
      return new Trajectory(List.of(new Trajectory.State()));
    }
  }

  @Override
  public double getMaxVelocityMetersPerSecond(Pose2d poseMeters, double curvatureRadPerMeter, double velocityMetersPerSecond) {
    return 4;
  }

  @Override
  public MinMax getMinMaxAccelerationMetersPerSecondSq(Pose2d poseMeters, double curvatureRadPerMeter, double velocityMetersPerSecond) {
    double maxAcceleration = 0.5;
    double minAcceleration = 0.3;
    MinMax max = new MinMax(minAcceleration, maxAcceleration);
    return max;
}

  public void LoadAllPaths() {
    barrelRacingPath = LoadTrajectory("barrelRacingPath", driveTrain);
    bouncePath = LoadTrajectory("bouncePath", driveTrain);
    galacticSearchARed = LoadTrajectory("galacticSearchARed", driveTrain);
    galacticSearchABlue = LoadTrajectory("galacticSearchABlue", driveTrain);
    galacticSearchBRed = LoadTrajectory("galacticSearchBRed", driveTrain);
    galacticSearchBRed = LoadTrajectory("galacticSearchBRed", driveTrain);
    slalomPath = LoadTrajectory("slalomPath", driveTrain);
    testPath = LoadTrajectory("testPath", driveTrain);
    
    
  }
}
