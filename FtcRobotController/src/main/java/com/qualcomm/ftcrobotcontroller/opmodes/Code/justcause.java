package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by natalieroot on 3/28/16.
 */
public class justcause extends OpMode{
    public DcMotor motorR;
    public DcMotor motorL;

    public void init() {
        motorR = hardwareMap.dcMotor.get("motorR");
        motorL = hardwareMap.dcMotor.get("motorL");
    }

    @Override
    public void loop() {
        if(Math.abs(gamepad1.right_stick_y) > .05){
            motorR.setPower(gamepad1.right_stick_y * -1);
        }
        else {
            motorR.setPower(0);
        }
        if(Math.abs(gamepad1.left_stick_y) > .05){
            motorL.setPower(gamepad1.left_stick_y);
        }
        else {
            motorL.setPower(0);
        }
    }

}
