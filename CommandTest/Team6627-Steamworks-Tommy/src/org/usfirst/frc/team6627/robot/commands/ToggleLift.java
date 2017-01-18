package org.usfirst.frc.team6627.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6627.robot.Robot;

/**
 *
 */
public class ToggleLift extends Command {
	boolean isUp = false;
	Timer timer = new Timer();
	
	DoubleSolenoid sol;
	
	public ToggleLift(DoubleSolenoid givenSol) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.exampleSubsystem);
		
		sol = givenSol;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		timer.reset();
		timer.start();
		
		sol.set(DoubleSolenoid.Value.kForward);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if (timer.get() > 1.0 && timer.get() < 2.5)
			sol.set(DoubleSolenoid.Value.kOff);
		else if (timer.get() > 2.5 && timer.get() < 4.5)
			sol.set(DoubleSolenoid.Value.kReverse);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		if (timer.get() > 5)
			return true;
		
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		sol.set(DoubleSolenoid.Value.kOff);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
