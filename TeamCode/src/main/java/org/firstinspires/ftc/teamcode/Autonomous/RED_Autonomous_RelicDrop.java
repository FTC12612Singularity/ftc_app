package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "RED Auto Relic Drop Side")
//@Disabled
public class RED_Autonomous_RelicDrop extends OpMode {
    // Declare OpMode members.
    private Robot robot = new Robot();
    private ElapsedTime elapsedTime = new ElapsedTime();

    private boolean colorIsRed;

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
        if (elapsedTime.seconds() < 0.5) {
            robot.rightJewelServo.setPosition(robot.RIGHT_PUSH_POSITION);
        } else if (elapsedTime.seconds() < 1.5) {
            colorIsRed = robot.leftColorRed();
            if (colorIsRed) {
                robot.driveArcade(-0.25, 0);
            } else {
                robot.driveArcade(0.25, 0);
            }
        } else if (elapsedTime.seconds() < 1.75){
            robot.rightJewelServo.setPosition(robot.RIGHT_RELEASE_POSITION);
            robot.driveArcade(0.0,0);
        //} else if (elapsedTime.seconds() < 4) {
          //  robot.driveArcade(0.25, 0);
        } else {
            robot.driveArcade(0.0, 0);
        }
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {

    }
}
