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

/**
 * Add your docs here.
 */
public class TrajectoryManager {
  public Trajectory testPath;
  public Trajectory lineToTrench;
  public Trajectory linetoThreeCenterBalls;
  public Trajectory shootingSpot;
  public Trajectory slalomPath;
  public Trajectory galacticSearchChallengeARed;
  public Trajectory galacticSearchChallengeABlue;
  public Trajectory galacticSearchChallengeBRed;
  public Trajectory galacticSearchChallengeBBlue;
  public Trajectory bouncePath;
  public Trajectory barrelRacingPath;
  public static Trajectory excessPath;
  
  public Trajectory LoadTrajectory(String trajectoryFile) {
    
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
    testPath = LoadTrajectory("testPath");
    lineToTrench = LoadTrajectory("lineToTrench");
    linetoThreeCenterBalls = LoadTrajectory("lineToThreeCenterBalls");
    //still needs to be added to Pathweaver
    shootingSpot = LoadTrajectory("shootingSpot");
    
  }
}
