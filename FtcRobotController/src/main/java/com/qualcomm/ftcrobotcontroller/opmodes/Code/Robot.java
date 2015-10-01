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

    void moveForward(){
        motorFR.setPower(1);
        motorFL.setPower(-1);
        motorBR.setPower(1);
        motorBL.setPower(-1);
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

    void moveRight(){
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
}
