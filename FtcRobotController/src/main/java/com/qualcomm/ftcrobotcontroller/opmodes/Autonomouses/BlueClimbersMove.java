package com.qualcomm.ftcrobotcontroller.opmodes.Autonomouses;

/**
 * Created by Steven on 2/25/20.46.
 */
public class BlueClimbersMove extends AutoOpMode {

	@Override
	public void runOpMode() throws InterruptedException {
		initialize("blue");
		waitForStart();

		backwardsWithManiWithEncoders(.4, BLUE_START);
		loopTurnRight(.35, BLUE_TURN_TO_WALL);
		backwardsWithManiWithEncoders(.4, BLUE_START_TO_WALL);
		loopTurnLeft(.35, BLUE_TURN_TO_LINE);
		backwardsWithManiWithEncoders(.4, BLUE_WALL_TO_LINE);
		loopTurnRight(.4, BLUE_TURN_TO_BEACON);
		moveBackwardsWithEncoders(1, BLUE_LINE_TO_BEACON);
		dropClimbers(CLIMBERS_ANGLE);
		forwardWithManiWithEncoders(.4, BLUE_BACK_AWAY_FROM_BEACON);
	}
}
