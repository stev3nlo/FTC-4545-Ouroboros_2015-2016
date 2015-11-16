package com.qualcomm.ftcrobotcontroller.opmodes.Code;

import android.util.Log;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.exception.RobotCoreException;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cController;
import com.qualcomm.robotcore.hardware.I2cDevice;

import java.util.concurrent.locks.Lock;

/**
 * Created by kunalgupta on 11/14/15.
 */


public class GyroTest implements HardwareDevice, I2cController.I2cPortReadyCallback {

    public static final int BNO055_ADDRESS_A = 0x28;
    public static final int BNO055_ADDRESS_B = 0x29;
    public static final int BNO055_ID        = 0xA0;
    public static final int
            BNO055_PAGE_ID_ADDR                                     = 0X07,

            BNO055_CHIP_ID_ADDR                                     = 0x00,
            BNO055_ACCEL_REV_ID_ADDR                                = 0x01,
            BNO055_MAG_REV_ID_ADDR                                  = 0x02,
            BNO055_GYRO_REV_ID_ADDR                                 = 0x03,
            BNO055_SW_REV_ID_LSB_ADDR                               = 0x04,
            BNO055_SW_REV_ID_MSB_ADDR                               = 0x05,
            BNO055_BL_REV_ID_ADDR                                   = 0X06,

            BNO055_MAG_DATA_X_LSB_ADDR                              = 0X0E,
            BNO055_MAG_DATA_X_MSB_ADDR                              = 0X0F,
            BNO055_MAG_DATA_Y_LSB_ADDR                              = 0X10,
            BNO055_MAG_DATA_Y_MSB_ADDR                              = 0X11,
            BNO055_MAG_DATA_Z_LSB_ADDR                              = 0X12,
            BNO055_MAG_DATA_Z_MSB_ADDR                              = 0X13,

            BNO055_GYRO_DATA_X_LSB_ADDR                             = 0X14,
            BNO055_GYRO_DATA_X_MSB_ADDR                             = 0X15,
            BNO055_GYRO_DATA_Y_LSB_ADDR                             = 0X16,
            BNO055_GYRO_DATA_Y_MSB_ADDR                             = 0X17,
            BNO055_GYRO_DATA_Z_LSB_ADDR                             = 0X18,
            BNO055_GYRO_DATA_Z_MSB_ADDR                             = 0X19,




    @Override
    public String getDeviceName() {
        return null;
    }

    @Override
    public String getConnectionInfo() {
        return null;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public void close() {

    }

    @Override
    public void portIsReady(int i) {

    }
}
