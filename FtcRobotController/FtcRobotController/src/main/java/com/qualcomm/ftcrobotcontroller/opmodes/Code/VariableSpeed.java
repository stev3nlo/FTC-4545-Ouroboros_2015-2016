package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import java.math.*;

import static java.lang.Math.abs;

/**
 * Created by User on 10/27/2015.
 */
public class VariableSpeed {
    public double newValue(double num){
        double power = .75*(Math.pow(num,2.0));
        if (power >= 1) {
            if (num > 0)return 1.0;
            else return -1.0;
        }
        else {
            if (num >=0)return power;
            else return power * -1;
        }
    }

}
