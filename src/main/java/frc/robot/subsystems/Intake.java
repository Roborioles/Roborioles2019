/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.commands.LoadUp;

/**
 * Add your docs here.
 */
public class Intake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private Spark intakeMotor=new Spark(0);
  private boolean delayLaunch = false;
  private Solenoid pickyuppy= new Solenoid(0,2);
  private Solenoid innyouty= new Solenoid(0,1);
  private Solenoid Launcher = new Solenoid(0,3);
  private Solenoid hatchAlign = new Solenoid(0,4);
  private Servo Flippy= new Servo(1);
  public boolean getdelayLaunch(){
    return delayLaunch;
  }
  public void setdelayLaunch(boolean dl){
    delayLaunch=dl;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new LoadUp());
  }
  public void IntakeExecute() {

    //double dvalue = Double.valueOf(SmartDashboard.getString("DB/String 9", "21"));
    //SmartDashboard.putString("DB/String 4", Double.toString(Flippy.getAngle()));
    //Flippy.setAngle(dvalue);
    // */
    double leftJoystick = -1.0 * Robot.m_oi.getGamepad().getY();
    intakeMotor.set(leftJoystick);
    double rightJoystick = -1.0 * Robot.m_oi.getGamepad().getRawAxis(3);
    if (rightJoystick >= 0.5) {
      innyouty.set(true);
    }
    else if (rightJoystick <= -0.5) {
      innyouty.set(false);
    }
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
  public void MoonLaunch(boolean launchout){
    Launcher.set(launchout);

  } 
  public void Flip(){
    System.out.println("Flip");
  
   /* if(Flippy.getPosition()==0.5){
      System.out.println("0.0");
      Flippy.set(0.0);
    }else if(Flippy.getPosition()==0.0){
      System.out.println("0.5");
      Flippy.set(0.5);
    }*/
    if(Flippy.getAngle()==70){
      System.out.println("on"+Flippy.getAngle());
      Flippy.setAngle(25);
    } else {
      System.out.println("off"+Flippy.getAngle());
      Flippy.setAngle(70);
     }
  }
  public void HatchAlighnSet(){
    hatchAlign.set(!hatchAlign.get());
  }
  public void FlipUp(){
    System.out.println("up"+Flippy.getAngle());
    Flippy.setAngle(Flippy.getAngle()+3);
  }
  public void FlipDown(){
    System.out.println("down"+Flippy.getAngle());
    Flippy.setAngle(Flippy.getAngle()-3);
  }

  public boolean isFlipdown(){
    return Flippy.getAngle()==25;

  }
  public void FlipServoUp(){
    Flippy.setAngle(70);
  }

  public void FlipServoDown() {
      // System.out.println("on"+Flippy.getAngle());
      Flippy.setAngle(25);
  }

}
