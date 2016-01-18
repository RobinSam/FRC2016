package org.lasa.frc2016.vision;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.CoordinateSystem;
import com.ni.vision.NIVision.FindEdgeOptions2;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ROI;
import com.ni.vision.NIVision.StraightEdgeOptions;
import com.sun.org.apache.bcel.internal.Constants;
import edu.wpi.first.wpilibj.vision.AxisCamera;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lasa.frc2016.statics.HazyConstant;

public class HazyVision implements Runnable { // I could swap this out with Thread

    AxisCamera axis;
    Image image;
    ROI roi;
    CoordinateSystem plane;
    FindEdgeOptions2 fe02;
    StraightEdgeOptions se0;

    public HazyVision(AxisCamera axis, NIVision.Image image) {
        this.axis = axis;
        this.image = image;
        this.roi = NIVision.imaqCreateROI();
        this.plane = NIVision.imaqCalibrationSetAxisInfo(image);
        this.fe02 = new FindEdgeOptions2();
        this.se0 = new StraightEdgeOptions();
        this.axis.writeResolution(AxisCamera.Resolution.k640x480);
        this.axis.writeMaxFPS(5);
        this.axis.writeExposurePriority(0);
    }

    @Override
    public void run() {
        try {
            this.getImage();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Meow");
        }
    }

    private void getImage() {
        axis.getImage(image);
        this.changeHSL();
        this.filterContours();
        this.drawRect();
    }
    
    private void changeHSL() {
        NIVision.imaqColorThreshold(null, image, 0, NIVision.ColorMode.HSL, HazyConstant.HAZY_HUE_RANGE, HazyConstant.HAZY_SATURATION_RANGE, HazyConstant.HAZY_SATURATION_RANGE);
    }

    private void filterContours() {
        NIVision.imaqFindEdge2(image, roi, plane, plane, fe02, se0);
    }

    private void drawRect() {
        NIVision.imaqDrawLineOnImage(null, image, NIVision.DrawMode.DRAW_VALUE, new NIVision.Point(123, 234), new NIVision.Point(123, 567), 15);

    }
}