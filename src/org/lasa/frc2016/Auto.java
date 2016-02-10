package org.lasa.frc2016;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.lasa.frc2016.command.AimAndSpinUpShooter;
import org.lasa.frc2016.command.CommandManager;
import org.lasa.frc2016.command.DriveStraight;
import org.lasa.frc2016.command.SetArmPosition;
import org.lasa.frc2016.command.Shoot;

public class Auto implements Runnable {
    
    private static Auto instance;
    
    private final byte AUTONOMOUS_KEY;
    
    private Auto() {
        AUTONOMOUS_KEY = (byte) SmartDashboard.getNumber("Auto Key");
    }
    
    public static Auto getInstance() {
        return (instance == null) ? instance = new Auto() : instance;
    }
    
    @Override
    public void run() {
        
        switch(AUTONOMOUS_KEY) {
            case 0:
                CommandManager.addCommand(new DriveStraight("DriveOverDefense", 10, 24));
                break;
            case 1:
                CommandManager.addCommand(new SetArmPosition("PrepPortcullis", 10, 15, 0));
                CommandManager.addSequential(new SetArmPosition("Portcullis", 10, 15, 16));
                CommandManager.addParallel(new DriveStraight("DriveOverDefense", 10, 24));
                break;
            case 2:
                CommandManager.addCommand(new SetArmPosition("PrepSallyPort", 10, 15, 24));
                break;
            case 3:
                CommandManager.addCommand(new SetArmPosition("PrepDrawBridge", 10, 15, 29));
                CommandManager.addSequential(new SetArmPosition("DrawBridge", 10, 15, 0));
                CommandManager.addSequential(new DriveStraight("DriveOverDefense", 10, 36));
                break;
            case 4:
                CommandManager.addCommand(new SetArmPosition("PrepSeeSaw", 10, 15, 1));
                CommandManager.addSequential(new SetArmPosition("SeeSaw", 10, 15, 0));
                CommandManager.addSequential(new DriveStraight("DriveOverDefense", 10, 24));
                break;
            default:
                break;
        }
        
        if(CommandManager.empty()) {
            CommandManager.addCommand(new AimAndSpinUpShooter("PrepShooter", 10));
            CommandManager.addSequential(new Shoot("Shoot", 10));
        }
        
        
    }
}
