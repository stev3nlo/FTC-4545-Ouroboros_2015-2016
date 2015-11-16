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

            BNO055_EULER_H_LSB_ADDR                                 = 0X1A,
            BNO055_EULER_H_MSB_ADDR                                 = 0X1B,
            BNO055_EULER_R_LSB_ADDR                                 = 0X1C,
            BNO055_EULER_R_MSB_ADDR                                 = 0X1D,
            BNO055_EULER_P_LSB_ADDR                                 = 0X1E,
            BNO055_EULER_P_MSB_ADDR                                 = 0X1F,

            BNO055_QUATERNION_DATA_W_LSB_ADDR                       = 0X20,
            BNO055_QUATERNION_DATA_W_MSB_ADDR                       = 0X21,
            BNO055_QUATERNION_DATA_X_LSB_ADDR                       = 0X22,
            BNO055_QUATERNION_DATA_X_MSB_ADDR                       = 0X23,
            BNO055_QUATERNION_DATA_Y_LSB_ADDR                       = 0X24,
            BNO055_QUATERNION_DATA_Y_MSB_ADDR                       = 0X25,
            BNO055_QUATERNION_DATA_Z_LSB_ADDR                       = 0X26,
            BNO055_QUATERNION_DATA_Z_MSB_ADDR                       = 0X27,

            BNO055_LINEAR_ACCEL_DATA_X_LSB_ADDR                     = 0X28,
            BNO055_LINEAR_ACCEL_DATA_X_MSB_ADDR                     = 0X29,
            BNO055_LINEAR_ACCEL_DATA_Y_LSB_ADDR                     = 0X2A,
            BNO055_LINEAR_ACCEL_DATA_Y_MSB_ADDR                     = 0X2B,
            BNO055_LINEAR_ACCEL_DATA_Z_LSB_ADDR                     = 0X2C,
            BNO055_LINEAR_ACCEL_DATA_Z_MSB_ADDR                     = 0X2D,

            BNO055_GRAVITY_DATA_X_LSB_ADDR                          = 0X2E,
            BNO055_GRAVITY_DATA_X_MSB_ADDR                          = 0X2F,
            BNO055_GRAVITY_DATA_Y_LSB_ADDR                          = 0X30,
            BNO055_GRAVITY_DATA_Y_MSB_ADDR                          = 0X31,
            BNO055_GRAVITY_DATA_Z_LSB_ADDR                          = 0X32,
            BNO055_GRAVITY_DATA_Z_MSB_ADDR                          = 0X33,

            BNO055_TEMP_ADDR                                        = 0X34,

            BNO055_CALIB_STAT_ADDR                                  = 0X35,
            BNO055_SELFTEST_RESULT_ADDR                             = 0X36,
            BNO055_INTR_STAT_ADDR                                   = 0X37,

            BNO055_SYS_CLK_STAT_ADDR                                = 0X38,
            BNO055_SYS_STAT_ADDR                                    = 0X39,
            BNO055_SYS_ERR_ADDR                                     = 0X3A,

            BNO055_UNIT_SEL_ADDR                                    = 0X3B,
            BNO055_DATA_SELECT_ADDR                                 = 0X3C,

            BNO055_OPR_MODE_ADDR                                    = 0X3D,
            BNO055_PWR_MODE_ADDR                                    = 0X3E,

            BNO055_SYS_TRIGGER_ADDR                                 = 0X3F,
            BNO055_TEMP_SOURCE_ADDR                                 = 0X40,

            BNO055_AXIS_MAP_CONFIG_ADDR                             = 0X41,
            BNO055_AXIS_MAP_SIGN_ADDR                               = 0X42,

            BNO055_SIC_MATRIX_0_LSB_ADDR                            = 0X43,
            BNO055_SIC_MATRIX_0_MSB_ADDR                            = 0X44,
            BNO055_SIC_MATRIX_1_LSB_ADDR                            = 0X45,
            BNO055_SIC_MATRIX_1_MSB_ADDR                            = 0X46,
            BNO055_SIC_MATRIX_2_LSB_ADDR                            = 0X47,
            BNO055_SIC_MATRIX_2_MSB_ADDR                            = 0X48,
            BNO055_SIC_MATRIX_3_LSB_ADDR                            = 0X49,
            BNO055_SIC_MATRIX_3_MSB_ADDR                            = 0X4A,
            BNO055_SIC_MATRIX_4_LSB_ADDR                            = 0X4B,
            BNO055_SIC_MATRIX_4_MSB_ADDR                            = 0X4C,
            BNO055_SIC_MATRIX_5_LSB_ADDR                            = 0X4D,
            BNO055_SIC_MATRIX_5_MSB_ADDR                            = 0X4E,
            BNO055_SIC_MATRIX_6_LSB_ADDR                            = 0X4F,
            BNO055_SIC_MATRIX_6_MSB_ADDR                            = 0X50,
            BNO055_SIC_MATRIX_7_LSB_ADDR                            = 0X51,
            BNO055_SIC_MATRIX_7_MSB_ADDR                            = 0X52,
            BNO055_SIC_MATRIX_8_LSB_ADDR                            = 0X53,
            BNO055_SIC_MATRIX_8_MSB_ADDR                            = 0X54,

            ACCEL_OFFSET_X_LSB_ADDR                                 = 0X55,
            ACCEL_OFFSET_X_MSB_ADDR                                 = 0X56,
            ACCEL_OFFSET_Y_LSB_ADDR                                 = 0X57,
            ACCEL_OFFSET_Y_MSB_ADDR                                 = 0X58,
            ACCEL_OFFSET_Z_LSB_ADDR                                 = 0X59,
            ACCEL_OFFSET_Z_MSB_ADDR                                 = 0X5A,

            MAG_OFFSET_X_LSB_ADDR                                   = 0X5B,
            MAG_OFFSET_X_MSB_ADDR                                   = 0X5C,
            MAG_OFFSET_Y_LSB_ADDR                                   = 0X5D,
            MAG_OFFSET_Y_MSB_ADDR                                   = 0X5E,
            MAG_OFFSET_Z_LSB_ADDR                                   = 0X5F,
            MAG_OFFSET_Z_MSB_ADDR                                   = 0X60,

            GYRO_OFFSET_X_LSB_ADDR                                  = 0X61,
            GYRO_OFFSET_X_MSB_ADDR                                  = 0X62,
            GYRO_OFFSET_Y_LSB_ADDR                                  = 0X63,
            GYRO_OFFSET_Y_MSB_ADDR                                  = 0X64,
            GYRO_OFFSET_Z_LSB_ADDR                                  = 0X65,
            GYRO_OFFSET_Z_MSB_ADDR                                  = 0X66,

            ACCEL_RADIUS_LSB_ADDR                                   = 0X67,
            ACCEL_RADIUS_MSB_ADDR                                   = 0X68,
            MAG_RADIUS_LSB_ADDR                                     = 0X69,
            MAG_RADIUS_MSB_ADDR                                     = 0X6A;

    public static final int
            POWER_MODE_NORMAL                                       = 0X00,
            POWER_MODE_LOWPOWER                                     = 0X01,
            POWER_MODE_SUSPEND                                      = 0X02;

    public static final int
            OPERATION_MODE_CONFIG                                   = 0X00,
            OPERATION_MODE_ACCONLY                                  = 0X01,
            OPERATION_MODE_MAGONLY                                  = 0X02,
            OPERATION_MODE_GYRONLY                                  = 0X03,
            OPERATION_MODE_ACCMAG                                   = 0X04,
            OPERATION_MODE_ACCGYRO                                  = 0X05,
            OPERATION_MODE_MAGGYRO                                  = 0X06,
            OPERATION_MODE_AMG                                      = 0X07,
            OPERATION_MODE_IMU                                      = 0X08,
            OPERATION_MODE_IMUPLUS                                  = 0X08,
            OPERATION_MODE_COMPASS                                  = 0X09,
            OPERATION_MODE_M4G                                      = 0X0A,
            OPERATION_MODE_NDOF_FMC_OFF                             = 0X0B,
            OPERATION_MODE_NDOF                                     = 0X0C;

    private final int i2cBufferSize = 26;

    private final I2cDevice i2cIMU;

    private final int baseI2Caddress;

    private int operationalMode;

    private final byte[] i2cReadCache;

    private final byte[] i2cWriteCache;

    private final Lock i2cReadCacheLock;

    private final Lock i2cWriteCacheLock;

    private boolean offsetsInitialized;

    private double[] quaternionVector = new double[5];

    private double[] rollOffset = new double[2], pitchOffset = new double[2], yawOffset = new double[2];

    private int numberOfRegisters = 20;

    private int registerStartAddress = BNO055_EULER_H_LSB_ADDR;

    private int readCacheOffset = BNO055_EULER_H_LSB_ADDR - I2cController.I2C_BUFFER_START_ADDRESS;

    public long totalI2Creads;

    public double maxReadInterval;

    public double avgReadInterval;

    private long readStartTime;

    private void snooze(long milliSecs) {

        try {
            Thread.sleep(milliSecs);
        } catch (InterruptedException e){}

    }

    public AdafruitIMU(HardwareMap currentHWmap, String configuredIMUname,
                       byte baseAddress, byte operMode) throws RobotCoreException {
        boolean okSoFar = true;
        long calibrationStartTime = 0L;
        byte[] outboundBytes = new byte[i2cBufferSize];
        i2cIMU = currentHWmap.i2cDevice.get(configuredIMUname);

        baseI2Caddress = (int)baseAddress & 0XFF;
        operationalMode = (int)operMode & 0XFF;
        i2cReadCache = i2cIMU.getI2cReadCache();
        i2cReadCacheLock = i2cIMU.getI2cReadCacheLock();
        i2cWriteCache = i2cIMU.getI2cWriteCache();
        i2cWriteCacheLock = i2cIMU.getI2cWriteCacheLock();

        Log.i("FtcRobotController", "Preparing to reset IMU to its power-on state......");

        outboundBytes[0] = 0x00;
        okSoFar &= i2cWriteImmediately(outboundBytes, 1, BNO055_PAGE_ID_ADDR);

        if (!okSoFar) {

            throw new RobotCoreException("IMU PAGE_ID setting interrupted or I2C bus \"stuck busy\".");

        }
        if (okSoFar) {

            outboundBytes[0] = (byte)0XE1;

            Log.i("FtcRobotController", String.format("SYS_TRIGGER = 0X%02X. Resetting IMU........"
                    ,outboundBytes[0] ));

            okSoFar &= i2cWriteImmediately(outboundBytes, 1, BNO055_SYS_TRIGGER_ADDR);
            snooze(500);

            if (!okSoFar){
                throw new RobotCoreException("IMU reset interrupted or I2C bus \"stuck busy\".");
            }

        }

        

    }


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
