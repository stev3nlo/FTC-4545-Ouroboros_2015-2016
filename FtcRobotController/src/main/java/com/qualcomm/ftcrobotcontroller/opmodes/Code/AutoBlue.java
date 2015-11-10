package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Steven on 11/9/2015.
 */
public class AutoBlue extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor FL;
        DcMotor FR;
        DcMotor BL;
        DcMotor BR;
        double start;
        double change;
        FL = hardwareMap.dcMotor.get("motorFR");
        FR = hardwareMap.dcMotor.get("motorFL");
        BL = hardwareMap.dcMotor.get("motorBR");
        BR = hardwareMap.dcMotor.get("motorBL");
        try {
            waitForStart();
        } catch (InterruptedException e) {
            start = (FL.getCurrentPosition() + FR.getCurrentPosition() + BL.getCurrentPosition() + BR.getCurrentPosition()) / 4;
            change = 0;
            while (change < 10) {
                FL.setPower(-1);
                FR.setPower(1);
                BL.setPower(-1);
                BR.setPower(1);
                change = ((FL.getCurrentPosition() + FR.getCurrentPosition() + BL.getCurrentPosition() + BR.getCurrentPosition()) / 4) - start;
                telemetry.addData("Total Change: ", change);
            }
            FL.setPower(0);
            FR.setPower(0);
            BL.setPower(0);
            BR.setPower(0);
        }
    }
}
