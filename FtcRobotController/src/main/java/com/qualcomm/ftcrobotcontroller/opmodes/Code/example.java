package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Steven on 11/10/2015.
 */
public class example extends LinearOpMode {
    public DcMotor motorFL;
    @Override
    public void runOpMode() throws InterruptedException {
        motorFL = hardwareMap.dcMotor.get("motorFL");
        try {
            waitForStart();
        } catch (InterruptedException e) {
            telemetry.addData("Error: ", e);
        }
































































    }
}
