package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

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
    int changeFL;
    int changeFR;
    int changeBL;
    int changeBR;
    double avgChange;
    long time;
    int totalChange = 0;
    double speed = 0.2;
    @Override
    public void runOpMode() throws InterruptedException {
        motorFL = hardwareMap.dcMotor.get("motorFR");
        motorFR = hardwareMap.dcMotor.get("motorFL");
        motorBL = hardwareMap.dcMotor.get("motorBR");
        motorBR = hardwareMap.dcMotor.get("motorBL");

        motorFL.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motorBL.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motorFR.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motorBR.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        time = System.currentTimeMillis();
        telemetry.addData("Start time: ", time);
        try {
            waitForStart();
        } catch (InterruptedException e) {
            System.err.print(e.getMessage());
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

        //motorFL.setPower(-speed);
        //motorBL.setPower(-speed);
        motorFR.setPower(speed);
        motorBR.setPower(speed);

        //move forward
        int numLoops = 0;
        while (totalChange < 20) {
            //updates the change in encoder values
            changeFL = motorFL.getCurrentPosition();
            changeFR = motorFR.getCurrentPosition();
            changeBL = motorBL.getCurrentPosition();
            changeBR = motorBR.getCurrentPosition();
            avgChange = (changeFL + changeFR + changeBL + changeBR) / 4;
            totalChange += changeFR;
            numLoops++;
            try {Thread.sleep(200);}catch(Exception e) {}
        }

        motorFL.setPower(0);
        motorFR.setPower(0);
        motorBL.setPower(0);
        motorBR.setPower(0);

        telemetry.addData("changeFL", changeFL);
        telemetry.addData("changeFR", changeFR);
        telemetry.addData("changeBL", changeBL);
        telemetry.addData("changeBR", changeBR);
        telemetry.addData("Total Change", totalChange);
        telemetry.addData("Num loops", numLoops);
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
