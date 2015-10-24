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
    DcMotor MotorFL;
    DcMotor MotorFR;
    DcMotor MotorBL;
    DcMotor MotorBR;
    double eFL;
    double eFR;
    double eBL;
    double eBR;
    double changeFL;
    double changeFR;
    double changeBL;
    double changeBR;

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
        MotorFL = hardwareMap.dcMotor.get("motorFL");
        MotorFR = hardwareMap.dcMotor.get("motorFR");
        MotorBL = hardwareMap.dcMotor.get("motorBL");
        MotorBR = hardwareMap.dcMotor.get("MotorBR");
    }

    @Override
    public void loop() {
        if(gamepad1.a) //Resets encoders
        {
            eFL = MotorFL.getCurrentPosition();
            eBL = MotorBL.getCurrentPosition();
            eBR = MotorBR.getCurrentPosition();
            eFR = MotorFR.getCurrentPosition();
        }
        if (gamepad1.left_trigger == 1) {
            if (abs(gamepad1.right_stick_y) > .05) {
                MotorFR.setPower(gamepad1.right_stick_y * -1);
                MotorBR.setPower(gamepad1.right_stick_y * -1);
            }
            if (abs(gamepad1.left_stick_y) > .05) {
                MotorFL.setPower(gamepad1.left_stick_y);
                MotorBL.setPower(gamepad1.left_stick_y);
            }
        }
        changeFL = MotorFL.getCurrentPosition() - eFL;
        changeFR = MotorFR.getCurrentPosition() - eFR;
        changeBR = MotorBR.getCurrentPosition() - eBR;
        changeBL = MotorBL.getCurrentPosition() - eBL;
        telemetry.addData("FL Encoder", eFL);
        telemetry.addData("FR Encoder", eFR);
        telemetry.addData("BL Encoder", eBL);
        telemetry.addData("BR Encoder", eBR);
    }
}