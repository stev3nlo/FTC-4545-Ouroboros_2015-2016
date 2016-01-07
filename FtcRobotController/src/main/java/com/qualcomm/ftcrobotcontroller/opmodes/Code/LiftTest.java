package com.qualcomm.ftcrobotcontroller.opmodes.Code;

        import com.qualcomm.robotcore.eventloop.opmode.OpMode;
        import com.qualcomm.robotcore.hardware.*;
        import com.qualcomm.robotcore.hardware.configuration.ServoConfiguration;
/**
 * Created by Steven on 1/5/2016.
 */
public class LiftTest extends OpMode {
    public DcMotor liftR;
    public DcMotor liftL;
    @Override
    public void init() {
        liftR = hardwareMap.dcMotor.get("liftR");
        liftL = hardwareMap.dcMotor.get("liftL");
        }

    @Override
    public void loop() {
        //test lift
        if(Math.abs(gamepad1.left_stick_y) > .1){
                liftR.setPower(gamepad1.left_stick_y);
                liftL.setPower(gamepad1.left_stick_y*-1);
        }
        else{
            liftR.setPower(0);
            liftL.setPower(0);
        }
    }
}
