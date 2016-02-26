package com.qualcomm.ftcrobotcontroller.opmodes.Autonomouses;

/**
 * Created by Steven on 2/25/2016.
 */
public class BlueClimbers extends AutoOpMode {

	@Override
	public void runOpMode() throws InterruptedException {
		initialize();

		backwardsWithManiWithEncoders(1, BLUE_START_TO_WALL);
		turnLeft(.5, BLUE_TURN_TO_LINE);
		backwardsWithManiWithEncoders(1, BLUE_WALL_TO_LINE);
		turnRight(.5, BLUE_TURN_TO_BEACON);
		moveBackwardsWithEncoders(1, BLUE_LINE_TO_BEACON);
		dropClimbers(CLIMBERS_ANGLE);
	}
}
