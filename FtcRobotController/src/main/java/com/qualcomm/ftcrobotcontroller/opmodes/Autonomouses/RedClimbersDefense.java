package com.qualcomm.ftcrobotcontroller.opmodes.Autonomouses;

/**
 * Created by Steven on 2/26/2016.
 */
public class RedClimbersDefense extends AutoOpMode {

	@Override
	public void runOpMode() throws InterruptedException {
		initialize("red");
		waitForStart();

		backwardsWithManiWithEncoders(1, RED_START_TO_WALL);
		turnLeft(.5, RED_TURN_TO_LINE);
		backwardsWithManiWithEncoders(1, RED_WALL_TO_LINE);
		turnRight(.5, RED_TURN_TO_BEACON);
		moveBackwardsWithEncoders(1, RED_LINE_TO_BEACON);
		dropClimbers(CLIMBERS_ANGLE);
		moveForwardWithEncoders(1, RED_BACK_AWAY_FROM_BEACON);
		turnLeft(.5, RED_TURN_TO_OTHER_BEACON);
		backwardsWithManiWithEncoders(1, RED_BEACON_TO_BEACON);
	}
}
