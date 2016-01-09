package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

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
    int eBL;
    int eBR;
    int eAvg;

    @Override
    public void runOpMode() throws InterruptedException {
        waitOneFullHardwareCycle();
        motorFR = hardwareMap.dcMotor.get("motorFR");
        motorFL = hardwareMap.dcMotor.get("motorFL");
        motorBR = hardwareMap.dcMotor.get("motorBR");
        motorBL = hardwareMap.dcMotor.get("motorBL");
        manipulator = hardwareMap.dcMotor.get("manipulator");
        climber = hardwareMap.servo.get("climber");
        climber.setPosition(1);

        waitForStart();

        moveForward(1, 5000);
        moveBackwards(1, 5000);
        forwardWithMani(1, 5000);
        dropClimbers();
        turnLeft(0.5, 3000);
        turnRight(0.5, 3000);
    }

    public void reset() {
        motorFR.setPower(0);
        motorFL.setPower(0);
        motorBR.setPower(0);
        motorBL.setPower(0);
        eBL = Math.abs(motorBL.getCurrentPosition());
        eBR = Math.abs(motorBR.getCurrentPosition());
        update();
        manipulator.setPower(0);
    }

    public void update() {
        eBR = Math.abs(motorBR.getCurrentPosition());
        eBL = Math.abs(motorBL.getCurrentPosition());
        eAvg = (Math.abs(eBR) + Math.abs(eBL)) / 2;
    }
    public void moveForward(double speed, int distance) {
        int start = eAvg;
        while (eAvg < start + distance) {
            showData();
            motorFR.setPower(speed);
            motorFL.setPower(-speed);
            motorBR.setPower(speed);
            motorBL.setPower(-speed);
            update();
        }
        reset();
    }

    public void moveBackwards(double speed, int distance) {
        int start = eAvg;
        while (eAvg < start + distance) {
            showData();
            motorFR.setPower(-speed);
            motorFL.setPower(speed);
            motorBR.setPower(-speed);
            motorBL.setPower(speed);
            update();
        }
        reset();
    }

    public void forwardWithMani(double speed, int distance) {
        int start = eAvg;
        while (eAvg < start + distance) {
            showData();
            motorFR.setPower(speed);
            motorFL.setPower(-speed);
            motorBR.setPower(speed);
            motorBL.setPower(-speed);
            manipulator.setPower(-1);
            update();
        }
        reset();
    }

    public void dropClimbers() throws InterruptedException {
        climber.setPosition(0);
        wait(500);
        climber.setPosition(.5);
    }

    public void turnRight(double speed, int amount) {
        int start = eAvg;
        while (eAvg < start + amount) {
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
        int start = eAvg;
        while (eAvg < start + amount) {
            showData();
            motorFR.setPower(-speed);
            motorFL.setPower(-speed);
            motorBR.setPower(-speed);
            motorBL.setPower(-speed);
            update();
        }
        reset();
    }

    public void showData() {
        telemetry.addData("eBR", eBR);
        telemetry.addData("eBL", eBL);
        telemetry.addData("eAvg", eAvg);
    }
}
