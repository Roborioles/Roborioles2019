/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.TopHatch;
import frc.robot.commands.DeploySecureServos;
import frc.robot.commands.ElevatorFloor;
import frc.robot.commands.HatchLetGo;
import frc.robot.commands.hatchHold;
import frc.robot.commands.setHatchSol;
import frc.robot.commands.Launch;
import frc.robot.commands.LaunchWedge;
import frc.robot.commands.LowerHatch;
import frc.robot.commands.MiddleHatch;
import frc.robot.commands.OverrideElevator;
import frc.robot.commands.RaiseLower;
import frc.robot.commands.ZeroEncoder;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());

 Joystick stick = new Joystick(0);
 Joystick gamePad=new Joystick(1);
 // Button button1=new JoystickButton(stick, 1);
 // Button button2=new JoystickButton(stick, 2);
 // Button button3=new JoystickButton(stick, 3);
 // Button button4=new JoystickButton(stick, 4);
 // Button button8=new JoystickButton(stick, 8);
 // Button button9=new JoystickButton(stick, 9);
 
 Button joystickButton11 = new JoystickButton(stick, 11);
 Button joystickButton10 = new JoystickButton(stick, 10);
 Button joystickButton6 = new JoystickButton(stick, 6);
 Button gamepadButton1 = new JoystickButton(gamePad, 1);
 Button gamepadButton2 = new JoystickButton(gamePad, 2);
 Button gamepadButton3 = new JoystickButton(gamePad, 3);
 Button gamepadButton4 = new JoystickButton(gamePad, 4);
 Button gamepadButton5 = new JoystickButton(gamePad, 5);
 Button gamepadButton6 = new JoystickButton(gamePad, 6);
 Button gamepadButton7 = new JoystickButton(gamePad, 7);
 Button gamepadButton8 = new JoystickButton(gamePad, 8);
 Button gamepadButton9 = new JoystickButton(gamePad, 9);
 Button gamepadButton10= new JoystickButton(gamePad, 10);
public OI(){

  // button8.whenPressed(new Stretch());
  // button9.whenPressed(new RaiseLower());
  // button1.whenPressed(new Launch());
  gamepadButton1.whenPressed(new LowerHatch());
  gamepadButton3.whenPressed(new MiddleHatch());
  gamepadButton4.whenPressed(new TopHatch());
  gamepadButton2.whenPressed(new ElevatorFloor());
  gamepadButton5.whenPressed(new HatchLetGo());
  gamepadButton7.whenPressed(new hatchHold());
  gamepadButton10.whenPressed(new setHatchSol());

  gamepadButton8.whenPressed(new Launch(true));
  gamepadButton8.whenReleased(new Launch(false));
  joystickButton10.whenPressed(new Launch(true));
  joystickButton10.whenReleased(new Launch(false));
  joystickButton11.whenPressed(new RaiseLower());
  gamepadButton9.whenPressed(new LaunchWedge());
  gamepadButton6.whenPressed(new DeploySecureServos());
  joystickButton6.whenPressed(new OverrideElevator());
  joystickButton6.whenReleased(new ZeroEncoder());
  // button2.whenPressed(new hatchHold());
  // button3.whenPressed(new setHatchSol());
  // button4.whenPressed(new hatchHold());
}

public Joystick getStick()
{
  return stick;
}

  public Joystick getGamepad()
  {
    return gamePad;
  }

  public boolean gamepadPresent() {
    if (!gamePad.getName().equals("")) {
      return true;
    }
    else {
      return false;
    }
  }
}
