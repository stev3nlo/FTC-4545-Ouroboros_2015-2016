package com.qualcomm.ftcrobotcontroller.opmodes.Code;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import static java.lang.Math.abs;

/**
 * Created by Vandegrift InvenTeam on 10/22/2015.
 */
public class TestRunnerWheels extends OpMode {
    public DcMotor motorFR;
    public DcMotor motorFL;
    public DcMotor motorBR;
    public DcMotor motorBL;


    @Override
    public void init() {

    }

    public void loop(){
        if(abs(gamepad1.right_stick_y) > .05){
            motorFR.setPower(gamepad1.right_stick_y);
            motorBR.setPower(gamepad1.right_stick_y);
        }
        if(abs(gamepad1.left_stick_y) > .05){
            motorFL.setPower(gamepad1.left_stick_y * -1);
            motorBR.setPower(gamepad1.left_stick_y * -1);
        }
    }

}

