package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.RobotLog;

/**
 * Created by Steven on 1/9/2016.
 */
public class AutonomousRed extends LinearOpMode {
    DcMotor motorFR;
    DcMotor motorFL;
    DcMotor motorBR;
    DcMotor motorBL;
    DcMotor manipulator;
    Servo climber;
    int BR = 0;
    int BL = 0;
    int avg = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        try {
            waitOneFullHardwareCycle();
        } catch (InterruptedException e) {
            RobotLog.e(e.getMessage());
        }

        motorFR = hardwareMap.dcMotor.get("motorFR");
        motorFL = hardwareMap.dcMotor.get("motorFL");
        motorBR = hardwareMap.dcMotor.get("motorBR");
        manipulator = hardwareMap.dcMotor.get("manipulator");
        motorBL = hardwareMap.dcMotor.get("motorBL");
        climber = hardwareMap.servo.get("climber");

        climber.setPosition(1);

        try {
            waitForStart();
        } catch (InterruptedException e) {
            RobotLog.e(e.getMessage());
        }


        backwardsWithMani(1, 4300);
        turnRight(1, 600);
        backwardsWithMani(1, 1300);
        turnLeft(1, 1100);
        //add a while loop that continues along the white line
        backwardsWithMani(.5, 1750);
        dropClimbers();
        moveForward(1, 250);
        turnLeft(1, 1200);
        moveBackwards(1, 1000);

     }

    public void moveForward(double speed, int distance) {
        getAvg();
        motorFR.setPower(-speed);
        motorFL.setPower(speed);
        motorBR.setPower(-speed);
        motorBL.setPower(speed);
        while (avg < distance) {
            getAvg();
            showData();
            try {
                waitOneFullHardwareCycle();
            } catch (InterruptedException e) {
                RobotLog.e(e.getMessage());
            }
        }
        reset();
    }

    public void moveBackwards(double speed, int distance) {
        getAvg();
        motorFR.setPower(speed);
        motorFL.setPower(-speed);
        motorBR.setPower(speed);
        motorBL.setPower(-speed);
        avg = 0;
        while (avg < distance) {
            getAvg();
            showData();
            try {
                waitOneFullHardwareCycle();
            } catch (InterruptedException e) {
                RobotLog.e(e.getMessage());
            }
        }
        reset();
    }

    public void forwardWithMani(double speed, int distance) {
        getAvg();
        motorFR.setPower(-speed);
        motorFL.setPower(speed);
        motorBR.setPower(-speed);
        motorBL.setPower(speed);
        manipulator.setPower(-1);
        while (avg < distance) {
            getAvg();
            showData();
            try {
                waitOneFullHardwareCycle();
            } catch (InterruptedException e) {
                RobotLog.e(e.getMessage());
            }
        }
        reset();
    }

    public void backwardsWithMani(double speed, int distance) {
        getAvg();
        motorFR.setPower(speed);
        motorFL.setPower(-speed);
        motorBR.setPower(speed);
        motorBL.setPower(-speed);
        manipulator.setPower(-1);
        while (avg < distance) {
            getAvg();
            showData();
            try {
                waitOneFullHardwareCycle();
            } catch (InterruptedException e) {
                RobotLog.e(e.getMessage());
            }
        }
        reset();
    }

    public void turnRight(double speed, int distance) {
        getAvg();
        motorFR.setPower(speed);
        motorFL.setPower(speed);
        motorBR.setPower(speed);
        motorBL.setPower(speed);
        while (avg < distance) {
            getAvg();
            showData();
            try {
                waitOneFullHardwareCycle();
            } catch (InterruptedException e) {
                RobotLog.e(e.getMessage());
            }
        }
        reset();
    }

    public void turnLeft(double speed, int distance) {
        getAvg();
        motorFR.setPower(-speed);
        motorFL.setPower(-speed);
        motorBR.setPower(-speed);
        motorBL.setPower(-speed);
        while (avg < distance) {
            getAvg();
            showData();
            try {
                waitOneFullHardwareCycle();
            } catch (InterruptedException e) {
                RobotLog.e(e.getMessage());
            }
        }
        reset();
    }

    public void dropClimbers() {
        climber.setPosition(0);
        try {
            Thread.sleep(1500);
        } catch (Exception  e) {
            e.printStackTrace();
        }
        climber.setPosition(1);
    }

    public void reset(){
        motorFR.setPower(0);
        motorFL.setPower(0);
        motorBR.setPower(0);
        motorBL.setPower(0);
        manipulator.setPower(0);
        avg = 0;
        motorBL.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorBR.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            RobotLog.e(e.getMessage());
        }
        motorBL.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motorBR.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    }

    public void getAvg() {
        BR = Math.abs(motorBR.getCurrentPosition());
        BL = Math.abs(motorBL.getCurrentPosition());
        avg = (BR + BL) / 2;
    }

    public void showData() {
        telemetry.addData("Encoder Value", avg);
    }
}
