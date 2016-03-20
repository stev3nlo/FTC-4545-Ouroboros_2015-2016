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
    int FRV = 0;
    int FLV = 0;
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
        DcMotor FR = hardwareMap.dcMotor.get("motor_1");
        DcMotor FL = hardwareMap.dcMotor.get("motor_2");
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
        average = 0;
        FL.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        FR.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            RobotLog.e(e.getMessage());
        }
        FL.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        FR.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    }

    public void getAvg() {
        FRV = Math.abs(FR.getCurrentPosition());
        FLV = Math.abs(FL.getCurrentPosition());
        average = (FRV + FLV) / 2;
    }
}

