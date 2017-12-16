
package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "TestEncoder")
//@Disabled
public class EncoderDriveTwo extends OpMode {
    // Declare OpMode members.
    private Robot robot = new Robot();
    private ElapsedTime elapsedTime = new ElapsedTime();

    static final double COUNTS_PER_MOTOR_REV = 1120;    // eg: NeveRest 40
    static final double DRIVE_GEAR_REDUCTION = 1.0 / 3.0;
    static final double WHEEL_DIAMETER_INCHES = 4.0;
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
    /*
     * Code to run ONCE when the driver hits PLAY
     */

}

    @Override
    public void start() {
        elapsedTime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        telemetry.addData("Encoder Left", robot.rightMotor.getCurrentPosition());
        telemetry.addData("Encoder Right", robot.leftMotor.getCurrentPosition());

        }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    }
