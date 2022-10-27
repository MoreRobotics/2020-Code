package frc.robot.commands;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class StartCompressor extends CommandBase {
    Compressor compressor = new Compressor();
    
    public StartCompressor() {

    }

    @Override
    public void initialize() {
        compressor.start();
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
