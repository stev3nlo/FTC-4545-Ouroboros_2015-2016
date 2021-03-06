package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.DigitalChannel;
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
    Servo boxBelt;
    Servo climber;
    ColorSensor colorSensorR;
    ColorSensor colorSensorL;
    DeviceInterfaceModule cdim;
    DigitalChannel touch;
    int BR = 0;
    int BL = 0;
    int avg = 0;
    int[] colorL = new int[3];
    int[] colorR = new int[3];



    @Override
    public void runOpMode() throws InterruptedException {
        waitOneFullHardwareCycle();


        motorFR = hardwareMap.dcMotor.get("motorFR");
        motorFL = hardwareMap.dcMotor.get("motorFL");
        motorBR = hardwareMap.dcMotor.get("motorBR");
        manipulator = hardwareMap.dcMotor.get("manipulator");
        motorBL = hardwareMap.dcMotor.get("motorBL");
        climber = hardwareMap.servo.get("climber");
        cdim = hardwareMap.deviceInterfaceModule.get("cdim");
        colorSensorL = hardwareMap.colorSensor.get("colorSensorL");
        colorSensorR = hardwareMap.colorSensor.get("colorSensorR");
        boxBelt = hardwareMap.servo.get("boxBelt");
        touch = hardwareMap.digitalChannel.get("touch");
        climber.setPosition(1);
        boxBelt.setPosition(.5);


        waitForStart();

        Thread.sleep(10000);
        backwardsWithMani(1, 3500);
        dropClimbers();
//        backwardsWithMani(1, 2000);
//        turnLeft(.5, 200);
//        backwardsWithMani(1, 100);
//        //turnRight(.5, 950);
//        //backwardsWithMani(.5 , 2400);
//        moveToLine(.1);
//        followToWallWithMani(.1);
//        dropClimbers();
//        moveForward(1, 250);
//        turnRight(.5, 925);
//        backwardsWithMani(1, 1000);
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

    public void followToWall(double speed) throws InterruptedException {
        while(!touch.getState()){
            getLeftColor();
            getRightColor();
            if (colorL[0] > 1500 && colorL[1] > 2000 && colorL[2] > 2000){
                motorFR.setPower(speed);
                motorBR.setPower(speed);
                motorFL.setPower(speed);
                motorBL.setPower(speed);
            }
            else if (colorR[0] > 1500 && colorR[1] > 2000 && colorR[2] > 2000){
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
            waitOneFullHardwareCycle();
        }
        reset();
    }
    public void followToWallWithMani(double speed) throws InterruptedException {
        manipulator.setPower(1);
        while(!touch.getState()){
            waitOneFullHardwareCycle();
            getLeftColor();
            getRightColor();
            if (colorL[0] > 1500 && colorL[1] > 2000 && colorL[2] > 2000){
                motorFR.setPower(speed);
                motorBR.setPower(speed);
                motorFL.setPower(speed);
                motorBL.setPower(speed);
            }
            else if (colorR[0] > 1500 && colorR[1] > 2000 && colorR[2] > 2000){
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
            waitOneFullHardwareCycle();
        }
        reset();
    }

    //if pushing out doesn't work pulling the balls in with a good maniulator might
    public void followToWallWithManiGoingIn(double speed) throws InterruptedException {
        manipulator.setPower(1);
        while(!touch.getState()){
            getLeftColor();
            getRightColor();
            if (colorL[0] > 1500 && colorL[1] > 2000 && colorL[2] > 2000){
                motorFR.setPower(speed);
                motorBR.setPower(speed);
                motorFL.setPower(speed);
                motorBL.setPower(speed);
            }
            else if (colorR[0] > 1500 && colorR[1] > 2000 && colorR[2] > 2000){
                motorFR.setPower(-speed);
                motorBR.setPower(-speed);
                motorFL.setPower(-speed);
                motorBL.setPower(-speed);
            }
            else{
                motorFR.setPower(speed);
                motorBR.setPower(-speed);
                motorFL.setPower(speed);
                motorBL.setPower(-speed);
            }
            waitOneFullHardwareCycle();
        }
        reset();
    }

    public void moveForward(double speed, int distance) throws InterruptedException {
        getAvg();
        motorFR.setPower(-speed);
        motorFL.setPower(speed);
        motorBR.setPower(-speed);
        motorBL.setPower(speed);
        while (avg < distance) {
            getAvg();
            showData();
            waitOneFullHardwareCycle();
        }
        reset();
    }

    public void moveToLine (double speed) throws InterruptedException {
        getRightColor();
        while(colorR[0] <= 1500 && colorR[1] <= 2000 && colorR[2] <= 2000){
            getRightColor();
            motorFL.setPower(-speed);
            motorBL.setPower(-speed);
            motorFR.setPower(speed);
            motorBR.setPower(speed);
            manipulator.setPower(1);
            waitOneFullHardwareCycle();
        }
        reset();

    }

    public void moveBackwards(double speed, int distance) throws InterruptedException {
        getAvg();
        motorFR.setPower(speed);
        motorFL.setPower(-speed);
        motorBR.setPower(speed);
        motorBL.setPower(-speed);
        avg = 0;
        while (avg < distance) {
            getAvg();
            showData();
            waitOneFullHardwareCycle();

        }
        reset();
    }

    public void forwardWithMani(double speed, int distance) throws InterruptedException {
        getAvg();
        motorFR.setPower(-speed);
        motorFL.setPower(speed);
        motorBR.setPower(-speed);
        motorBL.setPower(speed);
        manipulator.setPower(1);
        while (avg < distance) {
            getAvg();
            showData();
            waitOneFullHardwareCycle();
        }
        reset();
    }

    public void backwardsWithMani(double speed, int distance) throws InterruptedException {
        getAvg();
        motorFR.setPower(speed);
        motorFL.setPower(-speed);
        motorBR.setPower(speed);
        motorBL.setPower(-speed);
        manipulator.setPower(1);
        while (avg < distance) {
            getAvg();
            showData();
            waitOneFullHardwareCycle();
        }
        reset();
    }

    public void turnRight(double speed, int distance) throws InterruptedException {
        getAvg();
        motorFR.setPower(speed);
        motorFL.setPower(speed);
        motorBR.setPower(speed);
        motorBL.setPower(speed);
        while (avg < distance) {
            getAvg();
            showData();
            waitOneFullHardwareCycle();
        }
        reset();
    }

    public void turnLeft(double speed, int distance) throws InterruptedException {
        getAvg();
        motorFR.setPower(-speed);
        motorFL.setPower(-speed);
        motorBR.setPower(-speed);
        motorBL.setPower(-speed);
        while (avg < distance) {
            getAvg();
            showData();
            waitOneFullHardwareCycle();
        }
        reset();
    }

    public void dropClimbers() throws InterruptedException {
        climber.setPosition(0);
            Thread.sleep(1500);
        climber.setPosition(1);
    }

    public void reset() throws InterruptedException {
        motorFR.setPower(0);
        motorFL.setPower(0);
        motorBR.setPower(0);
        motorBL.setPower(0);
        manipulator.setPower(0);
        avg = 0;
        motorBL.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorBR.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        Thread.sleep(1000);
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
