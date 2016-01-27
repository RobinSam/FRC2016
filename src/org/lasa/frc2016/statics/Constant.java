package org.lasa.frc2016.statics;

import com.ni.vision.NIVision.Range;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Constant {

    public static final int NIVISION_IMAGE_BORDER_SIZE = 480;
    
    public static final Range HAZY_HUE_RANGE = new Range(44, 88);
    public static final Range HAZY_SATURATION_RANGE = new Range(199, 255);
    public static final Range HAZY_LUMINENCE_RANGE = new Range(37, 135);

    // DriveTrain PID
    public static final double DRIVE_TRAIN_PID_KP = 1;
    public static final double DRIVE_TRAIN_PID_KI = 1;
    public static final double DRIVE_TRAIN_PID_KD = 1;
    public static final double DRIVE_TRAIN_PID_KF = 1;
    public static final double DRIVE_TRAIN_PID_DONE_BOUND = 1000;
    public static final double DRIVE_TRAIN_PID_MAXU = 1;
    public static final double DRIVE_TRAIN_PID_MINU = -1;

    // FlyWheel PID
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

    public void loadFromFile() {
        try {
            BufferedReader r = new BufferedReader(new FileReader(new File("pathname")));
            String line, key;
            double value;
            int spaceIndex;
            while ((line = r.readLine()) != null) {
                spaceIndex = line.indexOf(" ");
                key = line.substring(0, spaceIndex);
                value = Double.valueOf(line.substring(spaceIndex + 1));
            }

            r.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
