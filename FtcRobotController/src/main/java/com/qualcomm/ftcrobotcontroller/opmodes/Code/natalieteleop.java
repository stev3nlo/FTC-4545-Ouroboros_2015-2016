package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by natalieroot on 4/4/16.
 */
public class natalieteleop extends OpMode{
    public DcMotor motorFL;
    public DcMotor motorFR;
    public DcMotor motorBR;
    public DcMotor motorBL;
    public DcMotor manipulator;
    public DcMotor liftL;
    public DcMotor liftR;
    public Servo attachL;
    public Servo attachR;
    public Servo climber;
    public Servo switchL;
    public Servo switchR;
    public Servo turntableL;
    public Servo turntableR;
    


    @Override
    public void init() {
        motorFL = hardwareMap.dcMotor.get("motorFL");
        motorFR = hardwareMap.dcMotor.get("motorFR");
        motorBL = hardwareMap.dcMotor.get("motorBL");
        motorBR = hardwareMap.dcMotor.get("motorBR");
        liftL = hardwareMap.dcMotor.get("liftL");
        liftR = hardwareMap.dcMotor.get("liftR");
        manipulator = hardwareMap.dcMotor.get("mainpulator");
        attachL = hardwareMap.servo.get("attachL");
        attachR = hardwareMap.servo.get("attachR");
        climber = hardwareMap.servo.get("climber");
        switchL = hardwareMap.servo.get("switchL");
        switchR = hardwareMap.servo.get("switchR");
        turntableL = hardwareMap.servo.get("turntableL");
        turntableR = hardwareMap.servo.get("turntableR");
        switchL.setPosition(.5);
        switchR.setPosition(1);
        climber.setPosition(1);


    }

    @Override
    public void loop() {
        // drive train move
        if (Math.abs(gamepad1.left_stick_y) > .05) {
            motorFL.setPower(gamepad1.left_stick_y * -1);
            motorBL.setPower(gamepad1.left_stick_y * -1);
        }
        else {
            motorFL.setPower(0);
            motorBL.setPower(0);
        }
        if (Math.abs(gamepad1.right_stick_y) > .05){
            motorFR.setPower(gamepad1.right_stick_y);
            motorBR.setPower(gamepad1.right_stick_y);
        }
        else {
            motorFR.setPower(0);
            motorBR.setPower(0);
        }
        // manipulator
        if (Math.abs(gamepad2.left_stick_y) > .05) {
            manipulator.setPower(gamepad2.left_stick_y);
        }
        else {
            manipulator.setPower(0);
        }
        // lift extension
        if(Math.abs(gamepad2.right_stick_y) > .05) {
            liftL.setPower(gamepad2.right_stick_y);
            liftR.setPower(gamepad2.right_stick_y);
        }
        else {
            liftL.setPower(0);
            liftR.setPower(0);
        }
        // attach hooks
        if (Math.abs(gamepad1.right_trigger) > .05) {
            attachL.setPosition(.95);
            attachR.setPosition(.25);
        }
        else if (Math.abs(gamepad1.left_trigger) > .05) {
            attachL.setPosition(.35);
            attachR.setPosition(.85);
        }
        // climber
        if (gamepad1.x) {
            climber.setPosition(0);
        }
        else {
            climber.setPosition(1);
        }
        // climber switches
        if (gamepad1.left_bumper) {
            switchL.setPosition(1);
        }
        else {
            switchL.setPosition(.5);
        }
        if (gamepad1.right_bumper) {
            switchR.setPosition(.5);
        }
        else {
            switchR.setPosition(1);
        }
        // turntable
        if (Math.abs(gamepad2.right_trigger) > .05) {
            turntableR.setPosition(x);
            turntableL.setPosition(x);
        }
        else {
            turntableR.setPosition(y);
            turntableL.setPosition(y);
        }
        // halfspeed macro




    }
}
