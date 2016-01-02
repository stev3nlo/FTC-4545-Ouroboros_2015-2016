package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.hardware.configuration.ServoConfiguration;
/**
 * Created by sopa on 12/30/15.
 */
public class Test extends OpMode{
    public DcMotor motorFR;
    public DcMotor motorFL;
    public DcMotor motorBR;
    public DcMotor motorBL;
    @Override
    public void init() {
        motorFL = hardwareMap.dcMotor.get("motorFL");
        motorFR = hardwareMap.dcMotor.get("motorFR");
        motorBL = hardwareMap.dcMotor.get("motorBL");
        motorBR = hardwareMap.dcMotor.get("motorBR");
    }

    @Override
    public void loop() {
        //tests wheels
        if(Math.abs(gamepad1.right_stick_y) > .05){
            motorFR.setPower(gamepad1.right_stick_y * -1);
            motorBR.setPower(gamepad1.right_stick_y * -1);
        }
        else{
            motorFR.setPower(0);
            motorBR.setPower(0);
        }
        if(Math.abs(gamepad1.left_stick_y) > .05){
            motorFL.setPower(gamepad1.left_stick_y);
            motorBL.setPower(gamepad1.left_stick_y);
        }
        else{
            motorFL.setPower(0);
            motorBL.setPower(0);
        }

    }

}
