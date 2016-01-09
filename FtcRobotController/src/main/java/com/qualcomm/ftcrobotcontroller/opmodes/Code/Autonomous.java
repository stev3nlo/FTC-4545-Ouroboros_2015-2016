package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.RobotLog;

/**
 * Created by Steven on 1/9/2016.
 */
public class Autonomous extends LinearOpMode {
    DcMotor motorFR;
    DcMotor motorFL;
    DcMotor motorBR;
    DcMotor motorBL;
    DcMotor manipulator;
    Servo climber;
    int BR = 0;
    int BL = 0;
    int avg = 0;

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
        motorBL = hardwareMap.dcMotor.get("motorBL");
        manipulator = hardwareMap.dcMotor.get("manipulator");
        climber = hardwareMap.servo.get("climber");
        climber.setPosition(1);

        try {
            waitForStart();
        } catch (InterruptedException e) {
            RobotLog.e(e.getMessage());
        }

        forwardWithMani(1, 20000);
//        turnRight(.5, 2000);
//        moveForward(1, 2000);
//        turnLeft(.5, 2000);
//        moveForward(1, 2000);
//        dropClimbers();
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
        }
        reset();
    }

    public void dropClimbers() {
        climber.setPosition(0);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        climber.setPosition(.5);
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
