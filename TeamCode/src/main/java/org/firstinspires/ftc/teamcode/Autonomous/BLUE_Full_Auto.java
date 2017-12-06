package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "BLUE Auto")
//@Disabled
public class BLUE_Full_Auto extends OpMode {
    // Declare OpMode members.
    private Robot robot = new Robot();
    private ElapsedTime elapsedTime = new ElapsedTime();

    private boolean colorIsBlue;
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
            colorIsBlue = robot.leftColorBlue();
            if (colorIsBlue) {
                robot.methodTankDrive(0.28, -0.1);
            } else {
                robot.driveArcade(.25, 0);
            }
        } else if (elapsedTime.seconds() < 2.5) {
            robot.rightJewelServo.setPosition(robot.RIGHT_RELEASE_POSITION);
            telemetry.addData("status", "raising");
            robot.driveArcade(0.0, 0);
            referenceEncoderPosition = robot.rightMotor.getCurrentPosition();
        } else if (elapsedTime.seconds() < 3) {
            robot.driveArcade(.5, -0.25);
        } else if (elapsedTime.seconds() < 5) {
            robot.methodTankDrive(0.5, 0);
        } else if (elapsedTime.seconds() < 6) {
            robot.grip(Robot.GRIPPER_STATES.GRIPPER_FULL_OPEN);
        } else if (elapsedTime.seconds() < 6.5) {
            robot.driveArcade(0.25, 0);
        } else if (elapsedTime.seconds() < 7) {
            robot.driveArcade(-0.5, 0);
        } else if (elapsedTime.seconds() < 9) {
            robot.driveArcade(0.25, 0);
        } else if (elapsedTime.seconds() < 9.5) {
            robot.driveArcade(-0.25, 0 );

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

