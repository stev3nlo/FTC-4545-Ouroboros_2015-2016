package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.hardware.configuration.ServoConfiguration;


import static java.lang.Math.abs;


public class TeleOp2 extends OpMode{

//declares hardware
    public DcMotor motorFR;
    public DcMotor motorFL;
    public DcMotor motorBR;
    public DcMotor motorBL;
    public DcMotor motorHangR;
    public DcMotor motorHangL;
    public DcMotor motorSpinner;
    public DcMotor motorLift;
    public Servo boxTiltFR;
    public Servo boxTiltFL;
    public Servo boxTiltBR;
    public Servo boxTiltBL;
    public Servo switchL;
    public Servo switchR;
//variables
    double boxTiltPosition = 0.5;
    boolean reverse = false;
    boolean halfspeed = false;
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
        motorHangL = hardwareMap.dcMotor.get("motorHangL");
        motorHangR = hardwareMap.dcMotor.get("motorHangR");
        motorSpinner = hardwareMap.dcMotor.get("motorSpinner");
        motorLift = hardwareMap.dcMotor.get("motorLift");
        boxTiltBR = hardwareMap.servo.get("boxTiltBR");
        boxTiltBL = hardwareMap.servo.get("boxTiltBL");
        boxTiltFR = hardwareMap.servo.get("boxTiltFR");
        boxTiltFL = hardwareMap.servo.get("boxTiltFL");
        switchL = hardwareMap.servo.get("switchL");
        switchR = hardwareMap.servo.get("switchR");
        halfspeed = false;
        lastTime = System.currentTimeMillis();
        boolean tilt;
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
        // halfspeed servo: will run at full speed at all times
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
        if (abs(gamepad2.right_stick_y) > .05) { //sets the motors that move the hang pulley
            motorHangL.setPower(gamepad2.right_stick_y * HALFSPEED);
            motorHangR.setPower(gamepad2.right_stick_y * HALFSPEED * -1);
        }
        else {
            motorHangL.setPower(0);
            motorHangR.setPower(0);
        }

        //halfspeed maniuplator
        if (((gamepad2.left_trigger > 0.5) && (gamepad2.right_trigger > 0.5) || (gamepad2.left_trigger == 0) && (gamepad2.right_trigger == 0))) {
            motorSpinner.setPower(0); //If both triggers are pushed, set motor power to 0
        }
        else if (gamepad2.right_trigger > 0.5) {
            motorSpinner.setPower(HALFSPEED); //Spinner motor
        }
        else if (gamepad2.left_trigger > 0.5) {
            motorSpinner.setPower(-1 * HALFSPEED); //Reverse spinner motor
        }

        //box lift is now the regular lift
        if (abs(gamepad2.right_stick_y) > .05) {
            motorHangR.setPower(gamepad2.right_stick_y * HALFSPEED); //Lift the box
            motorHangL.setPower(gamepad2.right_stick_y * HALFSPEED * -1);
        }
        else {
            motorLift.setPower(0);
        }

        // box tilt(not affected by halfspeed)
        if (gamepad2.left_bumper) { //sets the box to two postions
                boxTiltFR.setPosition(.45); //When left bumper is pushed, set servos to 90 degrees
                boxTiltBR.setPosition(.45);
            }

