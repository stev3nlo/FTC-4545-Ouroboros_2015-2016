package com.qualcomm.ftcrobotcontroller.opmodes.Autonomouses;

/**
 * Created by Steven on 2/25/2016.
 */
public class RedClimbers10 extends AutoOpMode {

	@Override
	public void runOpMode() throws InterruptedException {
		initialize("red");
		waitForStart();

		Thread.sleep(10000);
		backwardsWithManiWithEncoders(.4, RED_START);
		loopTurnLeft(.35, RED_TURN_TO_WALL);
		backwardsWithManiWithEncoders(1, RED_START_TO_WALL);
		loopTurnLeft(.5, RED_TURN_TO_LINE);
		backwardsWithManiWithEncoders(1, RED_WALL_TO_LINE);
		loopTurnRight(.5, RED_TURN_TO_BEACON);
		moveBackwardsWithEncoders(1, RED_LINE_TO_BEACON);
		dropClimbers(CLIMBERS_ANGLE);
	}
}
