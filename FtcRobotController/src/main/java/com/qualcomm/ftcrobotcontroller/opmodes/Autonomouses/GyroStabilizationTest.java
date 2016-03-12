package com.qualcomm.ftcrobotcontroller.opmodes.Autonomouses;

/**
 * Created by Steven on 3/10/2016.
 */
public class GyroStabilizationTest extends AutoOpMode {
	@Override
	public void runOpMode() throws InterruptedException {
		initialize("blue");
		waitForStart();
		Thread.sleep(10000);
		backwardsWithManiWithEncoders(.4, 2000);
	}
}
