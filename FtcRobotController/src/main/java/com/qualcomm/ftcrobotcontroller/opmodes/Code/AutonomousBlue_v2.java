package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.RobotLog;

/**
 * Created by Steven on 1/9/2016.
 */
public class AutonomousBlue_v2 extends LinearOpMode {
    DcMotor motorFR;
    DcMotor motorFL;
    DcMotor motorBR;
    DcMotor motorBL;
    DcMotor manipulator;
    Servo climber;
    ColorSensor colorSensorR;
    ColorSensor colorSensorL;
    DeviceInterfaceModule cdim;
    TouchSensor touch;
    int BR = 0;
    int BL = 0;
    int avg = 0;
    int[] colorL = new int[3];
    int[] colorR = new int[3];



    @Override
    public void runOpMode() throws InterruptedException {
        try {
            waitOneFullHardwareCycle();
        } catch (InterruptedException e) {
            RobotLog.e(e.getMessage());
        }

        motorFR = hardwareMap.dcMotor.get("motorFR");
        motorFL = hardwareMap.dcMotor.get("motorFL");
        motorBR = hardwareMap.dcMotor.get("motorBR");
        manipulator = hardwareMap.dcMotor.get("manipulator");
        motorBL = hardwareMap.dcMotor.get("motorBL");
        climber = hardwareMap.servo.get("climber");
        cdim = hardwareMap.deviceInterfaceModule.get("cdim");
        colorSensorL = hardwareMap.colorSensor.get("colorSensorL");
        colorSensorR = hardwareMap.colorSensor.get("colorSensorR");
        climber.setPosition(1);

        try {
            waitForStart();
        } catch (InterruptedException e) {
            RobotLog.e(e.getMessage());
        }

        backwardsWithMani(1, 4300);
        turnLeft(.5, 600);
        backwardsWithMani(1, 1300);
        turnRight(.5, 950);
        backwardsWithMani(.5 , 2400);
        dropClimbers();
        moveForward(1, 250);
        turnRight(.5, 925);
        backwardsWithMani(1, 1000);
    }

    public void getLeftColor (){

        colorL[0] = colorSensorL.red();
        colorL[1] = colorSensorL.blue();
        colorL[2] = colorSensorL.green();
    }

    public void getRightColor (){
        colorR[0] = colorSensorR.red();
        colorR[1] = colorSensorR.blue();
        colorR[2] = colorSensorR.green();
    }

    public void followToWall(double speed){
        while(!touch.isPressed()){
            getLeftColor();
            getRightColor();
            if (colorL[0] > 1000 && colorL[1] > 1000 && colorL[2] > 1000){
                motorFR.setPower(speed);
                motorBR.setPower(speed);
                motorFL.setPower(speed);
                motorBL.setPower(speed);
            }
            else if (colorR[0] > 1000 && colorR[1] > 1000 && colorR[2] > 1000){
                motorFR.setPower(-speed);
                motorBR.setPower(-speed);
                motorFL.setPower(-speed);
                motorBL.setPower(-speed);
            }
            else{
                motorFR.setPower(-speed);
                motorBR.setPower(speed);
                motorFL.setPower(-speed);
                motorBL.setPower(speed);
            }
        }
        reset();
    }
    public void followToWallWithMani(double speed){
        manipulator.setPower(-1);
        while(!touch.isPressed()){
            getLeftColor();
            getRightColor();
            if (colorL[0] > 1000 && colorL[1] > 1000 && colorL[2] > 1000){
                motorFR.setPower(speed);
                motorBR.setPower(speed);
                motorFL.setPower(speed);
                motorBL.setPower(speed);
            }
            else if (colorR[0] > 1000 && colorR[1] > 1000 && colorR[2] > 1000){
                motorFR.setPower(-speed);
                motorBR.setPower(-speed);
                motorFL.setPower(-speed);
                motorBL.setPower(-speed);
            }
            else{
                motorFR.setPower(-speed);
                motorBR.setPower(speed);
                motorFL.setPower(-speed);
                motorBL.setPower(speed);
            }
        }
        reset();
    }

