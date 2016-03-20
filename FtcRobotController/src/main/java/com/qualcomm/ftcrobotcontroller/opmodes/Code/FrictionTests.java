package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.RobotLog;

/**
 * Created by sonesh patel on 3/19/16.
 */
public class FrictionTests extends LinearOpMode {
    public DcMotor FR;
    public DcMotor FL;
    public DcMotor BR;
    public DcMotor BL;
    int BRV = 0;
    int BLV = 0;
    int average = 0;
    double cOF = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        try {
            waitOneFullHardwareCycle();
        }
        catch (InterruptedException e) {
            RobotLog.e(e.getMessage());
        }
        DcMotor FR = hardwareMap.dcMotor.get("FR");
        DcMotor FL = hardwareMap.dcMotor.get("FR");
        DcMotor BR = hardwareMap.dcMotor.get("FR");
        DcMotor BL = hardwareMap.dcMotor.get("FR");
        try {
            waitForStart();
        } catch (InterruptedException e) {
            RobotLog.e(e.getMessage());
        }
        //
        moveForward(.5, 500);
        turnRight(.5, 500);
    }

    public void moveForward(double speed, int distance ) {
        getAvg();
        FR.setPower(speed);
        FL.setPower(-speed);
        BR.setPower(speed);
        BL.setPower(-speed);
        while (average < distance) {
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

    public void moveBackWard(double speed, int distance) {
        moveForward(-speed, distance);
    }

    public void turnRight(double speed, int distance) {
        getAvg();
        FR.setPower(speed);
        FL.setPower(speed);
        BR.setPower(speed);
        BL.setPower(speed);
        while (average < distance) {
            getAvg();
            showData();
            try {
                waitOneFullHardwareCycle();
            }
            catch (InterruptedException e) {
                RobotLog.e(e.getMessage());
            }
        }
        reset();
    }

    public void turnLeft(double speed, int distance) {
        turnRight(-speed, distance);
    }

    private void showData() {
        telemetry.addData("Encoder Value", average);
    }

    public void reset(){
        FR.setPower(0);
        FL.setPower(0);
        BR.setPower(0);
        BL.setPower(0);
        average = 0;
        BL.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        BR.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            RobotLog.e(e.getMessage());
        }
        BL.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        BR.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    }

    public void getAvg() {
        BRV = Math.abs(BR.getCurrentPosition());
        BLV = Math.abs(BL.getCurrentPosition());
        average = (BRV + BLV) / 2;
    }
}

