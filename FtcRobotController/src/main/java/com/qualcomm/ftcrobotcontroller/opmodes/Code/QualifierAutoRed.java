package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.RobotLog;

/**
 * Created by Vandegrift InvenTeam on 1/7/2016.
 */
public class QualifierAutoRed extends LinearOpMode {
    DcMotor motorFR;
    DcMotor motorFL;
    DcMotor motorBR;
    DcMotor motorBL;
    DcMotor manipulator;
    Servo climber;
    int BR = 0;
    int BL = 0;
    int curr = 0;
    int avg = 0;
    int start = 0;

    @Override
    public void runOpMode() {
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

        moveForward(1, 5000);
        moveBackwards(1, 5000);
        forwardWithMani(1, 5000);
        dropClimbers();
        turnLeft(0.5, 3000);
        turnRight(0.5, 3000);
    }

    public void moveForward(double speed, int distance) {
        update();
        start = curr;
        curr = 0;
        while ((curr) < distance) {
            showData();
            motorFR.setPower(-speed);
            motorFL.setPower(speed);
            motorBR.setPower(-speed);
            motorBL.setPower(speed);
            update();
        }
        reset();
    }

    public void moveBackwards(double speed, int distance) {
        update();
        start = curr;
        curr = 0;
        while ((curr) < distance) {
            showData();
            motorFR.setPower(speed);
            motorFL.setPower(-speed);
            motorBR.setPower(speed);
            motorBL.setPower(-speed);
            update();
        }
        reset();
    }

    public void forwardWithMani(double speed, int distance) {
        update();
        start = curr;
        curr = 0;
        while ((curr) < distance) {
            showData();
            motorFR.setPower(-speed);
            motorFL.setPower(speed);
            motorBR.setPower(-speed);
            motorBL.setPower(speed);
            manipulator.setPower(-1);
            update();
        }
        reset();
    }

    public void turnRight(double speed, int amount) {
        update();
        start = curr;
        curr = 0;
        while ((curr) < amount) {
            showData();
            motorFR.setPower(speed);
            motorFL.setPower(speed);
            motorBR.setPower(speed);
            motorBL.setPower(speed);
            update();
        }
        reset();
    }

    public void turnLeft(double speed, int amount) {
        update();
        start = curr;
        curr = 0;
        while ((curr) < amount) {
            showData();
            motorFR.setPower(-speed);
            motorFL.setPower(-speed);
            motorBR.setPower(-speed);
            motorBL.setPower(-speed);
            update();
        }
        reset();
    }

    public void dropClimbers() {
        climber.setPosition(0);
        //wait(500);
        climber.setPosition(.5);
    }

    public void reset() {
        motorFR.setPower(0);
        motorFL.setPower(0);
        motorBR.setPower(0);
        motorBL.setPower(0);
        start = curr;
        update();
        manipulator.setPower(0);
    }

    public void update() {
        BR = Math.abs(motorBR.getCurrentPosition());
        BL = Math.abs(motorBL.getCurrentPosition());
        avg = (BR + BL) / 2;
        curr = avg - start;
    }

    public void showData() {
        telemetry.addData("current position", curr);
    }
}