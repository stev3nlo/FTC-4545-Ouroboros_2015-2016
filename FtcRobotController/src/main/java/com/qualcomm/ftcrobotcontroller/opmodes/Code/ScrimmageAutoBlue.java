package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Steven on 11/2/2015.
 */
public class ScrimmageAutoBlue extends LinearOpMode {
    public DcMotor motorFR;
    public DcMotor motorFL;
    public DcMotor motorBR;
    public DcMotor motorBL;
    double eFL;
    double eFR;
    double eBL;
    double eBR;
    double eAvg;
    double changeFL;
    double changeFR;
    double changeBL;
    double changeBR;
    double avgChange;
    @Override
    public void runOpMode() throws InterruptedException {
        motorFL = hardwareMap.dcMotor.get("motorFR");
        motorFR = hardwareMap.dcMotor.get("motorFL");
        motorBL = hardwareMap.dcMotor.get("motorBR");
        motorBR = hardwareMap.dcMotor.get("motorBL");

        waitForStart();

        moveForward(1, 1000);
        reset();
    }

    public void moveForward(double speed, double goal) {
        reset();
        while (avgChange < goal) {
            motorFL.setPower(-speed);
            motorFR.setPower(speed);
            motorBL.setPower(-speed);
            motorBR.setPower(speed);
        }
    }

    public void reset() {
        eFL = motorFL.getCurrentPosition();
        eFR = motorFR.getCurrentPosition();
        eBL = motorBL.getCurrentPosition();
        eBR = motorBR.getCurrentPosition();
        eAvg = (eFL + eFR + eBL + eBR) / 4;
        changeFL = 0;
        changeFR = 0;
        changeBR = 0;
        changeBL = 0;
        avgChange = 0;
        motorFL.setPower(0);
        motorFR.setPower(0);
        motorBL.setPower(0);
        motorBR.setPower(0);
    }

    public void getChange() {
        changeFL = motorFL.getCurrentPosition() - eFL;
        changeFR = motorFR.getCurrentPosition() - eFR;
        changeBL = motorBL.getCurrentPosition() - eBL;
        changeBR = motorBR.getCurrentPosition() - eBR;
        avgChange = (changeFL + changeFR + changeBL + changeBR) / 4;
    }
}
