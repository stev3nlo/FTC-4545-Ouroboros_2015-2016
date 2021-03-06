package com.qualcomm.ftcrobotcontroller.opmodes.Autonomouses;

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
	Servo boxBelt;
	Servo ramp;
	Servo drop;
	Servo switchL;
	Servo switchR;
	Servo hitter;
    AdafruitIMU gyroSensor;
    ColorSensor colorSensorR;
    ColorSensor colorSensorL;
    int BL;
    int BR;
    int avg;
	String gyroInit;
    volatile double[] rollAngle = new double[2], pitchAngle = new double[2], yawAngle = new double[2];
    int[] colorL = new int[3];
    int[] colorR = new int[3];

	//constant variables
	public static final double CLIMBERS_ANGLE = 180;

	//constant variables for BLUE side
	public static final int BLUE_START = 200;
	public static final int BLUE_TURN_TO_WALL = 35;
	public static final int BLUE_START_TO_WALL = 1575;
	public static final int BLUE_TURN_TO_LINE = 35;
	public static final int BLUE_WALL_TO_LINE = 150; //increased by 100 on 3-4-16 8:02
	public static final int BLUE_TURN_TO_BEACON = 65;
	public static final int BLUE_LINE_TO_BEACON = 550; //increased by 100 on 3-4-16 8:02
	public static final int BLUE_BACK_AWAY_FROM_BEACON = 2000;

	//move out of way for alliance for BLUE side
	public static final int BLUE_TURN_TO_CLEAR = 90;
	public static final int BLUE_BEACON_TO_CLEAR = 400;

	//goes to clear other ramp for BLUE side
	public static final int BLUE_TURN_TO_OTHER_SIDE = 140;
	public static final int BLUE_BEACON_TO_OTHER_SIDE = 2000;
	public static final int BLUE_TURN_TO_RAMP_BASE = 40;
	public static final int BLUE_OTHER_SIDE_TO_CLEAR_RAMP_BASE = 2000;

	//goes to block enemy autonomous for BLUE side
	public static final int BLUE_TURN_TO_OTHER_BEACON = 135;
	public static final int BLUE_BEACON_TO_BEACON = 3000;

	//goes to ramp and hits switches for BLUE side
	public static final int BLUE_TURN_TO_RAMP =  100;
	public static final int BLUE_BEACON_TO_RAMP = 2500;
	public static final int BLUE_TURN_TO_CHURRO = 95;
	public static final int BLUE_RAMP_TO_CHURRO = 500;

	//constant variables for RED side
	public static final int RED_START = 200;
	public static final int RED_TURN_TO_WALL = 40;
	public static final int RED_START_TO_WALL = 1500;
	public static final int RED_TURN_TO_LINE = 40;
	public static final int RED_WALL_TO_LINE = 250;
	public static final int RED_TURN_TO_BEACON = 80;
	public static final int RED_LINE_TO_BEACON = 600;
	public static final int RED_BACK_AWAY_FROM_BEACON = 2000;

	//move out of way for alliance for RED side
	public static final int RED_TURN_TO_CLEAR = 90;
	public static final int RED_BEACON_TO_CLEAR = 400;

	//goes to clear other ramp for RED side
	public static final int RED_TURN_TO_OTHER_SIDE = 140;
	public static final int RED_BEACON_TO_OTHER_SIDE = 2000;
	public static final int RED_TURN_TO_RAMP_BASE = 40;
	public static final int RED_OTHER_SIDE_TO_CLEAR_RAMP_BASE = 2000;

	//goes to ramp and hits switches for RED side
	public static final int RED_TURN_TO_RAMP =  80;
	public static final int RED_BEACON_TO_RAMP = 2500;
	public static final int RED_TURN_TO_CHURRO = 95;
	public static final int RED_RAMP_TO_CHURRO = 500;

	//blocks opposite beacon
	//goes to block enemy autonomous for RED side
	public static final int RED_TURN_TO_OTHER_BEACON = 135;
	public static final int RED_BEACON_TO_BEACON = 3000;

	public AutoOpMode() {
		super();
	}

	public void initialize(String side) throws InterruptedException {
		gyroInit = "initialized";
        waitOneFullHardwareCycle();
        motorFL = hardwareMap.dcMotor.get("motorFL");
        motorFR = hardwareMap.dcMotor.get("motorFR");
        motorBL = hardwareMap.dcMotor.get("motorBL");
        motorBR = hardwareMap.dcMotor.get("motorBR");
        manipulator = hardwareMap.dcMotor.get("manipulator");
        climber = hardwareMap.servo.get("climber");
		boxBelt = hardwareMap.servo.get("boxBelt");
		ramp = hardwareMap.servo.get("ramp");
		drop = hardwareMap.servo.get("drop");
        colorSensorL = hardwareMap.colorSensor.get("colorSensorL");
        colorSensorR = hardwareMap.colorSensor.get("colorSensorR");
		switchL = hardwareMap.servo.get("switchL");
		switchR = hardwareMap.servo.get("switchR");
        telemetry.addData("gyro", "initializing");
		hitter = hardwareMap.servo.get("hitter");

        motorFL.setPower(0);
        motorFR.setPower(0);
        motorBL.setPower(0);
        motorBR.setPower(0);
        manipulator.setPower(0);
        climber.setPosition(1);
		hitter.setPosition(.5);
		boxBelt.setPosition(.5);
		switchL.setPosition(.5);
		switchR.setPosition(1);
		if (side.equals("blue")) {
			ramp.setPosition(0);
			drop.setPosition(1);
		} else {
			if (side.equals("red")) {
				ramp.setPosition(1);
				drop.setPosition(0);
			} else {
				telemetry.addData("Side", " invalid");
			}
		}

        try {
			hardwareMap.logDevices();

            gyroSensor = new AdafruitIMU(hardwareMap, "gyro"

                    //The following was required when the definition of the "I2cDevice" class was incomplete.
                    //, "cdim", 5

                    , (byte)(AdafruitIMU.BNO055_ADDRESS_A * 2)//By convention the FTC SDK always does 8-bit I2C bus
                    //addressing
                    , (byte) AdafruitIMU.OPERATION_MODE_IMU);
            waitOneFullHardwareCycle();
        } catch (RobotCoreException e){
            gyroInit = "failed";
        }
        BL = 0;
        BR = 0;
        avg = 0;
        telemetry.addData("gyro init", gyroInit);
		reset();
    }

    public void getLeftColor (){

        colorL[0] = colorSensorL.red();
        colorL[1] = colorSensorL.blue();
        colorL[2] = colorSensorL.green();
    }

    public void getRightColor (){
        colorR[0] = colorSensorR.red();
        colorR[1] = colorSensorR.blue();
        colorR[2] = colorSensorR.green();
    }

    public void moveForwardWithEncoders(double speed, double goal) throws InterruptedException {
		resetGyro();
		startMotors(speed, -speed);
		double angle;
		angle = yawAngle[0];
		double currAngle;
		while (avg < goal) {
//			showAngles();
			getAvg();
			waitOneFullHardwareCycle();
			getAngles();
			telemetry.addData("stuff: ", String.format("current:%.2f, target:%.2f", getYawAngle(), angle));
			if (getYawAngle() < -3) {
				startMotors(speed * .5, -speed);
				telemetry.addData("I'm inside", " the first");
			} else if (getYawAngle() > 1) {
				startMotors(speed, -speed * .5);
				telemetry.addData("I'm inside", " the second");
			} else {
				startMotors(speed, -speed);
			}
		}
		reset();
	}
    public void forwardWithManiWithEncoders(double speed, double goal) throws InterruptedException {
		resetGyro();
		startMotors(speed, -speed);
        manipulator.setPower(-1);
        double angle;
		angle = yawAngle[0];
		double currAngle;
        while (avg < goal) {
//			showAngles();
            getAvg();
            waitOneFullHardwareCycle();
            getAngles();
			telemetry.addData("stuff: ", String.format("current:%.2f, target:%.2f", getYawAngle(), angle));
            if (getYawAngle() < -3) {
                startMotors(speed * .5, -speed);
				telemetry.addData("I'm inside", " the first");
            } else if (getYawAngle() > 1) {
                    startMotors(speed, -speed * .5);
					telemetry.addData("I'm inside", " the second");
			} else {
                    startMotors(speed, -speed);
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
		resetGyro();
        getAngles();
		double newAngle;
		currAngle = yawAngle[0];
		newAngle = yawAngle[0];
		while (Math.abs(newAngle - currAngle) < angle - 2) {
			waitOneFullHardwareCycle();
			showAngles();
			startMotors(-speed / 6, -speed / 6);
			getAngles();
			newAngle = yawAngle[0];
			waitOneFullHardwareCycle();
        }
		startMotors(0, 0);
		while (Math.abs(newAngle - currAngle) > angle + 2) {
			waitOneFullHardwareCycle();
			showAngles();
			startMotors(speed / 6, speed / 6);
			getAngles();
			newAngle = yawAngle[0];
			waitOneFullHardwareCycle();
		}

		startMotors(0 , 0);
		getAngles();
		showAngles();
		reset();

    }

	public void loopTurnLeft(double speed, double angle) throws InterruptedException {
		double currAngle;
		resetGyro();
		getAngles();
		double newAngle;
		currAngle = yawAngle[0];
		newAngle = yawAngle[0];
		double power = speed;
		double error;
		while(Math.abs(newAngle - currAngle) < angle - 2) {
			error = (Math.abs(Math.abs(newAngle) - Math.abs(angle))) / 360;
			power = speed * error;
			telemetry.addData("power", power);
			waitOneFullHardwareCycle();
			showAngles();
			startMotors(-power, -power);
			getAngles();
			newAngle = yawAngle[0];
			waitOneFullHardwareCycle();
		}
		getAngles();
		showAngles();
		reset();
	}

	public void turnRight(double speed, double angle) throws InterruptedException {
		turnLeft(-speed, angle);
	}

	public void loopTurnRight(double speed, double angle) throws InterruptedException {
		loopTurnLeft(-speed, angle);
	}

	public void startMotors(double left, double right) throws InterruptedException {
		waitOneFullHardwareCycle();
        motorFL.setPower(left);
		waitOneFullHardwareCycle();
        motorFR.setPower(right);
		waitOneFullHardwareCycle();
        motorBL.setPower(left);
		waitOneFullHardwareCycle();
        motorBR.setPower(right);
		waitOneFullHardwareCycle();
    }

    //angle out of 180 degrees
    public void dropClimbers(double angle) throws InterruptedException {
        waitOneFullHardwareCycle();
		climber.setPosition(1 - (angle / 180));
		Thread.sleep(500);
		climber.setPosition(1);
		waitOneFullHardwareCycle();
	}

	public void getAngles() throws InterruptedException {
		waitOneFullHardwareCycle();
		gyroSensor.getIMUGyroAngles(rollAngle, pitchAngle, yawAngle);
        telemetry.addData("heading", yawAngle[0]);
		waitOneFullHardwareCycle();
    }

	public double getYawAngle() {
		gyroSensor.getIMUGyroAngles(rollAngle, pitchAngle, yawAngle);
		return yawAngle[0];
	}

    public void reset() throws InterruptedException {
		waitOneFullHardwareCycle();
        motorFR.setPower(0);
        motorFL.setPower(0);
        motorBR.setPower(0);
        motorBL.setPower(0);
        manipulator.setPower(0);
        avg = 0;
		waitOneFullHardwareCycle();
        motorBL.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
		motorBR.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        Thread.sleep(250);
		motorBL.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motorBR.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
		resetGyro();
    }

    public void getAvg() throws InterruptedException {
		waitOneFullHardwareCycle();
        BR = Math.abs(motorBR.getCurrentPosition());
        BL = Math.abs(motorBL.getCurrentPosition());
		avg = (BR + BL) / 2;
		showData();
		waitOneFullHardwareCycle();
    }

	public void showAngles() throws InterruptedException {
		waitOneFullHardwareCycle();
		gyroSensor.getIMUGyroAngles(rollAngle, pitchAngle, yawAngle);
		telemetry.addData("Headings(yaw): ",
				String.format("Euler= %4.5f, Quaternion calculated= %4.5f", yawAngle[0], yawAngle[1]));
		telemetry.addData("Pitches: ",
				String.format("Euler= %4.5f, Quaternion calculated= %4.5f", pitchAngle[0], pitchAngle[1]));
		telemetry.addData("Max I2C read interval: ",
				String.format("%4.4f ms. Average interval: %4.4f ms.", gyroSensor.maxReadInterval
                        , gyroSensor.avgReadInterval));
		waitOneFullHardwareCycle();
	}

	public void resetGyro() throws InterruptedException {
		waitOneFullHardwareCycle();
		gyroSensor.startIMU();
		waitOneFullHardwareCycle();
	}

    public void moveToLine (double speed) throws InterruptedException {
        getRightColor();
        while(colorR[0] <= 1500 && colorR[1] <= 2000 && colorR[2] <= 2000){
            getRightColor();
            motorFL.setPower(-speed);
            motorBL.setPower(-speed);
            motorFR.setPower(speed);
            motorBR.setPower(speed);
            manipulator.setPower(-1);
            waitOneFullHardwareCycle();
        }
        reset();
    }

	public void followToLineWithManipulatorIn(int speed) throws InterruptedException{
        manipulator.setPower(-1);
        while(yawAngle[0] <= CLIMBERS_ANGLE - 1 || yawAngle[0] >= CLIMBERS_ANGLE + 1){
            getRightColor();
            if (colorR[0] > 1500 && colorR[1] > 2000 && colorR[2] > 2000){
                motorFR.setPower(-speed);
                motorBR.setPower(-speed);
                motorFL.setPower(-speed);
                motorBL.setPower(-speed);
            }
            else{
                motorFR.setPower(-speed);
                motorBR.setPower(speed);
                motorFL.setPower(-speed);
				motorBL.setPower(speed);
            }
            waitOneFullHardwareCycle();
        }
        reset();
    }

    public void showData() throws InterruptedException {
		waitOneFullHardwareCycle();
		telemetry.addData("Headings(yaw): ",
				String.format("Euler= %4.5f, Quaternion calculated= %4.5f", yawAngle[0], yawAngle[1]));
		telemetry.addData("Pitches: ",
				String.format("Euler= %4.5f, Quaternion calculated= %4.5f", pitchAngle[0], pitchAngle[1]));
		telemetry.addData("Max I2C read interval: ",
				String.format("%4.4f ms. Average interval: %4.4f ms.", gyroSensor.maxReadInterval
						, gyroSensor.avgReadInterval));
    }
}