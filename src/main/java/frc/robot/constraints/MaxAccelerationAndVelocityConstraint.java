// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.constraints;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.trajectory.constraint.TrajectoryConstraint;

/** Add your docs here. */
public class MaxAccelerationAndVelocityConstraint implements TrajectoryConstraint{
  private final double maxAcceleration;
  private final double maxVelocity;

  public MaxAccelerationAndVelocityConstraint(double maxVelocityMetersPerSecond, double maxAccelerationMetersPerSecondSq) {
    maxAcceleration = maxAccelerationMetersPerSecondSq;
    maxVelocity = maxVelocityMetersPerSecond;
  }

  @Override
  public double getMaxVelocityMetersPerSecond(Pose2d poseMeters, double curvatureRadPerMeter, double velocityMetersPerSecond) {
    return maxVelocity;
  }

  @Override
  public MinMax getMinMaxAccelerationMetersPerSecondSq(Pose2d poseMeters, double curvatureRadPerMeter, double velocityMetersPerSecond) {
    return new MinMax(0, maxAcceleration);
  }
}
