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
import edu.wpi.first.wpilibj.trajectory.*;
import frc.robot.constraints.MaxAccelerationAndVelocityConstraint;
import frc.robot.subsystems.DriveTrain;


/**
 * Add your docs here.
 */
public class TrajectoryManager {
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
  
  public Trajectory LoadTrajectory(String trajectoryFile) {
    TrajectoryConfig trajectoryConfig = new TrajectoryConfig(4.267, 0.5);
    trajectoryConfig.addConstraint(new MaxAccelerationAndVelocityConstraint(4.267, 0.5));
    try {
      Path path = Filesystem.getDeployDirectory().toPath().resolve("output/" + trajectoryFile + ".wpilib.json");
      return TrajectoryUtil.fromPathweaverJson(path);
    } 
    catch (IOException ex) {
      DriverStation.reportError("Unable to open trajectory: " + trajectoryFile, ex.getStackTrace());
      return new Trajectory(List.of(new Trajectory.State()));
    }
  }

  public void LoadAllPaths() {
    barrelRacingPath = LoadTrajectory("barrelRacingPath");
    bouncePath = LoadTrajectory("bouncePath");
    galacticSearchARed = LoadTrajectory("galacticSearchARed");
    galacticSearchABlue = LoadTrajectory("galacticSearchABlue");
    galacticSearchBRed = LoadTrajectory("galacticSearchBRed");
    galacticSearchBRed = LoadTrajectory("galacticSearchBRed");
    slalomPath = LoadTrajectory("slalomPath");
    testPath = LoadTrajectory("testPath");    
  }
}
