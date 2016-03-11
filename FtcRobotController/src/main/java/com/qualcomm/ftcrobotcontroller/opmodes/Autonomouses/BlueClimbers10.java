package com.qualcomm.ftcrobotcontroller.opmodes.Autonomouses;

/**
 * Created by Steven on 2/25/2016.
 */
public class BlueClimbers10 extends AutoOpMode {

	@Override
	public void runOpMode() throws InterruptedException {
		initialize("blue");
		waitForStart();

		Thread.sleep(10000);
		backwardsWithManiWithEncoders(.4, BLUE_START);
		loopTurnRight(.35, BLUE_TURN_TO_WALL);
		backwardsWithManiWithEncoders(1, BLUE_START_TO_WALL);
		loopTurnLeft(.5, BLUE_TURN_TO_LINE);
		backwardsWithManiWithEncoders(1, BLUE_WALL_TO_LINE);
		loopTurnRight(.5, BLUE_TURN_TO_BEACON);
		moveBackwardsWithEncoders(1, BLUE_LINE_TO_BEACON);
		dropClimbers(CLIMBERS_ANGLE);
	}
}
