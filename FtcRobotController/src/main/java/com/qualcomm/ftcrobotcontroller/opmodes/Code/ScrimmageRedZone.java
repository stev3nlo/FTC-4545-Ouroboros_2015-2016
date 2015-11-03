package com.qualcomm.ftcrobotcontroller.opmodes.Code;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import static java.lang.Math.abs;

/**
 * Created by Vandegrift InvenTeam on 11/2/2015.
 */
public class ScrimmageRedZone extends LinearOpMode{
    public DcMotor motorFR;
    public DcMotor motorFL;
    public DcMotor motorBR;
    public DcMotor motorBL;
    @Override
    public void runOpMode() throws InterruptedException {
        motorFL = hardwareMap.dcMotor.get("motorFL");
        motorFR = hardwareMap.dcMotor.get("motorFR");
        motorBL = hardwareMap.dcMotor.get("motorBL");
        motorBR = hardwareMap.dcMotor.get("motorBR");
        waitForStart();


    }
}