    public void moveForward(double speed, int distance) {
        getAvg();
        motorFR.setPower(-speed);
        motorFL.setPower(speed);
        motorBR.setPower(-speed);
        motorBL.setPower(speed);
        while (avg < distance) {
            getAvg();
            showData();
            try {
                waitOneFullHardwareCycle();
            } catch (InterruptedException e) {
                RobotLog.e(e.getMessage());
            }
        }
        reset();
    }

    public void senseLine (int speed){
        getRightColor();
        while(colorR[0] <= 400 && colorR[1] <= 400 && colorR[2] <= 400){
            getRightColor();
            motorFL.setPower(1);
            motorBL.setPower(1);
            motorFR.setPower(-1);
            motorBR.setPower(-1);
            manipulator.setPower(-1);
        }
        reset();

    }

    public void moveBackwards(double speed, int distance) {
        getAvg();
        motorFR.setPower(speed);
        motorFL.setPower(-speed);
        motorBR.setPower(speed);
        motorBL.setPower(-speed);
        avg = 0;
        while (avg < distance) {
            getAvg();
            showData();
            try {
                waitOneFullHardwareCycle();
            } catch (InterruptedException e) {
                RobotLog.e(e.getMessage());
            }
        }
        reset();
    }

    public void forwardWithMani(double speed, int distance) {
        getAvg();
        motorFR.setPower(-speed);
        motorFL.setPower(speed);
        motorBR.setPower(-speed);
        motorBL.setPower(speed);
        manipulator.setPower(-1);
        while (avg < distance) {
            getAvg();
            showData();
            try {
                waitOneFullHardwareCycle();
            } catch (InterruptedException e) {
                RobotLog.e(e.getMessage());
            }
        }
        reset();
    }

    public void backwardsWithMani(double speed, int distance) {
        getAvg();
        motorFR.setPower(speed);
        motorFL.setPower(-speed);
        motorBR.setPower(speed);
        motorBL.setPower(-speed);
        manipulator.setPower(-1);
        while (avg < distance) {
            getAvg();
            showData();
            try {
                waitOneFullHardwareCycle();
            } catch (InterruptedException e) {
                RobotLog.e(e.getMessage());
            }
        }
        reset();
    }

    public void turnRight(double speed, int distance) {
        getAvg();
        motorFR.setPower(speed);
        motorFL.setPower(speed);
        motorBR.setPower(speed);
        motorBL.setPower(speed);
        while (avg < distance) {
            getAvg();
            showData();
            try {
                waitOneFullHardwareCycle();
            } catch (InterruptedException e) {
                RobotLog.e(e.getMessage());
            }
        }
        reset();
    }

    public void turnLeft(double speed, int distance) {
        getAvg();
        motorFR.setPower(-speed);
        motorFL.setPower(-speed);
        motorBR.setPower(-speed);
        motorBL.setPower(-speed);
        while (avg < distance) {
            getAvg();
            showData();
            try {
                waitOneFullHardwareCycle();
            } catch (InterruptedException e) {
                RobotLog.e(e.getMessage());
            }
        }
        reset();
    }

    public void dropClimbers() {
        climber.setPosition(0);
        try {
            Thread.sleep(1500);
        } catch (Exception  e) {
            e.printStackTrace();
        }
        climber.setPosition(1);
    }

    public void reset(){
        motorFR.setPower(0);
        motorFL.setPower(0);
        motorBR.setPower(0);
        motorBL.setPower(0);
        manipulator.setPower(0);
        avg = 0;
        motorBL.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorBR.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            RobotLog.e(e.getMessage());
        }
        motorBL.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motorBR.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    }

    public void getAvg() {
        BR = Math.abs(motorBR.getCurrentPosition());
        BL = Math.abs(motorBL.getCurrentPosition());
        avg = (BR + BL) / 2;
    }

    public void showData() {
        telemetry.addData("Encoder Value", avg);
    }
}
