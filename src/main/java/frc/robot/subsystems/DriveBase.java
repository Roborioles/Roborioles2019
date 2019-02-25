/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.commands.Drive;


/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveBase extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private CANSparkMax leftMotor1 = new CANSparkMax(1,MotorType.kBrushless);
  private CANSparkMax leftMotor2 = new CANSparkMax(2,MotorType.kBrushless);
  private CANSparkMax rightMotor1 = new CANSparkMax(3,MotorType.kBrushless);
  private CANSparkMax rightMotor2 = new CANSparkMax(4,MotorType.kBrushless);
  // private WPI_TalonSRX leftMotor1 = new WPI_TalonSRX(1);
  // private WPI_TalonSRX leftMotor2 = new WPI_TalonSRX(2);
  //private WPI_TalonSRX rightMotor1 = new WPI_TalonSRX(3);
  // private WPI_TalonSRX rightMotor2 = new WPI_TalonSRX(4);

  private DifferentialDrive robotDrive = new DifferentialDrive(leftMotor1, rightMotor1);
  private Solenoid shiftSolenoid = new Solenoid(0, 0);
  private CANEncoder encoderShift = leftMotor1.getEncoder();


  public DriveBase(){
    leftMotor2.follow(leftMotor1);
    rightMotor2.follow(rightMotor1);

  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new Drive());
  }
  public void DriveWithJoySticks(double speed, double rotation){
    robotDrive.arcadeDrive(speed, rotation);



  }
  public void AutoShift(double upshift,double downshift){
   // double motorVelocityleft=leftMotor1.getSensorCollection().getQuadratureVelocity();
   // double motorVelocityright=rightMotor1.getSensorCollection().getQuadratureVelocity();
   // shiftSolenoid.set(true);
    
    boolean ishigh=shiftSolenoid.get();
    double rpmleft= encoderShift.getVelocity();
    //int rpmleft= (int) ((motorVelocityleft * 10) / 4096 * 60);
    //int rpmright= (int) ((motorVelocityright * 10) / 4096 * 60);
    // SmartDashboard.putNumber("DB/String 5", rpm);
    System.out.println("RPM Left: "+rpmleft);
    rpmleft=java.lang.Math.abs(rpmleft);
    if(ishigh== false && rpmleft >upshift){
      shiftSolenoid.set(true);
  
    }
    if(ishigh== true && rpmleft <downshift){
      shiftSolenoid.set(false);
      
    }

  }
  public double Deadband(double joystickValue, double deadbandZone) {
    if (joystickValue < deadbandZone && joystickValue > deadbandZone * -1 ) {
      return 0.0;
    }
    else {
      return (joystickValue - (Math.abs(joystickValue)/joystickValue * deadbandZone)/(1-deadbandZone));
    }
  }
}
