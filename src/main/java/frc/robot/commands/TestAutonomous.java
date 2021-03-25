/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.TrajectoryManager;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class TestAutonomous extends SequentialCommandGroup {
  private final DriveTrain m_driveTrain;
  /**
   * Creates a new TestAutonomous.
   */
  public TestAutonomous(DriveTrain driveTrain, Intake intake, TrajectoryManager trajectoryManager, Trajectory path) {
    m_driveTrain = driveTrain;
    
    if(path == trajectoryManager.bouncePathNew) {
      addCommands(m_driveTrain.getRamseteCommand(trajectoryManager.bouncePathNew), 
      m_driveTrain.getRamseteCommand(trajectoryManager.bouncePathNew2), 
      m_driveTrain.getRamseteCommand(trajectoryManager.bouncePathNew3), 
      m_driveTrain.getRamseteCommand(trajectoryManager.bouncePathNew4));
    } else if (path == trajectoryManager.galacticSearchABlue || 
    path == trajectoryManager.galacticSearchBBlue || 
    path == trajectoryManager.galacticSearchARed || 
    path == trajectoryManager.galacticSearchBRed) {
      System.out.println(path);
      Command galacticSearch = new ParallelCommandGroup(new IntakePowerCells(intake), m_driveTrain.getRamseteCommand(path));
      addCommands(galacticSearch);
    } else {
      addCommands(m_driveTrain.getRamseteCommand(path));
    }
    
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    
  }
}
