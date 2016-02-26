package com.qualcomm.ftcrobotcontroller.opmodes.Autonomouses;

/**
 * Created by Steven on 2/25/2016.
 */
public class RedClimbersClear extends AutoOpMode {

	@Override
	public void runOpMode() throws InterruptedException {
		initialize();

		backwardsWithManiWithEncoders(1, RED_START_TO_WALL);
		turnLeft(.5, RED_TURN_TO_LINE);
		backwardsWithManiWithEncoders(1, RED_WALL_TO_LINE);
		turnRight(.5, RED_TURN_TO_BEACON);
		moveBackwardsWithEncoders(1, RED_LINE_TO_BEACON);
		dropClimbers(CLIMBERS_ANGLE);
		moveForwardWithEncoders(1, RED_BACK_AWAY_FROM_BEACON);
		turnLeft(.5, RED_TURN_TO_OTHER_SIDE);
		backwardsWithManiWithEncoders(1, RED_BEACON_TO_OTHER_SIDE);
		turnLeft(.5, RED_TURN_TO_RAMP_BASE);
		backwardsWithManiWithEncoders(1, RED_OTHER_SIDE_TO_CLEAR_RAMP_BASE);

	}
}
