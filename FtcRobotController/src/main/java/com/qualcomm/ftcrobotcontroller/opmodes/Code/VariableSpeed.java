package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import java.math.*;

import static java.lang.Math.abs;

/**
 * Created by User on 10/27/2015.
 */
public class VariableSpeed {
    public double newValue(double num){
        BigDecimal a = new BigDecimal("0.000000000000000000000007626656");
        BigDecimal b = new BigDecimal("0.000000000000000000272080701657");
        BigDecimal c = new BigDecimal("0.000000000000003715117053405310");
        BigDecimal d = new BigDecimal("0.000000000024215489818429200000");
        BigDecimal e = new BigDecimal("0.000000077305839163423100000000");
        BigDecimal f = new BigDecimal("0.000102195266110368000000000000");
        BigDecimal g = new BigDecimal("0.079417125827859500000000000000");
        BigDecimal h = new BigDecimal("" + Math.pow(num,6));
        BigDecimal i = new BigDecimal("" + Math.pow(num,5));
        BigDecimal j = new BigDecimal("" + Math.pow(num,4));
        BigDecimal k = new BigDecimal("" + Math.pow(num,3));
        BigDecimal l = new BigDecimal("" + Math.pow(num,2));
        BigDecimal m = new BigDecimal("" + num);
        BigDecimal ans =  a.multiply(h).subtract(b.multiply(i));
        ans = ans.add(c.multiply(j));
        ans = ans.subtract(d.multiply(k));
        ans = ans.add(e.multiply(l));
        ans = ans.subtract(f.multiply(m));
        ans = ans.add(g);

        double power = ans.doubleValue();

        return power;
    }

}