        else { //Tilt the box in the other directiom
                boxTiltFR.setPosition(boxTiltPosition); //While right bumper is pushed, add .05 from the position of the servo
            }
        }

    public void regular() {
        //wheels
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
        // halfspeed servo: will run at full speed at all times
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

        // lift
        if (abs(gamepad2.right_stick_y) > .05) { //sets the motors that move the hang pulley
            motorHangL.setPower(gamepad2.right_stick_y);
            motorHangR.setPower(gamepad2.right_stick_y * -1);
        }
        else {
            motorHangL.setPower(0);
            motorHangR.setPower(0);
        }

        //halfspeed maniuplator
        if (((gamepad2.left_trigger > 0.5) && (gamepad2.right_trigger > 0.5) || (gamepad2.left_trigger == 0) && (gamepad2.right_trigger == 0))) {
            motorSpinner.setPower(0); //If both triggers are pushed, set motor power to 0
        }
        else if (gamepad2.right_trigger > 0.5) {
            motorSpinner.setPower(1); //Spinner motor
        }
        else if (gamepad2.left_trigger > 0.5) {
            motorSpinner.setPower(-1); //Reverse spinner motor
        }

        //box lift
        if (abs(gamepad2.left_stick_y) > .05) {
            motorLift.setPower(gamepad2.left_stick_y); //Lift the box
        }
        else {
            motorLift.setPower(0);
        }

        // box tilt
        if (gamepad2.left_bumper) { //Tilt the box
            if (boxTiltPosition != 0) {
                boxTiltPosition -= .05;
                boxTiltBR.setPosition(boxTiltPosition); //While left bumper is pushed, subtract .05 from the position of the servo
            }
        }
        if (gamepad2.right_bumper) { //Tilt the box in the other direction
            if (boxTiltPosition != 1) {
                boxTiltPosition += .05;
                boxTiltBR.setPosition(boxTiltPosition); //While right bumper is pushed, add .05 from the position of the servo
            }
        }
    }

    public void reverse(){
        //wheels
        if(abs(gamepad1.right_stick_y) > .05) {
            motorFR.setPower(gamepad1.right_stick_y);
            motorBR.setPower(gamepad1.right_stick_y);
        }
        else{
            motorFR.setPower(0);
            motorFR.setPower(0);
        }
        if((abs(gamepad1.left_stick_y) > .05)) {
            motorFL.setPower(gamepad1.left_stick_y * -1);
            motorBL.setPower(gamepad1.left_stick_y * -1);
        }
        else{
            motorFL.setPower(0);
            motorBL.setPower(0);
        }
        //servo: will run at full speed at all times and is not affected by reverse method
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

        // reverse lift
        if (abs(gamepad2.right_stick_y) > .05) { //sets the motors that move the hang pulley
            motorHangL.setPower(gamepad2.right_stick_y * -1);
            motorHangR.setPower(gamepad2.right_stick_y);
        }
        else {
            motorHangL.setPower(0);
            motorHangR.setPower(0);
        }

        //reverse maniuplator
        if (((gamepad2.left_trigger > 0.5) && (gamepad2.right_trigger > 0.5) || (gamepad2.left_trigger == 0) && (gamepad2.right_trigger == 0))) {
            motorSpinner.setPower(0); //If both triggers are pushed, set motor power to 0
        }
        else if (gamepad2.right_trigger > 0.5) {
            motorSpinner.setPower(-1); //Reverse Spinner motor
        }
        else if (gamepad2.left_trigger > 0.5) {
            motorSpinner.setPower(1); //Spinner motor
        }

        //reverse box lift
        if (abs(gamepad2.left_stick_y) > .05) {
            motorLift.setPower(gamepad2.left_stick_y * -1); //Lift the box
        }
        else {
            motorLift.setPower(0);
        }
    }

    public void reverseHalfspeed(){
        //reverse wheels in halfspeed
        if(abs(gamepad1.right_stick_y) > .05) {
            motorFR.setPower(gamepad1.right_stick_y * HALFSPEED);
            motorBR.setPower(gamepad1.right_stick_y * HALFSPEED);
        }
        else{
            motorFR.setPower(0);
            motorFR.setPower(0);
        }
        if((abs(gamepad1.left_stick_y) > .05)) {
            motorFL.setPower(gamepad1.left_stick_y * HALFSPEED * -1);
            motorBL.setPower(gamepad1.left_stick_y * HALFSPEED * -1);
        }
        else{
            motorFL.setPower(0);
            motorBL.setPower(0);
        }
        //servo: will run at full speed at all times and is not affected by reverse method
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

        // reverse lift in halfspeed
        if (abs(gamepad2.right_stick_y) > .05) { //sets the motors that move the hang pulley
            motorHangL.setPower(gamepad2.right_stick_y * HALFSPEED * -1);
            motorHangR.setPower(gamepad2.right_stick_y * HALFSPEED);
        }
        else {
            motorHangL.setPower(0);
            motorHangR.setPower(0);
        }

        //reverse maniuplator
        if (((gamepad2.left_trigger > 0.5) && (gamepad2.right_trigger > 0.5) || (gamepad2.left_trigger == 0) && (gamepad2.right_trigger == 0))) {
            motorSpinner.setPower(0); //If both triggers are pushed, set motor power to 0
        }
        else if (gamepad2.right_trigger > 0.5) {
            motorSpinner.setPower(-1 * HALFSPEED); //Reverse Spinner motor
        }
        else if (gamepad2.left_trigger > 0.5) {
            motorSpinner.setPower(HALFSPEED); //Spinner motor
        }

        //reverse box lift
        if (abs(gamepad2.left_stick_y) > .05) {
            motorLift.setPower(gamepad2.left_stick_y * HALFSPEED * -1); //Lift the box
        }
        else {
            motorLift.setPower(0);
        }

        //box tilt(not affected by reverse or halfspeed becuase it would futher confuse driver)
        if (gamepad2.left_bumper) { //Tilt the box
            if (boxTiltPosition != 0) {
                boxTiltPosition -= .05;
                boxTiltBR.setPosition(boxTiltPosition); //While left bumper is pushed, subtract .05 from the position of the servo
            }
        }
        if (gamepad2.right_bumper) { //Tilt the box in the other direction
            if (boxTiltPosition != 1) {
                boxTiltPosition += .05;
                boxTiltBR.setPosition(boxTiltPosition); //While right bumper is pushed, add .05 from the position of the servo
            }
        }
    }

    //macro for pulling the robot up
    public void pull(){
        motorHangR.setPower(1);

    }



    public void loop() {
        // halfspeed macro
        if(gamepad1.a){
            if (halfspeed && !reverse){
                halfspeed = false;
                regular();
            }
            else if(halfspeed && reverse){
                halfspeed = false;
                reverse();
            }
            else if (!halfspeed && !reverse){
                halfspeed = true;
                halfspeed();
            }
            else if (!halfspeed && !reverse){
                reverseHalfspeed();
            }
        }
        // reverse macro
        if (gamepad1.b){
            if (reverse && !halfspeed){
                reverse = false;
                regular();
            }
            else if(reverse && halfspeed){
                reverse = false;
                halfspeed();
            }
            else if (!reverse && !halfspeed){
                reverse = true;
                reverse();
            }
            else if (!reverse && halfspeed){
                reverse = true;
                reverseHalfspeed();
            }
        }
        //reset box
        if(gamepad2.a) {
            while (boxTiltPosition != 0) {
                boxTiltPosition -= .05;
                boxTiltBR.setPosition(boxTiltPosition);

            }
        }
        // if nothing pressed
        if (reverse && halfspeed){
            reverseHalfspeed();
        }
        else if(reverse && !halfspeed){
            reverse();
        }
        else if (!reverse && halfspeed){
            reverse();
        }
        else if (!reverse && !halfspeed){
            reverse();
        }
    }
}


