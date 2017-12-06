package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "RED NO DRIVE")
//@Disabled
public class RED_NO_DRIVE extends OpMode {
    // Declare OpMode members.
    private Robot robot = new Robot();
    private ElapsedTime elapsedTime = new ElapsedTime();

    private boolean colorIsRed;
    private int referenceEncoderPosition;
    private boolean moveOn;

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
            robot.grip(Robot.GRIPPER_STATES.GRIPPER_FULL_GRIP);
            robot.rightJewelServo.setPosition(robot.RIGHT_PUSH_POSITION);
        } else if (elapsedTime.seconds() < 1.5) {
            colorIsRed = robot.leftColorRed();
            if (colorIsRed) {
                robot.methodTankDrive(0.28, -0.1);
            } else {
                robot.driveArcade(.25, 0);
            }
        } else {
            robot.driveArcade(0, 0);
        }

       /* } else if (robot.rightMotor.getCurrentPosition() < referenceEncoderPosition + robot.convertTicksToInches(17)) {
            robot.driveArcade(0.5, 0);
            telemetry.addData("status", "driving");
            //referenceEncoderPosition = robot.rightMotor.getCurrentPosition();
        } else if (moveOn) {
            referenceEncoderPosition = robot.rightMotor.getCurrentPosition();
            moveOn = true;
       // } else if (robot.rightMotor.getCurrentPosition() < referenceEncoderPosition - robot.convertTicksToInches(7)) {
          //  robot.methodTankDrive(.4, -.4);
          //  telemetry.addData("status", "turning");
        } else {
            robot.driveArcade(0.0, 0);
        }
        telemetry.addData("encoder", robot.rightMotor.getCurrentPosition());// + ", " + referenceEncoderPosition);

        telemetry.addData("liftTest", robot.liftStageTwo.getCurrentPosition());// + ", " + referenceEncoderPosition);*/


    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {

    }
}

