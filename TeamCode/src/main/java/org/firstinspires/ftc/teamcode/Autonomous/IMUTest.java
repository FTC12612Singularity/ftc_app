package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.AdafruitIMU;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "IMUTest")
//@Disabled
public class IMUTest extends OpMode {
    // Declare OpMode members.
    AdafruitIMU imu;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        imu = new AdafruitIMU(hardwareMap, "Aimu");
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        imu.startIMU();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        telemetry.addData("Angle", imu.getYaw());
    }
}
