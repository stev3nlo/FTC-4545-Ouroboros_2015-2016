package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Vandegrift InvenTeam on 1/7/2016.
 */
public class QualifierAutoRed extends LinearOpMode{
    DcMotor motorFR;
    DcMotor motorFL;
    DcMotor motorBR;
    DcMotor motorBL;
    DcMotor manipulator;
    Servo climber;
    int eFLe;
    int eFRe;
    int eBLe;
    int eBRe;

    @Override
    public void runOpMode() throws InterruptedException {
        motorFR = hardwareMap.dcMotor.get("motorFR");
        motorFL = hardwareMap.dcMotor.get("motorFL");
        motorBR = hardwareMap.dcMotor.get("motorBR");
        motorBL = hardwareMap.dcMotor.get("motorBL");
        manipulator = hardwareMap.dcMotor.get("manipulator");
        climber = hardwareMap.servo.get("climber");

        waitForStart();
    }

    public void moveForward(int speed, int distance) {


    }

    public int 

}
