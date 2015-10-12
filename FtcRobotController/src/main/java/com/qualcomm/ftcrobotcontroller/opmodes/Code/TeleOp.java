package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.hardware.configuration.ServoConfiguration;

import static java.lang.Math.abs;


/**
 * Created by Vandegrift InvenTeam on 10/1/2015.
 */
public class TeleOp extends OpMode {

    public DcMotor motorFR;
    public DcMotor motorFL;
    public DcMotor motorBR;
    public DcMotor motorBL;
    public DcMotor motorHangR;
    public DcMotor motorHangL;
    public DcMotor motorSpinner;
    public DcMotor motorLift;
    public Servo boxTilt;

    double boxTiltPosition = 0.5;

    final double HALFSPEED = .3;

    public void init(){
        boxTilt.setPosition(boxTiltPosition);
    }
    public void loop() {
        if (gamepad1.left_trigger == 1) {
            if (abs(gamepad1.right_stick_y) > .05) { //Half speed for drive
                motorFR.setPower(gamepad1.right_stick_y * HALFSPEED);
                motorBR.setPower(gamepad1.right_stick_y * HALFSPEED);
            }
            if (abs(gamepad1.left_stick_y) > .05) {
                motorFL.setPower(gamepad1.left_stick_y * HALFSPEED * -1);
                motorBL.setPower(gamepad1.left_stick_y * HALFSPEED * -1);
            }
        }
        else{ //Regular tank controls
            if (abs(gamepad1.right_stick_y) > .05) {
                motorFR.setPower(gamepad1.right_stick_y * -1);
                motorBR.setPower(gamepad1.right_stick_y * -1);
            }
            if (abs(gamepad1.left_stick_y) > .05) {
                motorFL.setPower(gamepad1.left_stick_y);
                motorBL.setPower(gamepad1.left_stick_y);
            }
        }


        if (((gamepad2.left_trigger > 0.5) && (gamepad2.right_trigger > 0.5)) || (gamepad2.left_trigger == 0) && (gamepad2.right_trigger == 0)) {
            motorSpinner.setPower(0); //If both triggers are pushed, set motor power to 0
        }
        else if (gamepad2.right_trigger > 0.5) {
            motorSpinner.setPower(1); //Spinner motor
        }

        else if (gamepad2.left_trigger > 0.5) {
            motorSpinner.setPower(-1); //Reverse spinner motor
        }


        if (gamepad2.left_stick_y < -.05) {
            motorLift.setPower(gamepad2.left_stick_y * -1); //Lift the box
        }
        else {
            motorLift.setPower(0);
        }


        if (gamepad2.left_bumper) { //Tilt the box
            if (boxTiltPosition != 0) {
                boxTiltPosition -= .05;
                boxTilt.setPosition(boxTiltPosition); //While left bumper is pushed, subtract .05 from the position of the servo
            }
        }
        if (gamepad2.right_bumper) { //Tilt the box in the other direction
            if (boxTiltPosition != 1) {
                boxTiltPosition += .05;
                boxTilt.setPosition(boxTiltPosition); //While right bumper is pushed, add .05 from the position of the servo
            }
        }

        if (gamepad2.right_stick_y >.05 || gamepad2.left_stick_y <.05) { //sets the motors that move the hang pulley
            motorHangL.setPower(gamepad2.right_stick_y * -1);
            motorHangR.setPower(gamepad2.right_stick_y * -1);
        }
        else {
            motorHangL.setPower(0);
            motorHangR.setPower(0);
        }
    }

    public void stop(){

    }
}
