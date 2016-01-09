package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.hardware.configuration.ServoConfiguration;
/**
 * Created by sopa on 12/30/15.
 */
public class Test extends OpMode{
    public DcMotor motorFR;
    public DcMotor motorFL;
    public DcMotor motorBR;
    public DcMotor motorBL;
    public DcMotor manipulator;
    public DcMotor liftR;
    public DcMotor liftL;
    public Servo switchL;
    public Servo switchR;
    public Servo hookL;
    public Servo hookR;

    @Override
    public void init() {
        motorFL = hardwareMap.dcMotor.get("motorFL");
        motorFR = hardwareMap.dcMotor.get("motorFR");
        motorBL = hardwareMap.dcMotor.get("motorBL");
        motorBR = hardwareMap.dcMotor.get("motorBR");
        manipulator = hardwareMap.dcMotor.get("manipulator");
        liftR = hardwareMap.dcMotor.get("liftR");
        liftL = hardwareMap.dcMotor.get("liftL");
        switchL = hardwareMap.servo.get("switchL");
        switchR = hardwareMap.servo.get("switchR");
        hookL = hardwareMap.servo.get("hookL");
        hookR = hardwareMap.servo.get("hookR");
    }

    @Override
    public void loop() {
        //tests wheels
        if(Math.abs(gamepad1.right_stick_y) > .05){
            motorFR.setPower(gamepad1.right_stick_y);
            motorBR.setPower(gamepad1.right_stick_y);
        }
        else{
            motorFR.setPower(0);
            motorBR.setPower(0);
        }
        if(Math.abs(gamepad1.left_stick_y) > .05){
            motorFL.setPower(gamepad1.left_stick_y * -1);
            motorBL.setPower(gamepad1.left_stick_y * -1);
        }
        else{
            motorFL.setPower(0);
            motorBL.setPower(0);
        }
        //REMEMBER TO FLIP!!!!
        //test manipulator
<<<<<<< Updated upstream
        if(Math.abs(gamepad2.right_stick_y) > .1) {
            manipulator.setPower(gamepad2.right_stick_y * -1);
        }
        else{
                manipulator.setPower(0);
=======
        if(Math.abs(gamepad1.right_trigger) > .1)
        {
            if(gamepad1.a){
                manipulator.setPower(gamepad1.right_trigger);
            }
            else{
                manipulator.setPower(gamepad1.right_trigger * -1);
            }
        }
        else{
            manipulator.setPower(0);
        }
        //test lift
        if(Math.abs(gamepad1.left_trigger) > .1){
            if(gamepad1.b){
                liftR.setPower(gamepad1.left_trigger * -1);
                liftL.setPower(gamepad1.left_trigger);
            }
            else{
                liftR.setPower(gamepad1.left_trigger);
                liftL.setPower(gamepad1.left_trigger);
>>>>>>> Stashed changes
            }
        //test lift
        if(Math.abs(gamepad2.left_stick_y) > .1){
            liftR.setPower(gamepad2.left_stick_y);
            liftL.setPower(gamepad2.left_stick_y * -1);
        }
        else{
            liftR.setPower(0);
            liftL.setPower(0);
        }
        //switches
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

        //hooks
        if (gamepad1.x) {
            hookL.setPosition(.5);
            hookR.setPosition(.5);
        } else {
            hookL.setPosition(0);
            hookR.setPosition(1);
        }
    }
}
