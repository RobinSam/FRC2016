package org.lasa.frc2016;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.lasa.lib.HazyIterative;
import org.lasa.frc2016.input.DriverInput;
import org.lasa.frc2016.input.SensorInput;
import org.lasa.frc2016.statics.Constants;
import org.lasa.frc2016.subsystem.Drivetrain;
import org.lasa.frc2016.subsystem.Intake;
import org.lasa.frc2016.command.CommandManager;
import org.lasa.frc2016.subsystem.Arm;
import org.lasa.frc2016.subsystem.Shooter;
import org.lasa.frc2016.vision.HazyVision;

public class Robot extends HazyIterative {

    ScheduledExecutorService scheduler;
    Drivetrain drivetrain;
    Shooter shooter;
    Intake intake;
    Arm arm;
    DriverInput driverInput;
    SensorInput sensorInput;
    Constants constants;

    private static int time;

    private void pushToDashboard() {
        time++;
        SmartDashboard.putNumber("Time", time);
        drivetrain.pushToDashboard();
        //shooter.pushToDashboard();
        intake.pushToDashboard();
        arm.pushToDashboard();
    }

    private void initSubsystems() {
        constants.loadFromFile();
        drivetrain.initSubsystem();
        //shooter.initSubsystem();
        intake.initSubsystem();
        arm.initSubsystem();
        sensorInput.start();
//        HazyVision.getInstance().updateConstants();
    }
    
    public static int getTime() {
        return time;
    }

    @Override
    public void robotInit() {
        constants = new Constants();
//        new Thread(HazyVision.getInstance()).start();
//        scheduler = Executors.newScheduledThreadPool(1);
//        final ScheduledFuture<?> visionHandler = scheduler.scheduleAtFixedRate(HazyVision.getInstance(), (long)Constants.VISIONHANDLER_INITIAL_DELAY.getDouble(), (long)Constants.VISIONHANDLER_PERIOD.getDouble(), TimeUnit.MILLISECONDS);
        new Thread(HazyVision.getInstance()).start();
        drivetrain = Drivetrain.getInstance();
        //shooter = Shooter.getInstance();
        intake = Intake.getInstance();
        arm = Arm.getInstance();
        driverInput = DriverInput.getInstance();
        sensorInput = SensorInput.getInstance();
    }

    @Override
    public void teleopInit() {
        initSubsystems();
        time = 0;
    }

    @Override
    public void teleopPeriodic() {
        CommandManager.run();
        driverInput.run();
        drivetrain.run();
        //shooter.run();
        pushToDashboard();
    }

    @Override
    public void teleopContinuous() {
        sensorInput.run();
        arm.run();
        intake.run();
    }   

    @Override
    public void autonomousInit() {
        initSubsystems();
        time = 0;
    }

    @Override
    public void autonomousPeriodic() {
        CommandManager.run();
        shooter.run();
        pushToDashboard();
    }

    @Override
    public void autonomousContinuous() {
        sensorInput.run();
        drivetrain.run();
        arm.run();
        intake.run();
    }

    @Override
    public void disabledInit() {
        CommandManager.cancelAll();
        initSubsystems();
        time = 0;
    }

    @Override
    public void disabledPeriodic() {
        pushToDashboard();
    }

    @Override
    public void disabledContinuous() {
        sensorInput.run();
    }
}
