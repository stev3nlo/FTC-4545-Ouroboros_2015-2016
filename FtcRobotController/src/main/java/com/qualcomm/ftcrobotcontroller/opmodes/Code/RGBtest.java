package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.DigitalChannelController;
import com.qualcomm.robotcore.hardware.LED;
//import com.qualcomm.ftcRobotcontroller.R;
//import com.qualcomm.Robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.Robotcore.hardware.ColorSensor;
//import com.qualcomm.Robotcore.hardware.DeviceInterfaceModule;
//import com.qualcomm.Robotcore.hardware.DigitalChannelController;


/**
 * Created by Vandegrift InvenTeam on 11/3/2015.
 */
public class RGBtest extends LinearOpMode{

    ColorSensor colorSensor;
    DeviceInterfaceModule cdim;
    static final int LED_CHANNEL = 5;

    @Override
    public void runOpMode() throws InterruptedException {

        boolean bEnabled = false;
        boolean bPrevState = false;
        boolean bCurrState = false;

        float hsvValues[] = {0F, 0F, 0F};
        final float values[] = hsvValues;

        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(R.id.RelativeLayout);

        cdim = hardwareMap.deviceInterfaceModule.get("dim");

        cdim.setDigitalChannelMode(LED_CHANNEL, DigitalChannelController.Mode.OUTPUT);

        colorSensor = hardwareMap.colorSensor.get("lady");

        cdim.setDigitalChannelState(LED_CHANNEL, bEnabled);

        while (opModeIsActive()) {
            bCurrState = gamepad1.x || gamepad2.x;

            if (bCurrState == true && bCurrState != bPrevState) {

                DbgLog.msg("MY_DEBUG - x button was pressed! ");

                bPrevState = bCurrState;

                bEnabled = false;

                cdim.setDigitalChannelState(LED_CHANNEL, bEnabled);
            } else if (bCurrState == false && bCurrState != bPrevState) {

                DbgLog.msg("MY_DEBUG - x button was released! ");

<<<<<<< HEAD
            DbgLog.msg("MY_DEBUG - x button was released! Kunal is gay");
=======
                bPrevState = bCurrState;
>>>>>>> origin/master

                bEnabled = false;

                cdim.setDigitalChannelState(LED_CHANNEL, bEnabled);
            }

            Color.RGBToHSV((colorSensor.red() * 255) / 800, (colorSensor.green() * 255) / 800, (colorSensor.blue() * 255) / 800, hsvValues);

            telemetry.addData("Clear", colorSensor.alpha());
            telemetry.addData("Red  ", colorSensor.red());
            telemetry.addData("Green", colorSensor.green());
            telemetry.addData("Blue ", colorSensor.blue());
            telemetry.addData("Hue", hsvValues[0]);

<<<<<<< HEAD
        telemetry.addData("Clear", colorSensor.alpha());
        telemetry.addData("Red  ", colorSensor.red());
        telemetry.addData("Green", colorSensor.green());
        telemetry.addData("Blue ", colorSensor.blue());
        telemetry.addData("Hue", hsvValues[0]);
=======
            relativeLayout.post(new Runnable() {
                    public void run() {
                        relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, values));
                    }
            });
>>>>>>> origin/master

            waitOneFullHardwareCycle();
        }
    }

    public void waitOneFullHardwareCycle() throws InterruptedException {
        this.waitForNextHardwareCycle();
        Thread.sleep(1L);
        this.waitForNextHardwareCycle();
    }
}

