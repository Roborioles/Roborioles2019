/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.LoadUp;

/**
 * Add your docs here.
 */
public class Intake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private Spark intakeMotor=new Spark(0);
  private Solenoid pickyuppy= new Solenoid(0,2);
  private Solenoid innyouty= new Solenoid(0,1);
  private Solenoid Launcher = new Solenoid(0,3);



  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new LoadUp());
  }
  public void UpDown(){
    pickyuppy.set(!pickyuppy.get());

  }
  public void ExtendDistend(){
    innyouty.set(!innyouty.get());

  }
  public void BallIntake(double speed){
    intakeMotor.set(speed);
  }
  public void MoonLaunch(){
    Launcher.set(!Launcher.get());

  }

}
