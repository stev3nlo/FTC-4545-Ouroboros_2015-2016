package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by natalieroot on 3/28/16.
 */
public class justcause extends OpMode{
    public DcMotor motorR;
    public DcMotor motorL;
    private int direction = 1;
    private long currentTimeR = 0;
    private long lastTimeR = 0;
    private long DURATION = 1500000000;

    public void init() {
        motorR = hardwareMap.dcMotor.get("motorR");
        motorL = hardwareMap.dcMotor.get("motorL");
    }

    @Override
    public void loop() {
        if(gamepad1.a)
        {
            currentTimeR = System.nanoTime();
            if (currentTimeR > lastTimeR + DURATION) {
                direction *= -1;
            }
            lastTimeR = currentTimeR;
        }
        if(Math.abs(gamepad1.right_stick_y) > .05){
            motorR.setPower(gamepad1.right_stick_y * -1 * direction);
        }
        else {
            motorR.setPower(0);
        }
        if(Math.abs(gamepad1.left_stick_y) > .05){
            motorL.setPower(gamepad1.left_stick_y * direction);
        }
        else {
            motorL.setPower(0);
        }
    }

}
