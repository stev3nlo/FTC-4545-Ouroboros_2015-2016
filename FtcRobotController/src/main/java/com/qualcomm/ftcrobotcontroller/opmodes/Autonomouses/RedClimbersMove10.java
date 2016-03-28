package com.qualcomm.ftcrobotcontroller.opmodes.Autonomouses;

/**
 * Created by Steven on 2/25/2016.
 */
public class RedClimbersMove10 extends AutoOpMode {

	@Override
	public void runOpMode() throws InterruptedException {
		initialize("red");
		waitForStart();

		Thread.sleep(10000);
		backwardsWithManiWithEncoders(.4, RED_START);
		loopTurnLeft(.35, RED_TURN_TO_WALL);
		backwardsWithManiWithEncoders(.4, RED_START_TO_WALL);
		loopTurnRight(.35, RED_TURN_TO_LINE);
		backwardsWithManiWithEncoders(.4, RED_WALL_TO_LINE);
		loopTurnLeft(.4, RED_TURN_TO_BEACON);
		moveBackwardsWithEncoders(.4, RED_LINE_TO_BEACON);
		dropClimbers(CLIMBERS_ANGLE);
		forwardWithManiWithEncoders(.4, RED_BACK_AWAY_FROM_BEACON);
	}
}
