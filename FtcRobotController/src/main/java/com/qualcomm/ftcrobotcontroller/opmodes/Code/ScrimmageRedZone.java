package com.qualcomm.ftcrobotcontroller.opmodes.Code;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import static java.lang.Math.abs;

/**
 * Created by Vandegrift InvenTeam on 11/2/2015.
 */
public class ScrimmageRedZone extends LinearOpMode{
    double eFL = 0.0;
    double eFR = 0;
    double eBL = 0;
    double eBR = 0;
    double changeFL = 0;
    double changeFR = 0;
    double changeBR = 0;
    double changeBL = 0;
    boolean running = true;
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
        // turns robot to the final direction
        while(running){

        }


    }
    public double[] getChange(){
        changeFL = motorFL.getCurrentPosition() - eFL;
        changeFR = motorFR.getCurrentPosition() - eFR;
        changeBR = motorBR.getCurrentPosition() - eBR;
        changeBL = motorBL.getCurrentPosition() - eBL;
        eFL = motorFL.getCurrentPosition();
        eFR = motorFR.getCurrentPosition();
        eBL = motorBL.getCurrentPosition();
        eBR = motorBR.getCurrentPosition();
        double[] change = {changeFL,changeFR,changeBL,changeBR};
        return change;
    }

    public void turn(){
        motorFL.setPower(1);
        motorBL.setPower(1);


    }
}
