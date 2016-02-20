package org.lasa.lib.controlloop;

public class TorquePV extends ControlLoop {

    private double kP;
    private double kV;
    private double kI;
    private double kFFV;
    private double kFFA;
    private double errorSum;

    private TorqueTMP profile;
    private double actualPosition;
    private double actualVelocity;
    private double positionDoneRange;

    public TorquePV() {
        super();

        kP = 0.0;
        kV = 0.0;
        kI = 0.0;
        kFFV = 0.0;
        kFFA = 0.0;
        errorSum = 0.0;
    }

    public double calculate(TorqueTMP tmProfile, double currentPosition, double currentVelocity) {
        //Dont worry about what this does for now.
        double voltageAdjustment = /*tunedVoltage / ds.getBatteryVoltage();*/ 1.0;

        profile = tmProfile;
        setPoint = profile.getCurrentVelocity();
        currentValue = currentVelocity;

        double output = 0.0;

        //Position P
        double error = profile.getCurrentPosition() - currentPosition;
        output += (error * kP);

        //Velocity P
        double velocityError = profile.getCurrentVelocity() - currentVelocity;
        output += (velocityError * kV);

        //Velocity FeedForward
        output += (profile.getCurrentVelocity() * kFFV * voltageAdjustment);

        //Acceleration FeedForward
        output += (profile.getCurrentAcceleration() * kFFA * voltageAdjustment);
        
        output += errorSum * kI;
        if ((1 >= kI * errorSum) && (-1 <= kI* errorSum)) {
            errorSum += error;
        } else if (errorSum > 0) {
            errorSum = 1;
        } else if (errorSum < 0) {
            errorSum = -1;
        }

        return output;
    }

    public void setGains(double p, double v, double i, double ffV, double ffA) {
        kP = p;
        kV = v;
        kI = i;
        kFFV = ffV;
        kFFA = ffA;
    }

    public void reset() {
    }

    public void setPositionDoneRange(double range) {
        positionDoneRange = range;
    }

    @Override
    public boolean isDone() {
        if ((Math.abs(profile.getCurrentPosition() - actualPosition) < positionDoneRange)
                && Math.abs(profile.getCurrentVelocity() - actualVelocity) < doneRange) {
            doneCyclesCount++;
        } else {
            doneCyclesCount = 0;
        }

        return (doneCyclesCount > minDoneCycles);
    }

    public boolean onTrack() {
        if(profile == null) {
            return false;
        }
        return Math.abs(profile.getCurrentVelocity() - actualVelocity) < doneRange;
    }
}
