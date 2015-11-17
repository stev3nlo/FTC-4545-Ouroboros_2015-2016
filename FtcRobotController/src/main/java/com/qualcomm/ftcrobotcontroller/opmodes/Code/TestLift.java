package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by sopa on 11/16/15.
 */
public class TestLift extends OpMode {
    DcMotor motorHangR;
    final double halfSpeed = .3;

    @Override
    public void init() {
        motorHangR = hardwareMap.dcMotor.get("motorHangR");

    }

    @Override
    public void loop() {
        if (gamepad1.right_stick_y > .05){
            motorHangR.setPower(gamepad1.right_stick_y * halfSpeed * -1);
        }
        else{
            motorHangR.setPower(0);
        }

    }
}
