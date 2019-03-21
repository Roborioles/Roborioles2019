/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LaunchWedge extends Command {
  int delayCount = 0;
  boolean isComplete = false;
  boolean runningDelay = false;
  public LaunchWedge() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (!Robot.m_intake.getWedgeSolenoid()) {
      if (!Robot.m_intake.isSecureServoFlipdown() && !runningDelay) {
        Robot.m_intake.WedgeShoot();
        isComplete = true;
      }
      else if (Robot.m_intake.isSecureServoFlipdown() || runningDelay) {
        runningDelay = true;
        isComplete = false;
        Robot.m_intake.FlipServo(Robot.m_intake.SecureServoLeft,25);
        Robot.m_intake.FlipServo(Robot.m_intake.SecureServoRight,70);
        delayCount ++;
        if (delayCount == 12) {
          delayCount = 0;
          runningDelay = false;
        }
      }
  }
  else {
    Robot.m_intake.WedgeShoot();
    isComplete = true;
  }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isComplete;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
