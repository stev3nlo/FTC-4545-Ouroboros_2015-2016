package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Vandegrift InvenTeam on 2/15/2016.
 */
public class scoringMacros {
    //scores low from base, due to our current scoring stategegy will with not use this macro often
    public void scoreLow(DcMotor motorHangR, DcMotor motorHangL, Servo ramp, Servo drop, Servo boxBelt){
        //extends lift to the height of the low basket
        motorHangL.setPower(1);
        motorHangR.setPower(-1);
        try {
            wait(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        motorHangL.setPower(0);
        motorHangR.setPower(0);
        //moves the belt to the rihgt position
        boxBelt.setPosition(0);
        try {
            wait(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        boxBelt.setPosition(.5);
       //now that our box is aligned the drivers will manually dump and make any needed adjustments
    }

    public void scoreMid (DcMotor motorHangR, DcMotor motorHangL, Servo ramp, Servo drop, Servo boxBelt) {
        //extends lift to the height of the low basket
        motorHangL.setPower(1);
        motorHangR.setPower(-1);
        try {
            wait(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        motorHangL.setPower(0);
        motorHangR.setPower(0);
        //moves the belt to the rihgt position
        boxBelt.setPosition(0);
        try {
            wait(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boxBelt.setPosition(.5);
        //now that our box is aligned the drivers will manually dump and make any needed adjustments
    }

    public void scoreHigh (DcMotor motorHangR, DcMotor motorHangL, Servo ramp, Servo drop, Servo boxBelt) {
        //extends lift to the height of the low basket
        motorHangL.setPower(1);
        motorHangR.setPower(-1);
        try {
            wait(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        motorHangL.setPower(0);
        motorHangR.setPower(0);
        //moves the belt to the rihgt position
        boxBelt.setPosition(0);
        try {
            wait(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boxBelt.setPosition(.5);
        //now that our box is aligned the drivers will manually dump and make any needed adjustments
    }
}
