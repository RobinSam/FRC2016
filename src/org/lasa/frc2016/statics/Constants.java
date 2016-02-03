package org.lasa.frc2016.statics;

import org.lasa.lib.HazyConstant;

public class Constants extends HazyConstant {

    // Vision Constants
    public static final long VISIONHANDLER_INITIAL_DELAY = 10;
    public static final long VISIONHANDLER_PERIOD = 200;

    public static final int USBCAMERA_IMAGE_WIDTH = 480;
    public static final int USBCAMERA_IMAGE_HEIGHT = 640;

    public static final int HAZYVISION_HUE_LOWER_BOUND = 44;
    public static final int HAZYVISION_HUE_UPPER_BOUND = 88;
    public static final int HAZYVISION_SATURATION_LOWER_BOUND = 199;
    public static final int HAZYVISION_SATURATION_UPPER_BOUND = 255;
    public static final int HAZYVISION_LUMINENCE_LOWER_BOUND = 37;
    public static final int HAZYVISION_LUMINENCE_UPPER_BOUND = 135;

    // Drivetrain Stufffff
    public static final double DRIVE_SENSITIVITY = .65;

    // Drivetrain PID
    public static final double DRIVETRAIN_PID_KP = 1;
    public static final double DRIVETRAIN_PID_KI = 1;
    public static final double DRIVETRAIN_PID_KD = 1;
    public static final double DRIVETRAIN_PID_KF = 1;
    public static final double DRIVETRAIN_PID_DONE_BOUND = 1000;
    public static final double DRIVETRAIN_PID_MAXU = 1;
    public static final double DRIVETRAIN_PID_MINU = -1;

    // Flywheel PID
    public static final double FLYWHEEL_PID_KP = 0;
    public static final double FLYWHEEL_PID_KI = 0;
    public static final double FLYWHEEL_PID_KD = 0;
    public static final double FLYWHEEL_PID_KF = 0;
    public static final double FLYWHEEL_PID_DONE_BOUND = 1000;
    public static final double FLYWHEEL_PID_MAXU = 1;
    public static final double FLYWHEEL_PID_MINU = -1;
    public static final int FLYWHEEL_PID_IZONE = 100;
    public static final double FLYWHEEL_PID_RAMPRATE = 36;
    public static final int FLYWHEEL_PID_PROFILE = 0;
    public static final int FLYWHEEL_SPINUP_SPEED = 16000;

    // HazyJoystick
    public static final double DEADBAND = .1;

    @Override
    public String getFileLocation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}