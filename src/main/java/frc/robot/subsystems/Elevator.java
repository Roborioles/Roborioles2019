/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

//import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.FeedbackDevice;
//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.commands.ElevatorMove;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  //private WPI_TalonSRX elevatorMotor = new WPI_TalonSRX(5);
  private CANSparkMax elevatorMotor = new CANSparkMax(5, MotorType.kBrushless);
  // private CANSparkMax elevatorMotor2 = new CANSparkMax(6, MotorType.kBrushless);
  private CANEncoder elevatorEncoder = elevatorMotor.getEncoder();
  private CANPIDController elevatorPIDController = elevatorMotor.getPIDController();
  private double targetPos = 0;
  private boolean targetMode = false;
  private boolean manualMoving = false;
  private int povValue = -1;
  private boolean movingUp;
  private boolean cargoToggle = false;
  private int cycles = 0;
  private long maxTime = 0; 
  //private final static int kPIDLoopIdx = 0;
  //private final static int kTimeoutMs = 10;
  
  public Elevator() {     
    /*elevatorMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);
    elevatorMotor.setSensorPhase(true);
    
    /* set the peak and nominal outputs, 12V means full 
    elevatorMotor.configNominalOutputForward(0, kTimeoutMs);
    elevatorMotor.configNominalOutputReverse(0, kTimeoutMs);
    elevatorMotor.configPeakOutputForward(1, kTimeoutMs);
    elevatorMotor.configPeakOutputReverse(-1, kTimeoutMs);

    /* set closed loop gains in slot0 
    elevatorMotor.config_kF(kPIDLoopIdx, 0, kTimeoutMs);
    elevatorMotor.config_kP(kPIDLoopIdx, 0.12, kTimeoutMs);
    elevatorMotor.config_kI(kPIDLoopIdx, 0, kTimeoutMs);
    elevatorMotor.config_kD(kPIDLoopIdx, 0, kTimeoutMs); */
    elevatorPIDController.setP(0.8);
    elevatorPIDController.setI(0);
    elevatorPIDController.setD(140);
    elevatorPIDController.setIZone(0);
    elevatorPIDController.setFF(0); 
    elevatorPIDController.setOutputRange(-0.75,0.60);
    elevatorMotor.setClosedLoopRampRate(.25);
    //elevatorMotor.setInverted(true);
    //elevatorMotor2.follow(elevatorMotor, true);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ElevatorMove());
  }

  /*public void moveElevator(int direction) {
    if (direction == 2){
      elevatorMotor.set(0.5);
    }
    else if (direction == 1) {
      elevatorMotor.set(-0.5);
    }
    else {
      elevatorMotor.set(0);
    }
  }*/
  public void goToRevolutions(double newTargetPos, boolean withOffset) {
    double offset = -13.4;
    if (newTargetPos != targetPos) {
      cargoToggle = false;
    }
    if (withOffset == true) {
      if (cargoToggle == false) {
        targetPos = newTargetPos;
        cargoToggle = true;
      }
      else {
        targetPos = newTargetPos + offset;
        cargoToggle = false;
      }
    }
    else {
      targetPos = newTargetPos;
      cargoToggle = false;
    }
    targetMode = true;
  }
  
  public void goToInches(double inches, boolean withOffset) {
    double heightRatio = 0.0; //revolutions per inch
    goToRevolutions(inches * heightRatio, withOffset);
  }

  public void elevatorExecute() {
    long startTime = System.currentTimeMillis();
    /* double pvalue = 0.80;
    double dvalue = 140;
    pvalue = Double.valueOf(SmartDashboard.getString("DB/String 0", "0.80"));
     
    SmartDashboard.putString("DB/String 5", Double.toString(pvalue));
    SmartDashboard.putString("DB/String 6", Double.toString(dvalue));
    elevatorPIDController.setP(pvalue);
    elevatorPIDController.setD(dvalue);
    */
    if (cycles == 50) {
      cycles = 0;
      System.out.println("Position " + Double.toString(elevatorEncoder.getPosition()) + " Target: " + Double.toString(targetPos) + " Max Time: " + maxTime);
    }
    else {
      cycles ++;
    } 
    //double encoderValue = elevatorMotor.getSelectedSensorPosition(0)/4096.0;
    double encoderValue = elevatorEncoder.getPosition();
    // d-pad
    if (Robot.m_oi.gamepadPresent() == true) {
      povValue = Robot.m_oi.getGamepad().getPOV();
    }
    // move down
    if (povValue == 180) {
      targetMode = false;
      //elevatorMotor.set(ControlMode.PercentOutput, 0.5);
      movingUp = false;
      if (encoderValue >= -1.5) {
        elevatorMotor.set(0);
      }
      else {
        elevatorMotor.set(0.25);
      }
      manualMoving = true;
    }
    // move up
    else if (povValue == 0) {
      targetMode = false;
      if (encoderValue <= -45.0){
        elevatorMotor.set(0);
        // manualMoving = false;
        // goToRevolutions(encoderValue, false);
      }
      else {
        elevatorMotor.set(-0.25);
      }
      movingUp = true;
      /* if (encoderValue >= 0) {
        //elevatorMotor.set(ControlMode.PercentOutput, -0.5);
        elevatorMotor.set(-0.25);
      }
      else {
        //elevatorMotor.set(ControlMode.PercentOutput, 0);
        elevatorMotor.set(0.5);
      }*/
      manualMoving = true;
    }
    else if (povValue == -1) {
      if (!targetMode && manualMoving) {
        manualMoving = false;
        //encoderValue = elevatorMotor.getSelectedSensorPosition(0)/4096.0;
        //encoderValue = elevatorEncoder.getPosition();
        if (movingUp) {
          // adjustments maded to compensate for effect of gravity after releasing button
          if (encoderValue <= -45.0) {
            goToRevolutions(encoderValue, false);
          }
          else {
            goToRevolutions(encoderValue - 2.0, false);
          }
        }
        else {
          // adjustments maded to compensate for effect of gravity after releasing button
          if (encoderValue >= -1.5 || encoderValue <= -45.0) {
            goToRevolutions(encoderValue, false);
          }
          else {
            goToRevolutions(encoderValue + 1.0, false);
          }
        }
        
      }

      
    }
    if (targetMode) {
      executeTarget();
      //encoderValue = elevatorMotor.getSelectedSensorPosition(0)/4096.0;
      encoderValue = elevatorEncoder.getPosition();
      if (targetPos == -0.001 && encoderValue > -0.01) {
        // System.out.println("Close enough");
        //elevatorMotor.set(ControlMode.PercentOutput, 0);
        elevatorMotor.set(0);
        targetMode = false;
      }
    } 
    long endTime = System.currentTimeMillis();
    maxTime = Math.max(maxTime, endTime-startTime);
  }
  public void executeTarget() {
    //elevatorMotor.set(ControlMode.Position,targetPos * 4096.0);
    elevatorPIDController.setReference(targetPos, ControlType.kPosition);
  }
  public void elevatorInit() {
    //elevatorMotor.setSelectedSensorPosition(0);
    double encoderValue = elevatorEncoder.getPosition();
    goToRevolutions(encoderValue, false);
  }
}