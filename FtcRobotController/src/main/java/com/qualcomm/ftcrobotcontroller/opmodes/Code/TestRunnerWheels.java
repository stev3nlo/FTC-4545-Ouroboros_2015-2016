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
    boolean halfspeed;
    final double HALFSPEED = .3;


    @Override
    public void init() {
        motorFL = hardwareMap.dcMotor.get("motorFL");
        motorFR = hardwareMap.dcMotor.get("motorFR");
        motorBL = hardwareMap.dcMotor.get("motorBL");
        motorBR = hardwareMap.dcMotor.get("motorBR");
        halfspeed = false;
    }

    public void loop(){
        if (gamepad1.left_trigger == 1) {
//            if (halfspeed) {
//                halfspeed = false;
//            } else {
//                halfspeed = true;
//            }
//            if (halfspeed) {
//                if(abs(gamepad1.right_stick_y) > .05){
//                    motorFR.setPower(gamepad1.right_stick_y * -1 * HALFSPEED);
//                    motorBR.setPower(gamepad1.right_stick_y * HALFSPEED);
//                }
//                if(abs(gamepad1.left_stick_y) > .05){
//                    motorFL.setPower(gamepad1.left_stick_y * HALFSPEED);
//                    motorBL.setPower(gamepad1.left_stick_y * -1 * HALFSPEED);
//                }
//            }
        }
        else { //Regular tank controls
            if(abs(gamepad1.right_stick_y) > .05){
                motorFR.setPower(gamepad1.right_stick_y * -1);
                motorBR.setPower(gamepad1.right_stick_y);
            }
            if(abs(gamepad1.left_stick_y) > .05){
                motorFL.setPower(gamepad1.left_stick_y);
                motorBL.setPower(gamepad1.left_stick_y * -1 );
            }
            if (abs(gamepad1.right_stick_y) <= .05 && abs(gamepad1.left_stick_y) <= .05){
                motorFL.setPower(0);
                motorFR.setPower(0);
                motorBL.setPower(0);
                motorBR.setPower(0);
            }
        }
    }

}

