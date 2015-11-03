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
    double changeFL;
    double changeFR;
    double changeBL;
    double changeBR;
    @Override
    public void runOpMode() throws InterruptedException {
        motorFL = hardwareMap.dcMotor.get("motorFR");
        motorFR = hardwareMap.dcMotor.get("motorFL");
        motorBL = hardwareMap.dcMotor.get("motorBR");
        motorBR = hardwareMap.dcMotor.get("motorBL");


        waitForStart();


    }

    public void moveForward(double speed, double distance) {
        reset();
        
    }

    public void reset() {
        eFL = 0;
        eFR = 0;
        eBL = 0;
        eBR = 0;
        changeFL = 0;
        changeFR = 0;
        changeBR = 0;
        changeBL = 0;
    }
}
