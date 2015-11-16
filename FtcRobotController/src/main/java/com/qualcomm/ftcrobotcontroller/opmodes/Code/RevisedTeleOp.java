package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.hardware.configuration.ServoConfiguration;

import static java.lang.Math.abs;


public class RevisedTeleOp extends OpMode{
//declares hardware
    public DcMotor motorFR;
    public DcMotor motorFL;
    public DcMotor motorBR;
    public DcMotor motorBL;
    public DcMotor motorHangR;
    public DcMotor motorHangL;
    public DcMotor motorSpinner;
    public DcMotor motorLift;
    public Servo boxTilt;
    public Servo switchL;
    public Servo switchR;
//variables
    double boxTiltPosition = 0.5;
    boolean reverse = false;
    boolean halfspeed;
    final double HALFSPEED = .2;
    int curMode = 1;
    long lastTime = 0;
    final long DURATION = 1500;

    public int time = 0;
    @Override
    public void init() {
        motorFL = hardwareMap.dcMotor.get("motorFR");
        motorFR = hardwareMap.dcMotor.get("motorFL");
        motorBL = hardwareMap.dcMotor.get("motorBR");
        motorBR = hardwareMap.dcMotor.get("motorBL");
        motorHangL = hardwareMap.dcMotor.get("motorHangl");
        motorHangR = hardwareMap.dcMotor.get("motorHangR");
        motorSpinner = hardwareMap.dcMotor.get("motorSpinner");
        motorLift = hardwareMap.dcMotor.get("motorLift");
        boxTilt = hardwareMap.servo.get("boxTilt");
        switchL = hardwareMap.servo.get("switchL");
        switchR = hardwareMap.servo.get("switchR");
        halfspeed = false;
        lastTime = System.currentTimeMillis();
        boxTilt.setPosition(boxTiltPosition);
    }
    public int gamePadScale(){

        return 0;
    }

    public void halfspeed(){
        // halfspeed wheels
        if(abs(gamepad1.right_stick_y) > .05) {
            motorFR.setPower(gamepad1.right_stick_y * HALFSPEED * -1);
            motorBR.setPower(gamepad1.right_stick_y * HALFSPEED * -1);
        }
        else{
            motorFR.setPower(0);
            motorFR.setPower(0);
        }
        if((abs(gamepad1.left_stick_y) > .05)) {
            motorFL.setPower(gamepad1.left_stick_y * HALFSPEED);
            motorBL.setPower(gamepad1.left_stick_y * HALFSPEED);
        }
        else{
            motorFL.setPower(0);
            motorBL.setPower(0);
        }
        // halfspeed sevro: will run at full speed at all times
        if (gamepad1.left_bumper) {
            switchL.setPosition(1);
        }
        else {
            switchL.setPosition(.5);
        }
        if (gamepad1.left_trigger > .05) {
            switchL.setPosition(0);
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
        if (gamepad1.right_trigger > .05) {
            switchR.setPosition(1);
        }
        else {
            switchR.setPosition(0);
        }

        // halfspeed lift
        if (gamepad2.right_stick_y > .05 || gamepad2.left_stick_y < .05) { //sets the motors that move the hang pulley
            motorHangL.setPower(gamepad2.right_stick_y * HALFSPEED * -1);
            motorHangR.setPower(gamepad2.right_stick_y * HALFSPEED *  -1);
        }
        else {
            motorHangL.setPower(0);
            motorHangR.setPower(0);
        }
        //halfspeed maniuplator
        if (((gamepad2.left_trigger > 0.5) && (gamepad2.right_trigger > 0.5) || (gamepad2.left_trigger == 0) && (gamepad2.right_trigger == 0))){
            motorSpinner.setPower(0); //If both triggers are pushed, set motor power to 0
        }
        else if (gamepad2.right_trigger > 0.5) {
            motorSpinner.setPower(HALFSPEED); //Spinner motor
        }
        else if (gamepad2.left_trigger > 0.5) {
            motorSpinner.setPower(HALFSPEED * -1); //Reverse spinner motor
        }

        //


    }

    public void regular() {
        if(abs(gamepad1.right_stick_y) > .05) {
            motorFR.setPower(gamepad1.right_stick_y * -1);
            motorBR.setPower(gamepad1.right_stick_y * -1);
        }
        else{
            motorFR.setPower(0);
            motorFR.setPower(0);
        }
        if((abs(gamepad1.left_stick_y) > .05)) {
            motorFL.setPower(gamepad1.left_stick_y);
            motorBL.setPower(gamepad1.left_stick_y);
        }
        else{
            motorFL.setPower(0);
            motorBL.setPower(0);
        }
    }


    public void loop() {

    }
}


