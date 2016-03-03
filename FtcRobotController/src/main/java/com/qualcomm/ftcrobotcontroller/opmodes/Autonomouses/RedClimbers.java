package com.qualcomm.ftcrobotcontroller.opmodes.Autonomouses;

/**
 * Created by Steven on 2/25/2016.
 */
public class RedClimbers extends AutoOpMode {

	@Override
	public void runOpMode() throws InterruptedException {
		initialize("red");
		waitForStart();

//		backwardsWithManiWithEncoders(.75, RED_START_TO_WALL);
		turnRight(.5, RED_TURN_TO_LINE);
//		backwardsWithManiWithEncoders(1, RED_WALL_TO_LINE);
//		turnRight(.5, RED_TURN_TO_BEACON);
//		moveBackwardsWithEncoders(1, RED_LINE_TO_BEACON);
//		dropClimbers(CLIMBERS_ANGLE);
	}
}
