package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Vandegrift InvenTeam on 11/17/2015.
 */
public class TestClimber extends OpMode{

    double servoClimberPos;

    public Servo servoClimber;

    @Override
    public void init() {
        servoClimber = hardwareMap.servo.get(servoClimber);
    }

    @Override
    public void loop() {

    }
}
