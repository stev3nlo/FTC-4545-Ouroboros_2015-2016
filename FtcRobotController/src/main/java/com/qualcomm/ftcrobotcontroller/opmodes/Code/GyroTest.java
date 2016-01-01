package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.GyroSensor;

/**
 * Created by Vandegrift InvenTeam on 12/14/2015.
 */
public class GyroTest extends LinearOpMode {

    GyroSensor gyro;
    DeviceInterfaceModule cdim;
    static final int GYRO_CHANNEL = 4;

    public void runOpMode() throws InterruptedException {

        cdim = hardwareMap.deviceInterfaceModule.get("cdim");
        gyro = hardwareMap.gyroSensor.get("man");
        waitOneFullHardwareCycle();
        waitForStart();
        double rotationValue;

        while (true) {

            rotationValue = gyro.getRotation();
            telemetry.addData("rotation", rotationValue);
            waitOneFullHardwareCycle();

        }
    }
}