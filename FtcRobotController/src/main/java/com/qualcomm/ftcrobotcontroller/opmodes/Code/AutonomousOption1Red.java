package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by 4545 on 10/12/15.
 */
public class AutonomousOption1Red extends LinearOpMode{

    public DcMotor motorFR;
    public DcMotor motorFL;
    public DcMotor motorBR;
    public DcMotor motorBL;
    public DcMotor motorLift;
    public Servo boxTilt;
    public Servo switchL;
    public Servo switchR;
    public DcMotor motorHangR;
    public DcMotor motorHangL;
    public DcMotor motorSpinner;
    private double speed = .3;

    public void goStraight(){
        motorFR.setPower(speed * -1);
        motorBR.setPower(speed * -1);
        motorFL.setPower(speed);
        motorBL.setPower(speed);
    }

    public void turnRight(){
        motorFR.setPower(speed);
        motorBR.setPower(speed);
        motorFL.setPower(speed);
        motorBL.setPower(speed);
    }

    public void turnLeft(){
        motorFR.setPower(speed);
        motorBR.setPower(speed);
        motorFL.setPower(speed * -1);
        motorBL.setPower(speed * -1);
    }

    public void backward(){
        motorFR.setPower(speed);
        motorBR.setPower(speed);
        motorFL.setPower(speed * -1);
        motorBL.setPower(speed * -1);
    }

    public void motorSpinner(){
        motorSpinner.setPower(-1);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        //hardware mapping
        motorFL = hardwareMap.dcMotor.get("motorFR");
        motorFR = hardwareMap.dcMotor.get("motorFL");
        motorBL = hardwareMap.dcMotor.get("motorBR");
        motorBR = hardwareMap.dcMotor.get("motorBL");
        motorSpinner = hardwareMap.dcMotor.get("motorSpinner");
        motorLift = hardwareMap.dcMotor.get("motorLift");
        boxTilt = hardwareMap.servo.get("boxTilt");
        switchL = hardwareMap.servo.get("switchL");
        switchR = hardwareMap.servo.get("switchR");
    }
}