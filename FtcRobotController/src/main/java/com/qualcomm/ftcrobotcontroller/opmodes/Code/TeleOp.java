package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.hardware.configuration.ServoConfiguration;

import static java.lang.Math.abs;


/**
 * Created by Vandegrift InvenTeam on 10/1/2015.
 */
public class TeleOp extends OpMode {

//============================================= Hardware ===================================================
    public DcMotor motorFR;
    public DcMotor motorFL;
    public DcMotor motorBR;
    public DcMotor motorBL;
    public DcMotor motorHangR;
    public DcMotor motorHangL;
    public DcMotor motorSpinner;
    public DcMotor motorLift;
    public Servo boxTilt;
//============================================= Variables ==================================================
    double boxTiltPosition = 0.5;
    boolean reverse = false;
    boolean halfspeed;
    final double HALFSPEED = .3;

    public void init(){
        boxTilt.setPosition(boxTiltPosition);
    }
    public void loop() {
        if (gamepad1.a) {
            if (reverse) {
                reverse = false;
                if (gamepad1.left_trigger == 1) {
                        if (halfspeed) {
                            halfspeed = false;
                        } else {
                            halfspeed = true;
                        }
                        if (halfspeed) {
                            if (abs(gamepad1.right_stick_y) > .05) {
                                motorFR.setPower(gamepad1.right_stick_y * HALFSPEED * -1);
                                motorBR.setPower(gamepad1.right_stick_y * HALFSPEED * -1);
                            }
                            if (abs(gamepad1.left_stick_y) > .05) {
                                motorFL.setPower(gamepad1.left_stick_y * HALFSPEED);
                                motorBL.setPower(gamepad1.left_stick_y * HALFSPEED);
                            }
                        }
                    }
                else { //Regular tank controls
                    if (abs(gamepad1.right_stick_y) > .05) {
                        motorFR.setPower(gamepad1.right_stick_y);
                        motorBR.setPower(gamepad1.right_stick_y);
                    }
                    if (abs(gamepad1.left_stick_y) > .05) {
                        motorFL.setPower(gamepad1.left_stick_y * -1);
                        motorBL.setPower(gamepad1.left_stick_y * -1);
                    }
                }
            } else {
                reverse = true;
//============================================= Half Speed for drive ======================================
                if (gamepad1.left_trigger == 1) {
                    if (halfspeed) {
                        halfspeed = false;
                    } else {
                        halfspeed = true;
                    }
                    if (halfspeed) {
                        if (abs(gamepad1.right_stick_y) > .05) {
                            motorFR.setPower(gamepad1.right_stick_y * HALFSPEED * -1);
                            motorBR.setPower(gamepad1.right_stick_y * HALFSPEED * -1);
                        }
                        if (abs(gamepad1.left_stick_y) > .05) {
                            motorFL.setPower(gamepad1.left_stick_y * HALFSPEED);
                            motorBL.setPower(gamepad1.left_stick_y * HALFSPEED);
                        }
                    }
                }
//=========================================== Regular Tank Controls ========================================
                else {
                    if (abs(gamepad1.right_stick_y) > .05) {
                        motorFR.setPower(gamepad1.right_stick_y);
                        motorBR.setPower(gamepad1.right_stick_y);
                    }
                    if (abs(gamepad1.left_stick_y) > .05) {
                        motorFL.setPower(gamepad1.left_stick_y * -1);
                        motorBL.setPower(gamepad1.left_stick_y * -1);
                    }
                }

            }
        }
//============================================== Manipulator =================================================
        if (((gamepad2.left_trigger > 0.5) && (gamepad2.right_trigger > 0.5)) || (gamepad2.left_trigger == 0) && (gamepad2.right_trigger == 0)) {
            motorSpinner.setPower(0); //If both triggers are pushed, set motor power to 0
        } else if (gamepad2.right_trigger > 0.5) {
            motorSpinner.setPower(1); //Spinner motor
        } else if (gamepad2.left_trigger > 0.5) {
            motorSpinner.setPower(-1); //Reverse spinner motor
        }

//=============================================== Box Lift ====================================================
        if (gamepad2.left_stick_y < -.05) {
            motorLift.setPower(gamepad2.left_stick_y * -1); //Lift the box
        } else {
            motorLift.setPower(0);
        }

//=============================================== Box Tilt ===================================================
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
//============================================== Lift for Hang =================================================
        if (gamepad2.right_stick_y > .05 || gamepad2.left_stick_y < .05) { //sets the motors that move the hang pulley
            motorHangL.setPower(gamepad2.right_stick_y * -1);
            motorHangR.setPower(gamepad2.right_stick_y * -1);
        } else {
            motorHangL.setPower(0);
            motorHangR.setPower(0);
        }
//==================================================== Macros ==================================================
        if (gamepad1.x) {
            motorFR.setPower(-1);
            motorFL.setPower(1);
            motorBR.setPower(-1);
            motorBL.setPower(1);
        }
        if (gamepad1.b) {
            motorFR.setPower(1);
            motorFL.setPower(-1);
            motorBR.setPower(1);
            motorBL.setPower(-1);
        }
    }

    public void stop(){

    }
}
