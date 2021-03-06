package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import static java.lang.Math.abs;

/**
 * Created by sopa on 11/16/15.
 */
public class TestLift extends OpMode {
    DcMotor motorHangL;
    final double halfSpeed = .3;

    @Override
    public void init() {
        motorHangL = hardwareMap.dcMotor.get("motorHangL");

    }

    @Override
    public void loop() {
        if (abs(gamepad1.left_stick_y) > .05){
            motorHangL.setPower(gamepad1.left_stick_y);
        }
        else{
            motorHangL.setPower(0);
        }

    }
}
