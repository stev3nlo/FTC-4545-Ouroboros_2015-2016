package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.hardware.configuration.ServoConfiguration;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Vandegrift InvenTeam on 1/21/2016.
 */
public class TeleOp4 extends OpMode{
    public DcMotor motorFR;
    public DcMotor motorFL;
    public DcMotor motorBR;
    public DcMotor motorBL;
    public DcMotor motorHangL;
    public DcMotor motorHangR;
    public DcMotor motorSpinner;
    boolean halfspeed;
    int direction;
    final double HALFSPEED = .3;
    public Servo switchL;
    public Servo switchR;
    public Servo climber;
    long lastTime = 0;
    final long DURATION = 1500;
    public Servo boxR;
    public Servo boxL;
    public Servo boxBelt;
    public Servo attachR;
    public Servo attachL;
    ElapsedTime time = new ElapsedTime();
    @Override
    public void init() {
        motorFL = hardwareMap.dcMotor.get("motorFL");
        motorFR = hardwareMap.dcMotor.get("motorFR");
        motorBL = hardwareMap.dcMotor.get("motorBL");
        motorBR = hardwareMap.dcMotor.get("motorBR");
        switchL = hardwareMap.servo.get("switchL");
        switchR = hardwareMap.servo.get("switchR");
        motorHangL = hardwareMap.dcMotor.get("liftL");
        motorHangR = hardwareMap.dcMotor.get("liftR");
        motorSpinner = hardwareMap.dcMotor.get("manipulator");
        climber = hardwareMap.servo.get("climber");
        boxR = hardwareMap.servo.get("boxR");
        boxL = hardwareMap.servo.get("boxL");
        boxBelt = hardwareMap.servo.get("boxBelt");
        attachR = hardwareMap.servo.get("attachR");
        attachL = hardwareMap.servo.get("attachL");
        halfspeed = false;
        boxBelt.setPosition(.5);
        boxL.setPosition(0);
        boxR.setPosition(0);
        climber.setPosition(1);
    }

    @Override
    public void loop() {
       if(gamepad1.a)
       {
           time.startTime();
           if(time)
       }

    }
}
