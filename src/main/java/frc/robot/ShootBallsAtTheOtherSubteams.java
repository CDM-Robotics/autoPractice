// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/** Add your docs here. */
public class ShootBallsAtTheOtherSubteams {

    static WPI_TalonFX shooterLeftMaster;
    static WPI_TalonFX shooterRightMaster; 

    
    static WPI_TalonSRX shooterRotate;


    static WPI_TalonSRX mSerializer_Master;  
    static WPI_TalonSRX mSerializerHorizontal_Slave0;

    /**
     * used to push balls into the shooter to be shot out. Method should turn the serializer on when 
     * a button is pressed for as long as it is held down 
     * @param button_isPressed
     * The input from the joystick button will read true when the button is pressed and for as long as it is held down
     */
    public static void shootMethod(boolean button_isPressed) {
        if (button_isPressed == true) {
            mSerializer_Master.set(ControlMode.PercentOutput, 1);
            mSerializerHorizontal_Slave0.set(ControlMode.PercentOutput, 1);
        } else {
            mSerializer_Master.set(ControlMode.PercentOutput, 0);
            mSerializerHorizontal_Slave0.set(ControlMode.PercentOutput, 0);
        }
    }


    /**
     * Used to spin up the shooter and prepare to fire balls. Method should toggle between on and off modes. 
     * when a button is pressed it toggles on and when the button is pressed again it should turn off 
     * @param button_wasPressed
     * the input from the joystick button will read true one per button press regardless of if the button is held down 
     */
    static int ButtonButton = 0;
    public static void spinUpMethod(boolean button_wasPressed) {
        if (button_wasPressed == true) {
            ButtonButton = ButtonButton + 1;
            if (ButtonButton == 1) {
            shooterRightMaster.set(ControlMode.PercentOutput, 0.55);
            }
            if (ButtonButton == 2) {
            shooterRightMaster.set(ControlMode.PercentOutput, 0);
            ButtonButton = 0;
            }
        } /*else {
            shooterRightMaster.set(ControlMode.PercentOutput, 0);
        }*/

    }

    /**
     * Used to turn the shooter motor forward or reverse which aims the shooter. Method should move the shooter clockwise 
     * when the joystick is pushed left and counterclockwise when the joystick is moved right. 
     * @param yaw
     */
    public static void aimShooter(double yaw) {
        if ((yaw < 0.05) && (yaw > -0.05)) {
            shooterRotate.set(ControlMode.PercentOutput, 0);
        }
        if (yaw >= 0.05) {
            shooterRotate.set(ControlMode.PercentOutput, 0.5);
        }
        if (yaw <= 0.05) {
            shooterRotate.set(ControlMode.PercentOutput, -0.5);
        }
    }

    public static void setup() {
        
     shooterLeftMaster = new WPI_TalonFX(15); 
     shooterRightMaster = new WPI_TalonFX(12); 

    
     shooterRotate = new WPI_TalonSRX(4);


     mSerializer_Master = new WPI_TalonSRX(5); 
     mSerializerHorizontal_Slave0 = new WPI_TalonSRX(11);

     shooterLeftMaster.follow(shooterRightMaster);
     shooterLeftMaster.setInverted(true);
    }

}
