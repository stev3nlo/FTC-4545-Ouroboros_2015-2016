
/* Copyright (c) 2014, 2015 Qualcomm Technologies Inc

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Qualcomm Technologies Inc nor the names of its contributors
may be used to endorse or promote products derived from this software without
specific prior written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. */

package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.opmodes.Autonomouses.BlueClimbers;
import com.qualcomm.ftcrobotcontroller.opmodes.Autonomouses.BlueClimbers10;
import com.qualcomm.ftcrobotcontroller.opmodes.Autonomouses.BlueClimbersClear;
import com.qualcomm.ftcrobotcontroller.opmodes.Autonomouses.BlueClimbersClear10;
import com.qualcomm.ftcrobotcontroller.opmodes.Autonomouses.BlueClimbersMove;
import com.qualcomm.ftcrobotcontroller.opmodes.Autonomouses.BlueClimbersMove10;
import com.qualcomm.ftcrobotcontroller.opmodes.Autonomouses.BlueClimbersSwitches;
import com.qualcomm.ftcrobotcontroller.opmodes.Autonomouses.BlueClimbersSwitches10;
import com.qualcomm.ftcrobotcontroller.opmodes.Autonomouses.RedClimbers;
import com.qualcomm.ftcrobotcontroller.opmodes.Autonomouses.RedClimbers10;
import com.qualcomm.ftcrobotcontroller.opmodes.Autonomouses.RedClimbersClear;
import com.qualcomm.ftcrobotcontroller.opmodes.Autonomouses.RedClimbersClear10;
import com.qualcomm.ftcrobotcontroller.opmodes.Autonomouses.RedClimbersMove;
import com.qualcomm.ftcrobotcontroller.opmodes.Autonomouses.RedClimbersMove10;
import com.qualcomm.ftcrobotcontroller.opmodes.Autonomouses.RedClimbersSwitches;
import com.qualcomm.ftcrobotcontroller.opmodes.Autonomouses.RedClimbersSwitches10;
import com.qualcomm.ftcrobotcontroller.opmodes.Code.AutonomousBlue_v2;
import com.qualcomm.ftcrobotcontroller.opmodes.Code.AutonomousRed_v2;
import com.qualcomm.ftcrobotcontroller.opmodes.Code.IMUGyroOnlyTest;
import com.qualcomm.ftcrobotcontroller.opmodes.Code.RGBTeleop;
import com.qualcomm.ftcrobotcontroller.opmodes.Code.RGBtest;
import com.qualcomm.ftcrobotcontroller.opmodes.Code.TeleopBlueBasket;
import com.qualcomm.ftcrobotcontroller.opmodes.Code.TeleopRedBasket;
import com.qualcomm.ftcrobotcontroller.opmodes.Code.TeleopRedMabey;
import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegister;
import com.qualcomm.ftcrobotcontroller.opmodes.Code.Test;

/**
 * Register Op Modes
 */
public class FtcOpModeRegister implements OpModeRegister {

  /**
   * The Op Mode Manager will call this method when it wants a list of all
   * available op modes. Add your op mode to the list to enable it.
   *
   * @param manager op mode manager
   */
  public void register(OpModeManager manager) {

    /*
     * register your op modes here.
     * The first parameter is the name of the op mode
     * The second parameter is the op mode class property
     *
     * If two or more op modes are registered with the same name, the app will display an error.
     */


    /*
     * The following op modes are example op modes provided by QualComm.
     * Uncomment the lines to make the op modes available to the driver station.
     */
    //manager.register("LinearK9TeleOp", LinearK9TeleOp.class);
    //manager.register("LinearIrExample", LinearIrExample.class);
    //manager.register("IrSeekerOp", IrSeekerOp.class);
    //manager.register("CompassCalibration", CompassCalibration.class);
    //manager.register("NxtTeleOp", NxtTeleOp.class);

    /*
     * The NullOp op mode
     */
    //manager.register("NullOp", NullOp.class);
//    manager.register("RGBTest", RGBtest.class);
//    manager.register("Test", Test.class);
    /*
     * The following example op modes are designed to work with a K9-style robot.
     *  - K9TeleOp is a simple driver controlled program.
     *  - K9IrSeeker uses a legacy IR seeker V2 sensor to follow a beacon.
     *  - K9Line uses a legacy LEGO NXT light sensor to follow a white line.
     */

    manager.register("TeleOpRedBasket", TeleopRedBasket.class);
    manager.register("TeleOpBlueBasket", TeleopBlueBasket.class);
//    manager.register("RGBTeleop", RGBTeleop.class);
//    manager.register("red", TeleopRedMabey.class);


	manager.register("Gryo Test", IMUGyroOnlyTest.class);

    //Autonomouses
    manager.register("BlueClimbers", BlueClimbers.class);
//    manager.register("BlueClimbers10", BlueClimbers10.class);
    manager.register("BlueClimbersClear", BlueClimbersClear.class);
//    manager.register("BlueClimbersClear10", BlueClimbersClear10.class);
    manager.register("BlueClimbersMove", BlueClimbersMove.class);
//    manager.register("BlueClimbersMove10", BlueClimbersMove10.class);
    manager.register("BlueClimbersSwitches", BlueClimbersSwitches.class);
//    manager.register("BlueClimbersSwitches10", BlueClimbersSwitches10.class);
    manager.register("RedClimbers", RedClimbers.class);
//    manager.register("RedClimbers10", RedClimbers10.class);
    manager.register("RedClimbersMove", RedClimbersMove.class);
//    manager.register("RedClimbersMove10", RedClimbersMove10.class);
    manager.register("RedClimbersClear", RedClimbersClear.class);
//    manager.register("RedClimbersClear10", RedClimbersClear10.class);
    manager.register("RedClimbersSwitches", RedClimbersSwitches.class);
//    manager.register("RedClimbersSwitches10", RedClimbersSwitches10.class);
  }
}
