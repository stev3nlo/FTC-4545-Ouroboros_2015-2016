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
    boolean reverse;
    final double HALFSPEED = .3;
    public Servo switchL;
    public Servo switchR;
    public Servo climber;
    long lastTime = 0;
    final long DURATION = 1500;
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
        if(Math.abs(gamepad1.left_stick_y) > .1){
            motorFL.setPower(gamepad1.left_stick_y);
            motorBL.setPower(gamepad1.left_stick_y);
        }
        else {
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
        if(gamepad2.x){
            climber.setPosition(1);
        }
        else
        {
            climber.setPosition(0);
        }
        //box: UNKNOWN FOR NOW
    }
    //we might remove this macro if our wheels and lift aren't able to move very fast
    public void Halfspeed(){
        //halfspeed base
        if(Math.abs(gamepad1.right_stick_y) > .1){
            motorFR.setPower(gamepad1.right_stick_y * -1 * HALFSPEED);
            motorBR.setPower(gamepad1.right_stick_y * -1 * HALFSPEED);
        }
        else {
            motorFR.setPower(0);
            motorBR.setPower(0);
        }
        if(Math.abs(gamepad1.left_stick_y) > .1){
            motorFL.setPower(gamepad1.left_stick_y * HALFSPEED);
            motorBL.setPower(gamepad1.left_stick_y * HALFSPEED);
        }
        else {
            motorFL.setPower(0);
            motorBL.setPower(0);
        }
        //servo for flipping switches: not affected by halfspeed
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
        //manipulator not affected by halfspeed
        if(Math.abs(gamepad2.left_stick_y) > .1){
            motorSpinner.setPower(gamepad2.left_stick_y);
        }
        else {
            motorSpinner.setPower(0);
        }
        //halspeed lift
        if(Math.abs(gamepad2.right_stick_y) > .1){
            motorHangL.setPower(gamepad2.right_stick_y * HALFSPEED);
            motorHangR.setPower(gamepad2.right_stick_y * -1 * HALFSPEED);
        }
        else {
            motorHangL.setPower(0);
            motorHangR.setPower(0);
        }
        //servo climber switch is not affected by halfspeed
        if(gamepad2.x){
            climber.setPosition(1);
        }
        else
        {
            climber.setPosition(0);
        }
        //box: UNKNOWN FOR NOW
    }
    public void Reverse(){
        //reverse base
        if(Math.abs(gamepad1.right_stick_y) > .1){
            motorFR.setPower(gamepad1.right_stick_y);
            motorBR.setPower(gamepad1.right_stick_y);
        }
        else {
            motorFR.setPower(0);
            motorBR.setPower(0);
        }
        if(Math.abs(gamepad1.left_stick_y) > .1){
            motorFL.setPower(gamepad1.left_stick_y * -1);
            motorBL.setPower(gamepad1.left_stick_y * -1);
        }
        else {
            motorFL.setPower(0);
            motorBL.setPower(0);
        }
        //servo for flipping switches: not affected by reverse
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
        //manipulator not affected by reverse
        if(Math.abs(gamepad2.left_stick_y) > .1){
            motorSpinner.setPower(gamepad2.left_stick_y);
        }
        else {
            motorSpinner.setPower(0);
        }
        //lift not affected by reverse
        if(Math.abs(gamepad2.right_stick_y) > .1){
            motorHangL.setPower(gamepad2.right_stick_y);
            motorHangR.setPower(gamepad2.right_stick_y * -1);
        }
        else {
            motorHangL.setPower(0);
            motorHangR.setPower(0);
        }
        //servo climber switch is not affected by reverse
        if(gamepad2.x){
            climber.setPosition(1);
        }
        else
        {
            climber.setPosition(0);
        }
        //box: UNKNOWN FOR NOW
    }
    @Override
    public void loop() {
        //loop that checks for halfspeed
        if (gamepad1.a) {
            long currentTime = System.currentTimeMillis();
            // are we waiting?
            if (currentTime > lastTime + DURATION) {
                if (halfspeed) {
                    halfspeed = false;
                    Normal();
                }
                else {
                    halfspeed = true;
                    Halfspeed();
                }
                lastTime = currentTime;
            }
        }
        else if(gamepad1.b){
            long currentTime = System.currentTimeMillis();
            // are we waiting?
            if (currentTime > lastTime + DURATION) {
                if (reverse) {
                    reverse = false;
                    Normal();
                }
                else {
                    reverse = true;
                    Reverse();
                }
                lastTime = currentTime;
            }
        }
        else{
            Normal();
        }
    }

}

