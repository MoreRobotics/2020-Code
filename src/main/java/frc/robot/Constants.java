/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    //Defines the IDs of the motor controllers on the drive train
    public static final int DRIVE_TRAIN_FRONT_LEFT_ID = 3;
    public static final int DRIVE_TRAIN_FRONT_RIGHT_ID = 4;
    public static final int DRIVE_TRAIN_REAR_LEFT_ID = 2;
    public static final int DRIVE_TRAIN_REAR_RIGHT_ID = 5;
    public static final int DRIVER_CONTROLLER_PORT = 0;
    public static final int OPERATOR_CONTROLLER_PORT = 1;

    //Defines the IDs of the motor controllers on the shooter
    public static final int SHOOTER_LEFT_MASTER_MOTOR_ID = 20;
    public static final int SHOOTER_LEFT_SLAVE_MOTOR_ID = 21;
    public static final int SHOOTER_RIGHT_MASTER_MOTOR_ID = 22;
    public static final int SHOOTER_RIGHT_SLAVE_MOTOR_ID = 23;
    public static final int SHOOTER_FEEDER_MOTOR_ID = 24;
    //TODO: Add shooter feeder with ID = 24

    //Defines the channels of the solenoids on the shooter
    public static final int SHOOTER_SOLENOID_FORWARD_CHANNEL = 2;
    public static final int SHOOTER_SOLENOID_REVERSE_CHANNEL = 3;

    //Defines the speed the shooter wheels will be run ([-1.0, 1.0])
    public static final double SHOOTER_SPEED = 1;
    public static final double LOWERED_SHOOTER_SPEED = 0.25;

    //Defines the default target shooter RPM
    public static final double SHOOTER_HIGH_TARGET_RPM = 6500;//fill in
    public static final double SHOOTER_DEFAULT_TARGET_RPM = 6000;
    public static final double SHOOTER_MEDIUM_TARGET_RPM = 5000;//fill in
    public static final double SHOOTER_LOW_TARGET_RPM = 1500;//fill in
    public static final double RPM_TO_ENCODER_UNITS_PER_100_MS = .1 * 4096.0 / 60.0;

    //Shooter PID
    public final static Gains kGains_Shooter_Velocity = new Gains(0.01, 0, 0, 1023.0/53000.0, 300, 1.0);

    //Defines the IDs of the motor controllers on the hopper
    public static final int HOPPER_FRONT_MASTER_MOTOR_ID = 51;
    
    public static final int HOPPER_FEEDER_MOTOR_ID = 53;

    //Defines the IDs of the motor controllers on the intake
    public static final int INTAKE_MOTOR_ID = 60;

    //Defines the channels of the solenoids on the intake
    public static final int INTAKE_SOLENOID_FORWARD_CHANNEL = 0;
    public static final int INTAKE_SOLENOID_REVERSE_CHANNEL = 1;

    //Defines the speed the intake wheels will be run at ([-1.0, 1.0])
    public static final double INTAKE_SPEED = 1.0;

    //Defines the speed the hopper wheels will be run at ([-1.0, 1.0])
    public static final double HOPPER_SPEED = 0.5;

    //Defines the ID of the motor controller on the turret
    public static final int TURRET_MOTOR_ID = 40;

    //Defines the ID of the motor controllers on the climber
    public static final int CLIMBER_LEFT_MOTOR_ID = 31;
    public static final int CLIMBER_RIGHT_MOTOR_ID = 30;
    
    //Defines the ID of the control panel motor
    public static final int CONTROL_PANEL_MOTOR_ID = 13;

    //Defimes the speed the control panel motor will be run at
    public static final int CONTROL_PANEL_SPEED = 1;
    
    //Defines the conversion factor of encoder units to degrees
    public static final double ENCODER_UNITS_TO_DEGREES = 4096.0/360.0 * 10;
    //Turret ratio to find the angle of the turret
    public static final double TURRET_GEAR_RATIO = 10;
    public static final double TURRET_ENCODER_EDGES = 4096.0;
    public static final double TURRET_MAX_ROTATION_DEGREES = 90.0;

    public static final double TURRET_MAX_ROTATION= Constants.TURRET_MAX_ROTATION_DEGREES * Constants.ENCODER_UNITS_TO_DEGREES;

    //Turret Gains
    public static final int kTimeoutMs = 30;
    
    //PID GAINS
    public static final Gains k_Gains_Turret_Position = new Gains(0.2, 1e-9, 0.0, 0.2, 0, 1.0);

    //Gains Index
    public static final int kPIDLoopIdx = 0;

    //PID Loop Index
    public static final int PIDLOOP_INDEX = 0;

    public static final double CAMERA_HEIGHT = 0.6223;
    public static final double TARGET_HEIGHT = 2.495;
    public static final double CAMERA_ANGLE = 20;


    //Trajectory PID
    // public static final double ksVolts = 0.161;
    // public static final double kvVoltSecondsPerInch = 0.0612;
    // public static final double kaVoltSecondsSquaredPerInch = 0.00495;
    // public static final double kPDriveVel = 0.465;

    public static final double ksVolts = 0.161;
    public static final double kvVoltSecondsPerMeter = 2.409;
    public static final double kaVoltSecondsSquaredPerMeter = 0.195;
    public static final double kPDriveVel = .465;
   
    // public static final double kTrackwidthInches = 28.45;
    // public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(kTrackwidthInches);

    public static final double kTrackwidthMeters = 0.72;
    public static final DifferentialDriveKinematics kDriveKinematics =
        new DifferentialDriveKinematics(kTrackwidthMeters);

    // public static final double kMaxSpeedInchesPerSecond = 144;
    // public static final double kMaxAccelerationInchesPerSecondSquared = 144;

    public static final double kMaxSpeedMetersPerSecond = 3.658;
    //public static final double kMaxAccelerationMetersPerSecondSquared = 3.658;
    public static final double kMaxAccelerationMetersPerSecondSquared = .658;


    public static final double kRamseteB = 2;
    public static final double kRamseteZeta = 0.7;

    public static final double kEncoderDistancePerPulse = 5362.0224;

    public static final double EDGES_PER_REVOLUTION = 21448.15;
    //public static final double EDGES_PER_REVOLUTION = 2048;
    public static final double WHEEL_DIAMETER = .152;

}  
