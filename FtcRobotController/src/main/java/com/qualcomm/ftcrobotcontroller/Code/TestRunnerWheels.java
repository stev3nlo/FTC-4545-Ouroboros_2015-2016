package com.qualcomm.ftcrobotcontroller.opmodes.Code;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import static java.lang.Math.abs;

/**
 * Created by Vandegrift InvenTeam on 10/22/2015.
 */
public class TestRunnerWheels extends OpMode {
    VariableSpeed mod = new VariableSpeed();

    public DcMotor motorFR;
    public DcMotor motorFL;
    public DcMotor motorBR;
    public DcMotor motorBL;
    boolean halfspeed;
    final double HALFSPEED = .08;
    final double FULLSPEED = 1.0;
    public Servo switchL;
    public Servo switchR;

    long lastTime = 0;
    final long DURATION = 1500;

    @Override
    public void init() {
        motorFL = hardwareMap.dcMotor.get("motorFR");
        motorFR = hardwareMap.dcMotor.get("motorFL");
        motorBL = hardwareMap.dcMotor.get("motorBR");
        motorBR = hardwareMap.dcMotor.get("motorBL");
        switchL = hardwareMap.servo.get("switchL");
        switchR = hardwareMap.servo.get("switchR");
        halfspeed = false;
        lastTime = System.currentTimeMillis();
    }

    public void loop(){
//        if (gamepad1.left_trigger == 1) {
//            long currentTime = System.currentTimeMillis();
//            // are we waiting?
//            if (currentTime > lastTime + DURATION) {
//                halfspeed = !halfspeed;
//                lastTime = currentTime;
//            }
//        }

        double speed = (halfspeed) ? HALFSPEED : FULLSPEED;
        boolean right_y = abs(gamepad1.right_stick_y) > .05;
        boolean left_y = abs(gamepad1.left_stick_y) > .05;

        if(right_y){
            motorFR.setPower(mod.newValue(gamepad1.right_stick_y) * -1 * speed);
            motorBR.setPower(mod.newValue(gamepad1.right_stick_y) * -1 * speed);
        } else {
            motorFR.setPower(0);
            motorBR.setPower(0);
        }

        if(left_y) {
            motorFL.setPower(mod.newValue(gamepad1.left_stick_y) * speed);
            motorBL.setPower(mod.newValue(gamepad1.left_stick_y) * speed);
        } else {
            motorFL.setPower(0);
            motorBL.setPower(0);
        }
        if (gamepad1.left_bumper) {
            switchL.setPosition(1);
        } else {
            switchL.setPosition(.5);
        }
        if (gamepad1.left_trigger > .05) {
            switchL.setPosition(0);
        } else {
            switchL.setPosition(.5);
        }
        if (gamepad1.right_bumper) {
            switchR.setPosition(0);
        } else {
            switchR.setPosition(.5);
        }
        if (gamepad1.right_trigger > .05) {
            switchR.setPosition(1);
        } else {
            switchR.setPosition(0);
        }
    }
}
