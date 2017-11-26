package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Encoder Autonomous")
//@Disabled
public class EncoderDrive extends OpMode {
    // Declare OpMode members.
    private Robot robot = new Robot();
    private ElapsedTime elapsedTime = new ElapsedTime();

    static final double COUNTS_PER_MOTOR_REV = 1120;    // eg: NeveRest 40
    static final double DRIVE_GEAR_REDUCTION = 1.0 / 3.0;     // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * Math.PI);

    private int currentStep;
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
        switch (currentStep) {
            case 0:
                if (elapsedTime.seconds() < 1.0) {
                    robot.grip(Robot.GRIPPER_STATES.GRIPPER_FULL_GRIP);
                    telemetry.addData("Status", "Gripping");
                } else {
                    currentStep++;
                }
                break;
            case 1:
                if (elapsedTime.seconds() < 1.5) {
                    robot.rightJewelServo.setPosition(robot.RIGHT_PUSH_POSITION);
                } else if (elapsedTime.seconds() < 2.5) {
                    colorIsRed = robot.leftColorRed();
                    if (colorIsRed) {
                        robot.driveArcade(-0.25, 0);
                    } else {
                        robot.driveArcade(0.25, 0);
                    }
                } else if (elapsedTime.seconds() < 3) {
                    robot.rightJewelServo.setPosition(robot.RIGHT_RELEASE_POSITION);
                } else if (((robot.leftMotor.getCurrentPosition() + robot.rightMotor.getCurrentPosition()) / 2 < 32 * COUNTS_PER_INCH)) {
                    robot.driveArcade(0.25, 0);
                } else {
                    robot.driveArcade(0, 0);
                    currentStep++;
                }
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;

        }
        if (elapsedTime.seconds() < 1.0)

        {
            robot.grip(Robot.GRIPPER_STATES.GRIPPER_FULL_GRIP);
            telemetry.addData("Status", "Gripping");
        } else if (elapsedTime.seconds() < 2.0)

        {
            robot.driveArcade(0.5, 0);
            telemetry.addData("Status", "Driving");
        } else

        {
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
