package org.usfirst.frc.team6627.robot.commands;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveAuto extends Command {
	RobotDrive mainRD;
	
	Timer timer = new Timer();

    public DriveAuto(RobotDrive rd) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	mainRD = rd;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.reset();
    	timer.start();
    	
    	mainRD.drive(-0.5, 0.0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// Drive for 2 seconds
    	if (timer.get() < 2.0) {
    		mainRD.drive(-0.5, 0.0); // drive forwards half speed
    	} else if (timer.get() < 5.0) {
    		mainRD.drive(0.5, 0.0); // stop robot
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (timer.get() > 5.0) return true;
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	mainRD.drive(0.0,  0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
