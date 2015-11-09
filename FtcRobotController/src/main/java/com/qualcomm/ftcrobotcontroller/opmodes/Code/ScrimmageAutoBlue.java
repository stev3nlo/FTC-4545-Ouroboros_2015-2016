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
        telemetry.addData("Encoder Average init", eAvg);
        telemetry.addData("Average Chanage init", avgChange);
        motorFL = hardwareMap.dcMotor.get("motorFR");
        motorFR = hardwareMap.dcMotor.get("motorFL");
        motorBL = hardwareMap.dcMotor.get("motorBR");
        motorBR = hardwareMap.dcMotor.get("motorBL");
        try {
            waitForStart();
        } catch (InterruptedException e) {

        }

        //reset the variables
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

        //move forward
        while (avgChange < 1000) {
            motorFL.setPower(-1);
            motorFR.setPower(1);
            motorBL.setPower(-1);
            motorBR.setPower(1);
            telemetry.addData("Encoder Average", eAvg);
            telemetry.addData("Average Chanage", avgChange);
            //updates the change in encoder values
            changeFL = motorFL.getCurrentPosition() - eFL;
            changeFR = motorFR.getCurrentPosition() - eFR;
            changeBL = motorBL.getCurrentPosition() - eBL;
            changeBR = motorBR.getCurrentPosition() - eBR;
            avgChange = (changeFL + changeFR + changeBL + changeBR) / 4;
        }

        //reset the variables
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

//    public void moveForward(double speed, double goal) {
//        reset();
//        while (avgChange < goal) {
//            motorFL.setPower(-speed);
//            motorFR.setPower(speed);
//            motorBL.setPower(-speed);
//            motorBR.setPower(speed);
//            telemetry.addData("Encoder Average", eAvg);
//            telemetry.addData("Average Chanage", avgChange);
//            getChange();
//        }
//        motorFL.setPower(0);
//        motorFR.setPower(0);
//        motorBL.setPower(0);
//        motorBR.setPower(0);
//    }
//
//    public void reset() {
//        eFL = motorFL.getCurrentPosition();
//        eFR = motorFR.getCurrentPosition();
//        eBL = motorBL.getCurrentPosition();
//        eBR = motorBR.getCurrentPosition();
//        eAvg = (eFL + eFR + eBL + eBR) / 4;
//        changeFL = 0;
//        changeFR = 0;
//        changeBR = 0;
//        changeBL = 0;
//        avgChange = 0;
//        motorFL.setPower(0);
//        motorFR.setPower(0);
//        motorBL.setPower(0);
//        motorBR.setPower(0);
//    }
//
//    public void getChange() {
//        changeFL = motorFL.getCurrentPosition() - eFL;
//        changeFR = motorFR.getCurrentPosition() - eFR;
//        changeBL = motorBL.getCurrentPosition() - eBL;
//        changeBR = motorBR.getCurrentPosition() - eBR;
//        avgChange = (changeFL + changeFR + changeBL + changeBR) / 4;
//    }
}
