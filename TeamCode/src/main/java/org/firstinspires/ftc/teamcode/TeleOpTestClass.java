package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Autonomous.Robot;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp with Class")
//@Disabled
public class TeleOpTestClass extends OpMode {
    // Declare OpMode members.
    private Robot robot = new Robot();

    private boolean preY;
    private boolean toggleState;

    private boolean preX;
    private boolean slowState;


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

    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        boolean currentY = gamepad2.y;
        boolean currentX = gamepad1.x;

        if (currentX != preX) {
            if (currentX) {
                slowState = !slowState;
            }
        }

        robot.driveArcade(-gamepad1.left_stick_y, gamepad1.right_stick_x, slowState);

        double stageOneValue = -gamepad2.left_stick_y;
        double stageTwoValue = gamepad2.right_stick_y;

        robot.liftStageOne.setPower(stageOneValue);
        robot.liftStageTwo.setPower(stageTwoValue);

        if (currentY != preY) {
            if (currentY) {
                toggleState = !toggleState;
            }
        }

        Robot.GRIPPER_STATES gripperMode;
        if (toggleState) {
            gripperMode = Robot.GRIPPER_STATES.GRIPPER_FULL_GRIP;
        } else if (gamepad2.left_bumper || gamepad2.right_bumper) {
            if (gamepad2.left_bumper && gamepad2.right_bumper) {
                gripperMode = Robot.GRIPPER_STATES.GRIPPER_FULL_OPEN;
            } else if (gamepad2.left_bumper) {
                gripperMode = Robot.GRIPPER_STATES.GRIPPER_LEFT_ONLY;
            } else {
                gripperMode = Robot.GRIPPER_STATES.GRIPPER_RIGHT_ONLY;
            }
        } else {
            gripperMode = Robot.GRIPPER_STATES.GRIPPER_FULL_OPEN;
        }

        robot.grip(gripperMode);

        telemetry.addData("Color", robot.leftColorRed());

        preY = currentY;
        preX = currentX;
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {

    }
}