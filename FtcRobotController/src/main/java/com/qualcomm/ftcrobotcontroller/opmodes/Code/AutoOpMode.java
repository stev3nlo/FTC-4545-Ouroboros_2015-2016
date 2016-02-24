package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import android.util.Log;

import com.qualcomm.ftcrobotcontroller.opmodes.AdafruitIMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.exception.RobotCoreException;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Steven on 2/23/2016.
 */
public abstract class AutoOpMode extends LinearOpMode {

    DcMotor motorFL;
    DcMotor motorFR;
    DcMotor motorBL;
    DcMotor motorBR;
    DcMotor manipulator;
    Servo climber;
    AdafruitIMU gyroSensor;
    int BL;
    int BR;
    int avg;
    volatile double[] rollAngle = new double[2], pitchAngle = new double[2], yawAngle = new double[2];

    public void initialize() throws InterruptedException {
        waitOneFullHardwareCycle();
        motorFL = hardwareMap.dcMotor.get("motorFL");
        motorFR = hardwareMap.dcMotor.get("motorFR");
        motorBL = hardwareMap.dcMotor.get("motorBL");
        motorBR = hardwareMap.dcMotor.get("motorBR");
        manipulator = hardwareMap.dcMotor.get("manipulator");
        climber = hardwareMap.servo.get("climber");
        telemetry.addData("init", "initializing");

        motorFL.setPower(0);
        motorFR.setPower(0);
        motorBL.setPower(0);
        motorBR.setPower(0);
        manipulator.setPower(0);
        climber.setPosition(1);
        try {
            gyroSensor = new AdafruitIMU(hardwareMap, "hydro"

                    //The following was required when the definition of the "I2cDevice" class was incomplete.
                    //, "cdim", 5

                    , (byte)(AdafruitIMU.BNO055_ADDRESS_A * 2)//By convention the FTC SDK always does 8-bit I2C bus
                    //addressing
                    , (byte) AdafruitIMU.OPERATION_MODE_IMU);
        } catch (RobotCoreException e){
            telemetry.addData("gyro", "gyro failed");
        }
        BL = 0;
        BR = 0;
        avg = 0;
        telemetry.addData("init", "initialized");
    }

    public void moveForwardWithEncoders(double speed, double goal) throws InterruptedException {
        startMotors(speed, -speed);
        double angle;
		angle = yawAngle[0];
		double currAngle;
        while (avg < goal) {
            getAvg();
            waitOneFullHardwareCycle();
            getAngles();
            currAngle = yawAngle[0];
            if ((currAngle - angle) > 2) {
                startMotors((speed * .75), -speed);
            } else {
                if ((currAngle - angle) < -2) {
                    startMotors(speed, -(speed * .75));
                } else {
                    startMotors(speed, -speed);
                }
            }
        }
        reset();
    }

    public void forwardWithManiWithEncoders(double speed, double goal) throws InterruptedException {
        startMotors(speed, -speed);
        manipulator.setPower(1);
        double angle;
		angle = yawAngle[0];
		double currAngle;
        while (avg < goal) {
            getAvg();
            waitOneFullHardwareCycle();
            getAngles();
            currAngle = yawAngle[0];
            if ((currAngle - angle) > 2) {
                startMotors((speed * .75), -speed);
            } else {
                if ((currAngle - angle) < -2) {
                    startMotors(speed, -(speed * .75));
                } else {
                    startMotors(speed, -speed);
                }
            }
        }
        reset();
    }

    public void moveBackwardsWithEncoders(double speed, double goal) throws InterruptedException {
        moveForwardWithEncoders(-speed, goal);
    }

    public void backwardsWithManiWithEncoders(double speed, double goal) throws InterruptedException {
        forwardWithManiWithEncoders(-speed, goal);
    }

    public void turnLeft(double speed, double angle) throws InterruptedException {
        double currAngle;
        getAngles();
		double newAngle;
		currAngle = yawAngle[0];
		newAngle = yawAngle[0];
        while ((newAngle - currAngle) > angle) {
            startMotors(speed, speed);
			getAngles();
			newAngle = yawAngle[0];
        }
    }

	public void turnRight(double speed, double angle) throws InterruptedException {
		turnLeft(-speed, -angle);
	}

    public void startMotors(double left, double right) {
        motorFL.setPower(left);
        motorFR.setPower(right);
        motorBL.setPower(left);
        motorBR.setPower(right);
    }

    //angle out of 180 degrees
    public void dropClimbers(int angle) throws InterruptedException {
        climber.setPosition(1 - (angle / 180));
        Thread.sleep(250);
        climber.setPosition(1);
    }

    public void getAngles() throws InterruptedException {
        gyroSensor.getIMUGyroAngles(rollAngle, pitchAngle, yawAngle);
        telemetry.addData("heading", yawAngle[0]);
    }

    public void reset() throws InterruptedException {
        motorFR.setPower(0);
        motorFL.setPower(0);
        motorBR.setPower(0);
        motorBL.setPower(0);
        manipulator.setPower(0);
        avg = 0;
        motorBL.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorBR.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        Thread.sleep(1000);
        motorBL.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motorBR.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    }

    public void getAvg() {
        BR = Math.abs(motorBR.getCurrentPosition());
        BL = Math.abs(motorBL.getCurrentPosition());
        avg = (BR + BL) / 2;
        showData();
    }

    public void showData() {
        telemetry.addData("Encoder Value", avg);
    }
}