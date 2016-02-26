package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Steven on 12/2/2015.
 */
public class judgingMacros extends OpMode {

    DcMotor motorHangL;
    DcMotor motorHangR;
    DcMotor motorSpinner;
    public Servo ramp;
    public Servo drop;
    public Servo claw;
    public Servo boxBelt;
    @Override
    public void init() {
        motorHangL = hardwareMap.dcMotor.get("motorHangL");
        motorHangR = hardwareMap.dcMotor.get("motorHangR");
        motorSpinner = hardwareMap.dcMotor.get("manipulator");
        ramp = hardwareMap.servo.get("ramp");
        drop = hardwareMap.servo.get("drop");
        claw = hardwareMap.servo.get("claw");
        motorHangL.setPower(0);
        motorHangR.setPower(0);
        motorSpinner.setPower(0);
        boxBelt.setPosition(.5);
        ramp.setPosition(0);
        drop.setPosition(0);
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            motorSpinner.setPower(1);
            try {
                wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            motorSpinner.setPower(-1);
            try {
                wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            motorSpinner.setPower(0);
        }
        if (gamepad1.b) {
            motorHangL.setPower(1);
            motorHangR.setPower(-1);
            try {
                wait(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            motorHangL.setPower(0);
            motorHangR.setPower(0);
            try {
                wait(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            motorHangL.setPower(-1);
            motorHangR.setPower(1);
            try {
                wait(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            motorHangL.setPower(0);
            motorHangR.setPower(0);
        }
    }
}
