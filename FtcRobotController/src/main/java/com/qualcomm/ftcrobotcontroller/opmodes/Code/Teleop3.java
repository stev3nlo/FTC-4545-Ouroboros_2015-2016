package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.hardware.configuration.ServoConfiguration;


/**
 * Created by sopa on 12/14/15.
 */
public class Teleop3 extends OpMode{
    public DcMotor motorFR;
    public DcMotor motorFL;
    public DcMotor motorBR;
    public DcMotor motorBL;
    public DcMotor motorHangL;
    public DcMotor motorHangR;
    public DcMotor motorSpinner;
    boolean halfspeed;
    final double HALFSPEED = .3;
    final double FULLSPEED = 1.0;
    public Servo switchL;
    public Servo switchR;
    public Servo climber;
    @Override
    public void init() {
        motorFL = hardwareMap.dcMotor.get("motorFL");
        motorFR = hardwareMap.dcMotor.get("motorFR");
        motorBL = hardwareMap.dcMotor.get("motorBL");
        motorBR = hardwareMap.dcMotor.get("motorBR");
        switchL = hardwareMap.servo.get("switchL");
        switchR = hardwareMap.servo.get("switchR");
        motorHangL = hardwareMap.dcMotor.get("motorHangl");
        motorHangR = hardwareMap.dcMotor.get("motorHangR");
        motorSpinner = hardwareMap.dcMotor.get("motorSpinner");
        climber = hardwareMap.servo.get("climber");
        halfspeed = false;
    }

    @Override
    public void loop() {

    }
    public void Normal(){
        //base
        if(Math.abs(gamepad1.right_stick_y) > .1){
            motorFR.setPower(gamepad1.right_stick_y * -1);
            motorBR.setPower(gamepad1.right_stick_y * -1);
        }
        else {
            motorFR.setPower(0);
            motorBR.setPower(0);
        }
        //servo for flipping switches
        if (gamepad1.left_bumper) {
            switchL.setPosition(1);
        }
        else {
            switchL.setPosition(.5);
        }
        if (gamepad1.right_bumper) {
            switchR.setPosition(0);
        }
        else {
            switchR.setPosition(.5);
        }
        if(Math.abs(gamepad1.left_stick_y) > .1){
            motorFL.setPower(gamepad1.left_stick_y);
            motorBL.setPower(gamepad1.left_stick_y);
        }
        else {
            motorFL.setPower(0);
            motorBL.setPower(0);
        }
        //manipulator
        if(Math.abs(gamepad2.left_stick_y) > .1){
            motorSpinner.setPower(gamepad2.left_stick_y);
        }
        else {
            motorSpinner.setPower(0);
        }
        //lift
        if(Math.abs(gamepad2.right_stick_y) > .1){
            motorHangL.setPower(gamepad2.right_stick_y);
            motorHangR.setPower(gamepad2.right_stick_y * -1);
        }
        else {
            motorHangL.setPower(0);
            motorHangR.setPower(0);
        }
        //servo climber switch
        if(gamepad2.x){
            climber.setPosition(1);
        }
        else
        {
            climber.setPosition(0);
        }
    }
}

