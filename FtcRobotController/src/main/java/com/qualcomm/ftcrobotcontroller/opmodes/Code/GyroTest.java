package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import com.qualcomm.robotcore.hardware.GyroSensor;

/**
 * Created by kunalgupta on 11/14/15.
 */


public abstract class GyroTest extends java.lang.Object {

    public GyroSensor gyro;

    abstract double getRotation();

}
