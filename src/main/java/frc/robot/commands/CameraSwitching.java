/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class CameraSwitching extends InstantCommand {
  /**
   * Add your docs here.
   */
  boolean camera1;
  public CameraSwitching() {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

   // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.server.setSource(this.camera1 ?Robot.camera1: Robot.camera2);
    this.camera1=!this.camera1;

  }

}
