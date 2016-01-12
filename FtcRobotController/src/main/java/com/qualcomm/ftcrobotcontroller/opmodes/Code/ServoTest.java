package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Vandegrift InvenTeam on 1/11/2016.
 */
public class ServoTest extends OpMode {
    public DcMotor motorFR;
    public DcMotor motorFL;
    public DcMotor motorBR;
    public DcMotor motorBL;
    public DcMotor motorHangL;
    public DcMotor motorHangR;
    public DcMotor motorSpinner;
    boolean halfspeed;
    boolean reverse;
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
    }

    @Override
    public void loop() {
        //box
        //opens to the left
        if(gamepad1.x){
            boxL.setPosition(1);
            boxBelt.setPosition(1);
        }
        else{
            boxL.setPosition(.25);
            boxBelt.setPosition(.5);
        }
        //opens the box to the right
        if(gamepad1.b){
            boxR.setPosition(1);
            boxBelt.setPosition(0);
        }
        else{
            boxBelt.setPosition(.5);
            boxR.setPosition(.25);
        }
        //lock on servos
        //x is lock on
        if(gamepad1.x)
        {
            attachR.setPosition(.5);
            attachL.setPosition(.5);
        }
        //y is lock off
        else{
            attachR.setPosition(0);
            attachL.setPosition(0);
        }
    }
}
