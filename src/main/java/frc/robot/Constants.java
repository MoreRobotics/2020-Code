/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.function.DoubleBinaryOperator;

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
    public static final int SHOOTER_LEFT_MOTOR_MASTER_ID = 0;
    public static final int SHOOTER_LEFT_MOTOR_SLAVE_ID = 1;
    public static final int SHOOTER_RIGHT_MOTOR_ID = 11;
    public static final int SHOOTER_EXTRA_MOTOR_ID = 10;

    //Defines the channels of the solenoids on the shooter
    public static final int SHOOTER_SOLENOID_FORWARD_CHANNEL = 0;
    public static final int SHOOTER_SOLENOID_REVERSE_CHANNEL = 1;

    //Defines the speed the shooter wheels will be run ([-1.0, 1.0])
    public static final double SHOOTER_SPEED = 1.0;

    //Defines the IDs of the motor controllers on the hopper
    public static final int HOPPER_FRONT_MASTER_MOTOR_ID = 0;
    public static final int HOPPER_BACK_SLAVE_MOTOR_ID = 1;
    public static final int HOPPER_FEEDER_MOTOR_ID = 2;

    //Defines the IDs of the motor controllers on the intake
    public static final int INTAKE_MOTOR_ID = 0;

    //Defines the channels of the solenoids on the intake
    public static final int INTAKE_SOLENOID_FORWARD_CHANNEL = 2;
    public static final int INTAKE_SOLENOID_REVERSE_CHANNEL = 3;

    //Defines the speed the intake wheels will be run at ([-1.0, 1.0])
    public static final double INTAKE_SPEED = 1.0;

    //Defines the ID of the motor controller on the turret
    public static final int TURRET_MOTOR_ID = 0;

    //Defines the ID of the control panel motor
    public static final int CONTROL_PANEL_MOTOR_ID = 0;

    //Defimes the speed the control panel motor will be run at
    public static final int CONTROL_PANEL_SPEED = 1;
    
    //Defines the conversion factor of encoder units to degrees
    public static final double ENCODER_UNITS_TO_DEGREES = 4096/360;

    //Defines the conversion factor of encoder units / 100 ms to rev/min
    public static final double ENCODER_UNITS_PER_100_MS_TO_REV_PER_MIN = 60/(0.1*4096);

    //Defines the port of the driver's Xbox controller
    public static final int DRIVER_CONTROLLER_PORT = 0;

    //Defines the port of the operator's Xbox controller
    public static final int OPERATOR_CONTROLLER_PORT = 1;
    
    //Defines the PID loop coefficients
    public static final double P = 1.0;
    public static final double I = 0.0;
    public static final double D = 0.0;
}
