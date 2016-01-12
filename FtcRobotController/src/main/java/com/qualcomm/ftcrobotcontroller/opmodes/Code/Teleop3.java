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
        halfspeed = false;
    }
    public void Normal(){
        //base
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
            climber.setPosition(.5);
        }
        else
        {
            climber.setPosition(0);
        }
        //box
        //opens to the left
        if(gamepad2.x){
            boxL.setPosition(1);
            boxBelt.setPosition(1);
        }
        else{
            boxL.setPosition(.25);
            boxBelt.setPosition(.5);
        }
        //opens the box to the right
        if(gamepad2.b){
            boxR.setPosition(1);
            boxBelt.setPosition(0);
        }
        else{
            boxBelt.setPosition(.5);
            boxR.setPosition(.25);
        }
        //lock on servos
        //x is lock on
        if(gamepad2.x)
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
    //we might remove this macro if our wheels and lift aren't able to move very fast
    public void Halfspeed(){
        //halfspeed base
        if(Math.abs(gamepad1.right_stick_y) > .1){
            motorFR.setPower(gamepad1.right_stick_y * HALFSPEED);
            motorBR.setPower(gamepad1.right_stick_y * HALFSPEED);
        }
        else {
            motorFR.setPower(0);
            motorBR.setPower(0);
        }
        if(Math.abs(gamepad1.left_stick_y) > .1){
            motorFL.setPower(gamepad1.left_stick_y * HALFSPEED* -1);
            motorBL.setPower(gamepad1.left_stick_y * HALFSPEED *- 1);
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
            motorHangR.setPower(gamepad2.right_stick_y * HALFSPEED * -1);
        }
        else {
            motorHangL.setPower(0);
            motorHangR.setPower(0);
        }
        //servo climber switch is not affected by halfspeed
        if(gamepad2.x){
            climber.setPosition(.5);
        }
        else
        {
            climber.setPosition(0);
        }
        //box is not affected by halfspeed
        //opens to the left
        if(gamepad2.x){
            boxL.setPosition(1);
            boxBelt.setPosition(1);
        }
        else{
            boxL.setPosition(.25);
            boxBelt.setPosition(.5);
        }
        //opens the box to the right
        if(gamepad2.b){
            boxR.setPosition(1);
            boxBelt.setPosition(0);
        }
        else{
            boxBelt.setPosition(.5);
            boxR.setPosition(.25);
        }
        //lock on servos(not affected by halfspeed)
        //x is lock on
        if(gamepad2.x)
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
    public void Reverse(){
        //reverse base
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
        if(gamepad2.a){
            climber.setPosition(.5);
        }
        else
        {
            climber.setPosition(0);
        }
        //box is not affected by reverse
        //opens to the left
        if(gamepad2.x){
            boxL.setPosition(1);
            boxBelt.setPosition(1);
        }
        else{
            boxL.setPosition(.25);
            boxBelt.setPosition(.5);
        }
        //opens the box to the right
        if(gamepad2.b){
            boxR.setPosition(1);
            boxBelt.setPosition(0);
        }
        else{
            boxBelt.setPosition(.5);
            boxR.setPosition(.25);
        }
        //lock on servos(not affected by reverse)
        //x is lock on
        if(gamepad2.x)
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

