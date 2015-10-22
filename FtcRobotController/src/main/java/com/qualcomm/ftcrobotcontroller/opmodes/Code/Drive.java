package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import com.qualcomm.robotcore.hardware.*;

/**
 * Created by Vandegrift InvenTeam on 10/1/2015.
 */
public class Robot {



    public DcMotor motorFR;
    public DcMotor motorFL;
    public DcMotor motorBR;
    public DcMotor motorBL;

    public DcMotor motorHangR;
    public DcMotor motorHangL;

    public DcMotor motorSpinner;

    public void moveForward(){
        motorFR.setPower(1);
        motorFL.setPower(-1);
        motorBR.setPower(1);
        motorBL.setPower(-1);
    }

    void moveForward(double i){ //Moves Forward at a different speed than full power.
        motorFR.setPower(1 * i);
        motorFL.setPower(-1 * i);
        motorBR.setPower(1 * i);
        motorBL.setPower(-1 * i);
    }

    void moveStop(){
        motorFR.setPower(0);
        motorFL.setPower(0);
        motorBR.setPower(0);
        motorBL.setPower(0);
    }

    void moveLeft(){
        motorFR.setPower(1);
        motorFL.setPower(1);
        motorBR.setPower(1);
        motorBL.setPower(1);
    }

    void moveLeft(double i){ // Turns left at a different power besides 1
        motorFR.setPower(1*i);
        motorFL.setPower(1*i);
        motorBR.setPower(1*i);
        motorBL.setPower(1*i);
    }

    void moveRight(){
        motorFR.setPower(-1);
        motorFL.setPower(-1);
        motorBR.setPower(-1);
        motorBL.setPower(-1);
    }

    void moveRight(double i){ // Turns right at a different power besides 1
        motorFR.setPower(-1);
        motorFL.setPower(-1);
        motorBR.setPower(-1);
        motorBL.setPower(-1);
    }

    void moveBackward(){
        motorFR.setPower(-1);
        motorFL.setPower(1);
        motorBR.setPower(-1);
        motorBL.setPower(1);
    }

    void moveBackward(double i) { // Moves backwards at a different power besides 1
        motorFR.setPower(-1 * i);
        motorFL.setPower(1 * i);
        motorBR.setPower(-1 * i);
        motorBL.setPower(1 * i);
    }
}
