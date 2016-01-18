/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lasa.frc2016.statics;

import com.ni.vision.NIVision.Range;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author LASA Robotics
 */
public class HazyConstant {
    public static int NIVISION_IMAGE_BORDER_SIZE = 480;
    public static Range HAZY_HUE_RANGE = new Range(44, 88);
    public static Range HAZY_SATURATION_RANGE = new Range(199, 255);
    public static Range HAZY_LUMINENCE_RANGE = new Range(37, 135);
    
    public void loadFromFile() {
        try {
            BufferedReader r = new BufferedReader(new FileReader(new File("pathname")));
            String line, key;
            double value;
            int spaceIndex;
            while((line = r.readLine()) != null) {
                spaceIndex = line.indexOf(" ");
                key = line.substring(0,spaceIndex);
                value = Double.valueOf(line.substring(spaceIndex + 1));
            }
            
            r.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
        
    }
}
