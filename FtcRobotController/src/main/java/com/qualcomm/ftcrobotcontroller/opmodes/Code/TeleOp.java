package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.*;

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
    public Servo boxTilt;

    final double HALFSPEED = .3;

    public void init(){

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

        
    }



    public void stop(){

    }
}
