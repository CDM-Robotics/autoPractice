// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.rmi.server.RMIClassLoader;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  WPI_TalonFX r_Master = new WPI_TalonFX(2);
  WPI_TalonFX r_Slave = new WPI_TalonFX(1);
  WPI_TalonFX l_Master = new WPI_TalonFX(14); 
  WPI_TalonFX l_Slave = new WPI_TalonFX(13); 


  WPI_TalonFX LeftMaster = new WPI_TalonFX(15); 
  WPI_TalonFX RightMaster = new WPI_TalonFX(12); 

  double PercentOutput = 0; 
  @Override
  public void robotInit() {

    r_Slave.follow(r_Master);
    l_Slave.follow(l_Master);

    l_Master.setInverted(true);
    l_Slave.setInverted(true);

  }

 
  @Override
  public void robotPeriodic() {}

  
  @Override
  public void autonomousInit() {
    r_Master.setSelectedSensorPosition(0); 
    l_Master.setSelectedSensorPosition(0); 
    r_Slave.follow(r_Master);
    l_Slave.follow(l_Master);

    PercentOutput = 0; 

  }

  /** This function is called periodically during autonomous. 
   * 
   * Begin your program here 
  */
  @Override
  public void autonomousPeriodic() {

    SmartDashboard.putNumber("ticks", r_Master.getSelectedSensorPosition());
  r_Master.getSelectedSensorPosition();
  l_Master.getSelectedSensorPosition(); 
  //one rotation of the motor is 2048 ticks
  //one wheel rotation is 5 motor rotations 
  //circumference of the wheel is 18.84956


if (r_Master.getSelectedSensorPosition() < 32594) {

PercentOutput = 0.07; 
}else {
  PercentOutput = 0; 
}
  

  

  


SmartDashboard.putNumber("speed", PercentOutput);  
  r_Master.set(ControlMode.PercentOutput, PercentOutput);  
  l_Master.set(ControlMode.PercentOutput, PercentOutput);
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
  
  LeftMaster.follow(RightMaster);
  LeftMaster.setInverted(true);


  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {

    // run this for 10 seconds then stop 
    // the loop runs 50 times each second

    RightMaster.set(ControlMode.PercentOutput, .63);

  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}