/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.TrajectoryManager;
import frc.robot.subsystems.DriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class TestAutonomous extends SequentialCommandGroup {
  private final DriveTrain m_driveTrain;
  private final TrajectoryManager m_trajectoryManager;
  /**
   * Creates a new TestAutonomous.
   */
  public TestAutonomous(DriveTrain driveTrain, TrajectoryManager trajectoryManager) {
    m_driveTrain = driveTrain;
    m_trajectoryManager = trajectoryManager;
    
    addCommands(m_driveTrain.getRamseteCommand(m_trajectoryManager.testPath));
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    
  }
}
