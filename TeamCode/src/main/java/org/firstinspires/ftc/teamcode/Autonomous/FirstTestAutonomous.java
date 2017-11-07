package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Autonomous")
//@Disabled
public class FirstTestAutonomous extends OpMode {
    // Declare OpMode members.
    private Robot robot = new Robot();
    private ElapsedTime elapsedTime = new ElapsedTime();

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        robot.initRobot(hardwareMap);
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {

    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        elapsedTime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        if (elapsedTime.seconds() < 1.0) {
            robot.grip(Robot.GRIPPER_STATES.GRIPPER_FULL_GRIP);
            telemetry.addData("Status", "Gripping");
        } else if (elapsedTime.seconds() < 2.0) {
            robot.driveArcade(0.5, 0);
            telemetry.addData("Status", "Driving");
        } else {
            robot.driveArcade(0.0, 0);
            telemetry.addData("Status", "Done");
        }
        telemetry.addData("Time", elapsedTime.seconds());
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {

    }
}
