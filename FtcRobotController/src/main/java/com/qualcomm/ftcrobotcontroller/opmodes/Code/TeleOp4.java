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
    final int FULLSPEED = 1;
    public Servo switchL;
    public Servo switchR;
    public Servo climber;
    final long DURATION = 1500000000;;
    public Servo boxR;
    public Servo boxL;
    public Servo boxBelt;
    public Servo attachR;
    public Servo attachL;
    long currentTimeH = 0;
    long lastTimeH = 0;
    long currentTimeR = 0;
    long lastTimeR = 0;
    double speed = 0;
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
           if (gamepad1.a) {
               currentTimeH = System.nanoTime();
               // are we waiting?
               if (currentTimeH > lastTimeH + DURATION) {
                   if (halfspeed) {
                       halfspeed = false;
                   }
                   else {
                       halfspeed = true;
                   }
                   lastTimeH = currentTimeH;
               }
           }
       }
        if(gamepad1.b){
            currentTimeR = System.nanoTime();
            if(currentTimeR > lastTimeR + DURATION)
            {
                    direction *= -1;
            }
            lastTimeR = currentTimeR;
        }
        //if statement for deciding speed multiplier
        speed = (halfspeed)? HALFSPEED:FULLSPEED;
        //WHEELS: toogle between halfspeed, reverse, reverse halfspeed, and normal
        if(Math.abs(gamepad1.right_stick_y) > .05)
        {
            motorFR.setPower(gamepad1.right_stick_y * speed * direction);
            motorBR.setPower(gamepad1.right_stick_y * speed * direction);
        }
        else{
            motorFR.setPower(0);
            motorFL.setPower(0);
        }
        if(Math.abs(gamepad1.left_stick_y) > .05){
            motorFL.setPower(gamepad1.left_stick_y * speed * direction * -1);
            motorBL.setPower(gamepad1.left_stick_y * speed * direction * -1);
        }
        else{
            motorFL.setPower(0);
            motorBL.setPower(0);
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
        if(gamepad1.x){
            climber.setPosition(0);
        }
        else
        {
            climber.setPosition(1);
        }
        //BOX: need to change later
        //opens to the left
        if(gamepad2.a){
            boxL.setPosition(0);
        }
        else{
            boxL.setPosition(.75);
        }
        //opens the box to the right
        if(gamepad2.b){
            boxR.setPosition(.75);
        }
        else{

            boxR.setPosition(0);
        }
        //triggers control boxBelt
        if(gamepad2.right_trigger > .25){
            boxBelt.setPosition(0);
        }
        else if (gamepad2.left_trigger > .25){
            boxBelt.setPosition(1);
        }
        else{
            boxBelt.setPosition(.5);
        }
        //lock on servos
        //x is lock on
        if(gamepad1.right_trigger > .5) {
            attachR.setPosition(.25);
            attachL.setPosition(.95);
        }
        //y is lock off
        else if(gamepad1.left_trigger > .5){
            attachR.setPosition(.85);
            attachL.setPosition(.35);
        }
    }
}


