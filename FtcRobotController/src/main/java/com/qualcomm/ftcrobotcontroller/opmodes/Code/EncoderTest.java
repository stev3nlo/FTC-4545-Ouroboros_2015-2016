package com.qualcomm.ftcrobotcontroller.opmodes.Code;

//import com.qualcomm.robotcore.eventloop.EventLoopManager;
//import com.qualcomm.robotcore.eventloop.*;
//import com.qualcomm.robotcore.eventloop.opmode.*;
//import com.qualcomm.ftccommon.*;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.util.*;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.*;
//import com.qualcomm.robotcore.util.ElapsedTime;
//import com.qualcomm.ftccommon.FtcEventLoopHandler;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.hardware.*;
//import com.qualcomm.robotcore.hardware.configuration.ServoConfiguration;

import static java.lang.Math.abs;
/**
 * Created by Vandegrift InvenTeam on 10/22/2015.
 */
public class EncoderTest extends OpMode{
    DcMotor motorFL;
    DcMotor motorFR;
    DcMotor motorBL;
    DcMotor motorBR;
    double eFL;
    double eFR;
    double eBL;
    double eBR;
    double changeFL;
    double changeFR;
    double changeBL;
    double changeBR;

    boolean halfspeed;
    final double HALFSPEED = .3;
    final double FULLSPEED = 1.0;

    long lastTime = 0;
    final long DURATION = 1500;

    @Override
    public void init() {
        eFL = 0;
        eFR = 0;
        eBL = 0;
        eBR = 0;
        changeFL = 0;
        changeFR = 0;
        changeBR = 0;
        changeBL = 0;
        motorFL = hardwareMap.dcMotor.get("motorFL");
        motorFR = hardwareMap.dcMotor.get("motorFR");
        motorBL = hardwareMap.dcMotor.get("motorBL");
        motorBR = hardwareMap.dcMotor.get("motorBR");
        halfspeed = false;
        lastTime = System.currentTimeMillis();

    }

    @Override
    public void loop() {
        if(gamepad1.a) //Resets encoders
        {
            eFL = motorFL.getCurrentPosition();
            eBL = motorBL.getCurrentPosition();
            eBR = motorBR.getCurrentPosition();
            eFR = motorFR.getCurrentPosition();
        }
        if (gamepad1.left_trigger == 1) {
            long currentTime = System.currentTimeMillis();
            // are we waiting?
            if (currentTime > lastTime + DURATION) {
                halfspeed = !halfspeed;
                lastTime = currentTime;
            }
        }

        double speed = (halfspeed) ? HALFSPEED : FULLSPEED;
        boolean right_y = abs(gamepad1.right_stick_y) > .05;
        boolean left_y = abs(gamepad1.left_stick_y) > .05;

        if(right_y){
            motorFR.setPower(gamepad1.right_stick_y * -1 * speed);
            motorBR.setPower(gamepad1.right_stick_y * speed);
        } else {
            motorFR.setPower(0);
            motorBR.setPower(0);
        }

        if(left_y) {
            motorFL.setPower(gamepad1.left_stick_y * speed);
            motorBL.setPower(gamepad1.left_stick_y * -1 * speed);
        } else {
            motorFL.setPower(0);
            motorBL.setPower(0);
        }
        changeFL = motorFL.getCurrentPosition() - eFL;
        changeFR = motorFR.getCurrentPosition() - eFR;
        changeBR = motorBR.getCurrentPosition() - eBR;
        changeBL = motorBL.getCurrentPosition() - eBL;
        telemetry.addData("FL Encoder", eFL);
        telemetry.addData("FR Encoder", eFR);
        telemetry.addData("BL Encoder", eBL);
        telemetry.addData("BR Encoder", eBR);
    }
}