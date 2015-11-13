package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by Steven on 11/9/2015.
 */
public class AutoBlue extends LinearOpMode {
    DcMotor motorFL;
    DcMotor motorFR;
    DcMotor motorBL;
    DcMotor motorBR;
    double start;
    double curr;
    double change;
    @Override
    public void runOpMode() throws InterruptedException {
        motorFL = hardwareMap.dcMotor.get("motorFR");
        motorFR = hardwareMap.dcMotor.get("motorFL");
        motorBL = hardwareMap.dcMotor.get("motorBR");
        motorBR = hardwareMap.dcMotor.get("motorBL");
        motorFL.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motorBL.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motorFR.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motorBR.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        try {
            waitForStart();
        } catch (InterruptedException e){
            telemetry.addData("Error", e);
        }
        moveForward(1, 4500);
        turn("right", .5, 3000);
        moveBackwards(1, 4500);
    }

    public void reset() {
        motorFL.setPower(0);
        motorFR.setPower(0);
        motorBL.setPower(0);
        motorBR.setPower(0);
        start = 0;
        curr = 0;
        change = 0;
    }

    public void updateChange() {
        start = (Math.abs(motorFL.getCurrentPosition()) + Math.abs(motorFR.getCurrentPosition()) + Math.abs(motorBL.getCurrentPosition()) + Math.abs(motorBR.getCurrentPosition())) / 4;
        change = curr - start;
    }

    public void updateCurr() {
        curr = (Math.abs(motorFL.getCurrentPosition()) + Math.abs(motorFR.getCurrentPosition()) + Math.abs(motorBL.getCurrentPosition()) + Math.abs(motorBR.getCurrentPosition())) / 4;
    }

    public void moveForward(double speed, double goal) {
        reset();
        while (change < goal) {
            telemetry.addData("Change", change);
            motorFL.setPower(speed);
            motorFR.setPower(-speed);
            motorBL.setPower(speed);
            motorBR.setPower(-speed);
            updateCurr();
            updateChange();
        }
        reset();
        try {
            sleep(500);
        } catch (InterruptedException e){
            telemetry.addData("Error", e);
        }
    }

    public void moveBackwards(double speed, double goal) {
        reset();
        while (change < goal) {
            telemetry.addData("Change", change);
            motorFL.setPower(-speed);
            motorFR.setPower(speed);
            motorBL.setPower(-speed);
            motorBR.setPower(speed);
            updateCurr();
            updateChange();
        }
        reset();
        try {
            sleep(500);
        } catch (InterruptedException e){
            telemetry.addData("Error", e);
        }
    }

    public void turn(String dir, double speed, double goal) {
        reset();
        if (dir.equals("right")) {
            while (change < goal) {
                telemetry.addData("Change", change);
                motorFL.setPower(speed);
                motorFR.setPower(speed);
                motorBL.setPower(speed);
                motorBR.setPower(speed);
                updateCurr();
                updateChange();
            }
            reset();
            try {
                sleep(500);
            } catch (InterruptedException e){
                telemetry.addData("Error", e);
            }
        }
        if (dir.equals("left")) {
            while (change < goal) {
                telemetry.addData("Change", change);
                motorFL.setPower(-speed);
                motorFR.setPower(-speed);
                motorBL.setPower(-speed);
                motorBR.setPower(-speed);
                updateCurr();
                updateChange();
            }
            reset();
            try {
                sleep(500);
            } catch (InterruptedException e){
                telemetry.addData("Error", e);
            }
        }
        else {
            telemetry.addData("Error: ", "Invalid Direction");
        }
        reset();
        try {
            sleep(500);
        } catch (InterruptedException e){
            telemetry.addData("Error", e);
        }
    }
}
