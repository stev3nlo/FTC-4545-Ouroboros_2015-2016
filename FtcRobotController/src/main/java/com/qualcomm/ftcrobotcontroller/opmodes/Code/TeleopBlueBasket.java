package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import com.qualcomm.ftcrobotcontroller.opmodes.AdafruitIMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.exception.RobotCoreException;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.hardware.configuration.ServoConfiguration;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Vandegrift InvenTeam on 1/21/2016.
 */
public class TeleopBlueBasket extends OpMode{
    public DcMotor motorFR;
    public DcMotor motorFL;
    public DcMotor motorBR;
    public DcMotor motorBL;
    public DcMotor motorHangL;
    public DcMotor motorHangR;
    public DcMotor motorSpinner;
    public DcMotor motorWinch;
    boolean halfspeed;
    int direction;
    final double HALFSPEED = .3;
    final int FULLSPEED = 1;
    public Servo switchL;
    public Servo switchR;
    public Servo climber;
    final long DURATION = 1500000000;
    public Servo boxBelt;
    public Servo attachL;
    public Servo hitter;
    long currentTimeH = 0;
    long lastTimeH = 0;
    public Servo attachR;
    long currentTimeR = 0;
    long lastTimeR = 0;
    double hitterP = 0;
    double speed = 0;
    public Servo ramp;
	public Servo drop;
	volatile double[] rollAngle = new double[2], pitchAngle = new double[2], yawAngle = new double[2];

	@Override
    public void init() {
		motorFL = hardwareMap.dcMotor.get("motorFL");
		motorFR = hardwareMap.dcMotor.get("motorFR");
		motorBL = hardwareMap.dcMotor.get("motorBL");
		motorBR = hardwareMap.dcMotor.get("motorBR");
		switchL = hardwareMap.servo.get("switchL");
		switchR = hardwareMap.servo.get("switchR");
		motorHangL = hardwareMap.dcMotor.get("liftL");
		motorHangR = hardwareMap.dcMotor.get("liftR");
		motorSpinner = hardwareMap.dcMotor.get("manipulator");
		motorWinch = hardwareMap.dcMotor.get("motorWinch");
		climber = hardwareMap.servo.get("climber");
		boxBelt = hardwareMap.servo.get("boxBelt");
		attachR = hardwareMap.servo.get("attachR");
		attachL = hardwareMap.servo.get("attachL");
		ramp = hardwareMap.servo.get("ramp");
		drop = hardwareMap.servo.get("drop");
        hitter = hardwareMap.servo.get("hitter");
        switchL.setPosition(.5);
        switchR.setPosition(1);
		halfspeed = false;
		boxBelt.setPosition(.5);
		climber.setPosition(1);
		direction = 1;
		ramp.setPosition(0);
		drop.setPosition(1);
        hitter.setPosition(.5);
	}

    public void loop() {
        if (gamepad1.a) {
            if (gamepad1.a) {
                currentTimeH = System.nanoTime();
                // are we waiting?
                if (currentTimeH > lastTimeH + DURATION) {
                    if (halfspeed) {
                        halfspeed = false;
                    } else {
                        halfspeed = true;
                    }
                    lastTimeH = currentTimeH;
                }
            }
        }
        if (gamepad1.b) {
            currentTimeR = System.nanoTime();
            if (currentTimeR > lastTimeR + DURATION) {
                direction *= -1;
            }
            lastTimeR = currentTimeR;
        }
        //if statement for deciding speed multiplier
        speed = (halfspeed) ? HALFSPEED : FULLSPEED; //speed = halfspeed
        //WHEELS: toogle between halfspeed, reverse, reverse halfspeed, and normal
        if (Math.abs(gamepad1.right_stick_y) > .05) {
            motorFR.setPower(gamepad1.right_stick_y * speed * direction);
            motorBR.setPower(gamepad1.right_stick_y * speed * direction);
        } else {
            motorFR.setPower(0);
            motorBR.setPower(0);
        }
        if (Math.abs(gamepad1.left_stick_y) > .05) {
            motorFL.setPower(gamepad1.left_stick_y * speed * direction * -1);
            motorBL.setPower(gamepad1.left_stick_y * speed * direction * -1);
        } else {
            motorFL.setPower(0);
            motorBL.setPower(0);
        }
        //servo for flipping switches
        if (gamepad1.left_bumper) {
            switchL.setPosition(1);
        } else {
            switchL.setPosition(.5);
        }
        if (gamepad1.right_bumper) {
            switchR.setPosition(.5);
        } else {
            switchR.setPosition(1);
        }
        //manipulator
        if (Math.abs(gamepad2.left_stick_y) > .1) {
            motorSpinner.setPower(gamepad2.left_stick_y * -1);
        } else {
            motorSpinner.setPower(0);
        }
        //lift
        if (Math.abs(gamepad2.right_stick_y) > .1) {
            motorHangL.setPower(gamepad2.right_stick_y);
            motorHangR.setPower(gamepad2.right_stick_y * -1);
        } else {
            motorHangL.setPower(0);
            motorHangR.setPower(0);
        }
        //servo climber switch
        if (gamepad1.x) {
            climber.setPosition(0);
        } else {
            climber.setPosition(1);
        }
        //triggers control boxBelt
        if (gamepad2.right_trigger > .25) {
            boxBelt.setPosition(0);
        } else if (gamepad2.left_trigger > .25) {
            boxBelt.setPosition(1);
        } else {
            boxBelt.setPosition(.5);
        }
        //lock on servos
        //x is lock on
        if (gamepad1.right_trigger > .5) {
            attachR.setPosition(.25);
            attachL.setPosition(.95);
        }
        //y is lock off
        else if (gamepad1.left_trigger > .5) {
            attachR.setPosition(.85);
            attachL.setPosition(.35);
        }
        //Winch
        if(gamepad2.dpad_up) {
            motorWinch.setPower(1);

        }
        else if(gamepad2.dpad_down){
            motorWinch.setPower(-1);
        }
        else{
            motorWinch.setPower(0);
        }
        if(gamepad2.y) {
            ramp.setPosition(0);
        } // Ramp wall falls forward to push debris outwards
        if(gamepad2.a) {
            ramp.setPosition(.45);
            //Ramp resets
        }
        if(gamepad2.b) {
            drop.setPosition(.60);
            //Door drops
        }
        if(gamepad2.x) {
            drop.setPosition(1);
            //Door resets
        } //Climber Hitter
        if(gamepad1.dpad_left) {
            hitterP += .01;
            hitter.setPosition(hitterP);
        }
        if(gamepad1.dpad_right) {
            hitterP -= .01;
            hitter.setPosition(hitterP);
        }
    }
}