package com.qualcomm.ftcrobotcontroller.opmodes.Autonomouses;

/**
 * Created by Steven on 3/10/2016.
 */
public class GyroStabilizationTest extends AutoOpMode {
	@Override
	public void runOpMode() throws InterruptedException {
		initialize("blue");
		waitForStart();
		backwardsWithManiWithEncoders(.4, 5000);
	}
}
