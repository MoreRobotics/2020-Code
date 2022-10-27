package frc.robot.commands;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class StopCompressor extends CommandBase {
    Compressor compressor = new Compressor();
    
    public StopCompressor() {
        
    }

    @Override
    public void initialize() {
        compressor.stop();
    }

    @Override
    public void execute() {

    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override 
    public boolean isFinished() {
        return true;
    }
}
