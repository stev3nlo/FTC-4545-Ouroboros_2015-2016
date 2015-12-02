package com.qualcomm.ftcrobotcontroller.opmodes.Code;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import static java.lang.Math.abs;

/**
 * Created by Steven on 12/1/2015.
 */
public class newDriveTrainTeleOp extends OpMode {

    VariableSpeed mod = new VariableSpeed();

    public DcMotor motorFR;
    public DcMotor motorFL;
    public DcMotor motorBR;
    public DcMotor motorBL;
    public DcMotor motorHangL;
    public DcMotor motorHangR;
    public DcMotor motorSpinner;
    boolean halfspeed;
    final double HALFSPEED = .3;
    final double FULLSPEED = 1.0;
    public Servo switchL;
    public Servo switchR;
    public Servo climber;
    long lastTime = 0;
    final long DURATION = 1500;
    public boolean ramp = false;

    @Override
    public void init() {
        motorFL = hardwareMap.dcMotor.get("motorFL");
        motorFR = hardwareMap.dcMotor.get("motorFR");
        motorBL = hardwareMap.dcMotor.get("motorBL");
        motorBR = hardwareMap.dcMotor.get("motorBR");
        switchL = hardwareMap.servo.get("switchL");
        switchR = hardwareMap.servo.get("switchR");
        motorHangL = hardwareMap.dcMotor.get("motorHangL");
        motorHangR = hardwareMap.dcMotor.get("motorHangR");
        motorSpinner = hardwareMap.dcMotor.get("motorSpinner");
        climber = hardwareMap.servo.get("climber");
        halfspeed = false;
        lastTime = System.currentTimeMillis();
        switchR.setPosition(.5);
        climber.setPosition(1);
        motorFR.setPower(0);
        motorBR.setPower(0);
        motorFL.setPower(0);
        motorBL.setPower(0);
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            long currentTime = System.currentTimeMillis();
            // are we waiting?
            if (currentTime > lastTime + DURATION) {
                if (halfspeed) {
                    halfspeed = false;
                } else {
                    halfspeed = true;
                }
                lastTime = currentTime;
            }
        }
        double speed = (halfspeed) ? HALFSPEED : FULLSPEED;
        boolean right_y = abs(gamepad1.right_stick_y) > .05;
        boolean left_y = abs(gamepad1.left_stick_y) > .05;
        if (gamepad1.right_stick_y > .05 && gamepad1.left_stick_y < -.05) {
            speed = .3;
        } else {
            if (gamepad1.right_stick_y < -.05 && gamepad1.left_stick_y > .05) {
                speed = .3;
            } else {
                speed = FULLSPEED;
            }
        }

        if (gamepad1.x) {
            ramp = true;
        }
        if (gamepad1.y) {
            ramp = false;
        }

        if (!ramp) {
            if (right_y) {
                motorBR.setPower(mod.newValue(gamepad1.right_stick_y) * -1 * speed);
            } else {
                motorBR.setPower(0);
            }

            if (left_y) {
                motorBL.setPower(mod.newValue(gamepad1.left_stick_y) * speed);
            } else {
                motorBL.setPower(0);
            }
        } else {
            if (ramp) {
                if (right_y) {
                    motorFR.setPower(mod.newValue(gamepad1.right_stick_y) * -1 * speed);
                    motorBR.setPower(mod.newValue(gamepad1.right_stick_y) * -1 * speed);
                } else {
                    motorFR.setPower(0);
                    motorBR.setPower(0);
                }

                if (left_y) {
                    motorFL.setPower(mod.newValue(gamepad1.left_stick_y) * speed);
                    motorBL.setPower(mod.newValue(gamepad1.left_stick_y) * speed);
                } else {
                    motorFL.setPower(0);
                    motorBL.setPower(0);
                }
            }
        }

        if (gamepad2.right_stick_y > .05 || gamepad2.left_stick_y > .05) { //sets the motors that move the hang pulley
            motorHangL.setPower(gamepad2.left_stick_y);
            motorHangR.setPower(gamepad2.right_stick_y * -1);
        } else {
            motorHangL.setPower(0);
            motorHangR.setPower(0);
        }

        if (gamepad1.left_bumper) {
            switchL.setPosition(1);
        } else {
            switchL.setPosition(.7);
            if (gamepad1.left_trigger > .05) {
                switchL.setPosition(0);
            } else {
                switchL.setPosition(.7);
            }

            if (gamepad1.right_bumper) {
                switchR.setPosition(1);
            } else {
                switchR.setPosition(.5);
                if (gamepad1.right_trigger > .05) {
                    switchR.setPosition(0);
                } else {
                    switchR.setPosition(.5);
                }
            }
        }

        if (Math.abs(gamepad2.right_stick_y) > .05) {
            motorSpinner.setPower(gamepad2.right_stick_y);
        } else {
            motorSpinner.setPower(0);
        }

        if (gamepad2.a) {
            climber.setPosition(0);
        } else {
            climber.setPosition(1);
        }

        if (Math.abs(gamepad2.left_stick_y) > .05) {
            motorHangL.setPower(gamepad2.left_stick_y);
            motorHangR.setPower(gamepad2.left_stick_y * -1);
        } else {
            motorHangL.setPower(0);
            motorHangR.setPower(0);
        }
    }
}