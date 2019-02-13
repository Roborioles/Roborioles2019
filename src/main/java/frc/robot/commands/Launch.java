/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Launch extends Command {
  private boolean launchout = false; 
  private int delaycount=0;
  public Launch(boolean launchout) {
    this.launchout=launchout;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_intake);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() { 
    if(launchout){
      if(Robot.m_intake.getdelayLaunch()){
        if (delaycount<13) {
          delaycount++;
        }else{
          Robot.m_intake.MoonLaunch(true);
          Robot.m_intake.setdelayLaunch(false);
        }
      }else if(Robot.m_intake.isFlipdown()){
       Robot.m_intake.FlipServoUp();
       Robot.m_intake.setdelayLaunch(true);
       delaycount=0;
      }
      else{
        Robot.m_intake.MoonLaunch(true);
      }
    }
    else {
      Robot.m_intake.MoonLaunch(false);
      Robot.m_intake.setdelayLaunch(false);
    }
   
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return !Robot.m_intake.getdelayLaunch();
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
