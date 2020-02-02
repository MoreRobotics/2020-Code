/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

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

    //Defines the IDs of the motor controllers on the shooter
    public static final int SHOOTER_LEFT_MASTER_MOTOR_ID = 22;
    public static final int SHOOTER_LEFT_SLAVE_MOTOR_ID = 23;
    public static final int SHOOTER_RIGHT_MASTER_MOTOR_ID = 20;
    public static final int SHOOTER_RIGHT_SLAVE_MOTOR_ID = 21;
    //TODO: Add shooter feeder with ID = 24

    //Defines the channels of the solenoids on the shooter
    public static final int SHOOTER_SOLENOID_FORWARD_CHANNEL = 0;
    public static final int SHOOTER_SOLENOID_REVERSE_CHANNEL = 1;

    //Defines the speed the shooter wheels will be run ([-1.0, 1.0])
    public static final double SHOOTER_SPEED = 1.0;

    //Defines the default target shooter RPM
    public static final double SHOOTER_DEFAULT_TARGET_RPM = 5000;

    //Defines the IDs of the motor controllers on the hopper
    public static final int HOPPER_FRONT_MASTER_MOTOR_ID = 6;
    public static final int HOPPER_BACK_SLAVE_MOTOR_ID = 7;
    public static final int HOPPER_FEEDER_MOTOR_ID = 53;

    //Defines the IDs of the motor controllers on the intake
    public static final int INTAKE_MOTOR_ID = 60;

    //Defines the channels of the solenoids on the intake
    public static final int INTAKE_SOLENOID_FORWARD_CHANNEL = 2;
    public static final int INTAKE_SOLENOID_REVERSE_CHANNEL = 3;

    //Defines the speed the intake wheels will be run at ([-1.0, 1.0])
    public static final double INTAKE_SPEED = 1.0;

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
    public static final double ENCODER_UNITS_TO_DEGREES = 4096/360;

    //Defines the conversion factor for rpm to encoder units / 100 ms 
    public static final double RPM_TO_ENCODER_UNITS_PER_100_MS = 4096/600;

    //Defines the port of the driver's Xbox controller
    public static final int DRIVER_CONTROLLER_PORT = 0;

    //Defines the port of the operator's Xbox controller
    public static final int OPERATOR_CONTROLLER_PORT = 1;
    
    
}
