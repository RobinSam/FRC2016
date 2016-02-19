package org.lasa.frc2016.command;

import org.lasa.frc2016.statics.Constants;
import org.lasa.frc2016.subsystem.Shooter;

public class LongShot extends HazyCommand {

    public LongShot(String nm, double t) {
        super(nm, t);
    }

    @Override
    public void start() {
        super.start();
        shooter.setMode(Shooter.Mode.CONTROLLED);
        shooter.setHoodAngle(Constants.SHOOTER_LONG_ANGLE.getInt());
        shooter.setControlPoint(Constants.SHOOTER_LONG_RPM.getDouble());
    }

    @Override
    public void stop() {
        super.stop();
    }
    
    @Override
    public boolean isDone() {
        return shooter.isSpunUp();
    }

    @Override
    public void run() {
    }
    
}