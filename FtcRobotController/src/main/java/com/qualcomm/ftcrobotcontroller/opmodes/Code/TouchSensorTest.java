

package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by Vandegrift InvenTeam on 1/28/2016.
 */
public class TouchSensorTest extends OpMode{
    TouchSensor touch;


    @Override
    public void init() {
    touch = hardwareMap.touchSensor.get("touchSensor");
    }

    @Override
    public void loop() {
        if(touch.isPressed() == true){
            telemetry.addData("on",touch.isPressed());
        }
    }
}
