package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Steven on 11/9/2015.
 */
public class AutoBlue extends LinearOpMode {
    DcMotor motorFL;
    DcMotor motorFR;
    DcMotor motorBL;
    DcMotor motorBR;
    Servo climbers;
    Servo switchL;
    Servo switchR;
    int FL;
    int FR;
    int BL;
    int BR;
    int start;
    int curr;
    int change;
    @Override
    public void runOpMode() throws InterruptedException {
        motorFL = hardwareMap.dcMotor.get("motorFR");
        motorFR = hardwareMap.dcMotor.get("motorFL");
        motorBL = hardwareMap.dcMotor.get("motorBR");
        motorBR = hardwareMap.dcMotor.get("motorBL");
        climbers = hardwareMap.servo.get("climber");
        switchL = hardwareMap.servo.get("switchL");
        switchR = hardwareMap.servo.get("switchR");
        climbers.setPosition(1);
        switchL.setPosition(.7);
        switchR.setPosition(.5);


//        motorFL.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
//        motorBL.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
//        motorFR.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
//        motorBR.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        try {
            waitForStart();
        } catch (InterruptedException e){
            telemetry.addData("Error", e);
        }
        moveForward(1, 4500);
        climbers();
    }

    public void reset() {
        motorFL.setPower(0);
        motorFR.setPower(0);
        motorBL.setPower(0);
        motorBR.setPower(0);
        updateCurr();
        start = curr;
        curr = 0;
        change = 0;
        telemetry.clearData();
        telemetry.addData("Data Reset; Change: ", change);
    }

    public void updateCurr() {
        FL = Math.abs(motorFL.getCurrentPosition());
        FR = Math.abs(motorFR.getCurrentPosition());
        BL = Math.abs(motorBL.getCurrentPosition());
        BR = Math.abs(motorBR.getCurrentPosition());
        curr = (FL + FR + BL + BR) / 4;
        telemetry.addData("curr", curr);
    }

    public void updateChange() {
        updateCurr();
        change = curr - start;
        telemetry.addData("change", change);
    }

    public void moveForward(double speed, int goal) {
        reset();
        motorFL.setPower(-speed);
        motorFR.setPower(speed);
        motorBL.setPower(-speed);
        motorBR.setPower(speed);
        while (change < goal) {
            telemetry.addData("Encoder FR", FL);
            telemetry.addData("Encoder FL", FR);
            telemetry.addData("Encoder BR", BL);
            telemetry.addData("Encoder BL", BR);
            telemetry.addData("Change", change);
            updateChange();
        }
        reset();
        try {
            sleep(100);
        } catch (InterruptedException e){
            telemetry.addData("Error", e);
        }
    }

    public void moveBackwards(double speed, int goal) {
        reset();
        motorFL.setPower(speed);
        motorFR.setPower(-speed);
        motorBL.setPower(speed);
        motorBR.setPower(-speed);
        while (change < goal) {
            telemetry.addData("Change", change);
            updateChange();
        }
        reset();
        try {
            sleep(500);
        } catch (InterruptedException e){
            telemetry.addData("Error", e);
        }
    }

    public void turn(String dir, double speed, int goal) {
        reset();
        motorFL.setPower(-speed);
        motorFR.setPower(-speed);
        motorBL.setPower(-speed);
        motorBR.setPower(-speed);
        if (dir.equals("right")) {
            while (change < goal) {
                telemetry.addData("Change", change);
                updateChange();
            }
            reset();
            try {
                sleep(500);
            } catch (InterruptedException e){
                telemetry.addData("Error", e);
            }
        }
        motorFL.setPower(speed);
        motorFR.setPower(speed);
        motorBL.setPower(speed);
        motorBR.setPower(speed);
        if (dir.equals("left")) {
            while (change < goal) {
                telemetry.addData("Change", change);
                updateChange();
            }
            reset();
            try {
                sleep(500);
            } catch (InterruptedException e){
                telemetry.addData("Error", e);
            }
        }
        else {
            telemetry.addData("Error: ", "Invalid Direction");
        }
        reset();
        try {
            sleep(500);
        } catch (InterruptedException e){
            telemetry.addData("Error", e);
        }
    }

    public void climbers() {
        climbers.setPosition(1);
    }
}
