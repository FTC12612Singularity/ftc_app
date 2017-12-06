package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp")
@Disabled
public class TeleOp extends OpMode {
    // Declare OpMode members.
    private DcMotor leftMotor;
    private DcMotor rightMotor;

    private DcMotor liftDrive;
    private DcMotor liftDriveTwo;

    private Servo pink;
    private Servo blue;
    private Servo orange;
    private Servo green;

    private boolean preY;
    private boolean toggleState;

    private boolean preX;
    private boolean slowState;



    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        leftMotor = hardwareMap.get(DcMotor.class, "LM");
        rightMotor = hardwareMap.get(DcMotor.class, "RM");
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        liftDrive = hardwareMap.dcMotor.get("LD");
        liftDriveTwo = hardwareMap.dcMotor.get("LDT");
        liftDrive.setPower(0);
        liftDriveTwo.setPower(0);

        pink = hardwareMap.servo.get("pink");
        blue = hardwareMap.servo.get("blue");
        orange = hardwareMap.servo.get("orange");
        green = hardwareMap.servo.get("green");

        pink.setDirection(Servo.Direction.REVERSE);
        blue.setDirection(Servo.Direction.REVERSE);
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

        double drive = -gamepad1.left_stick_y;
        double turn = gamepad1.right_stick_x;
        leftMotor.setPower(Range.clip((drive + turn) * (slowState ? 0.5 : 1), -1.0, 1.0));
        rightMotor.setPower(Range.clip((drive - turn) * (slowState ? 0.5 : 1), -1.0, 1.0));

        double up = gamepad2.left_stick_y;
        double up2 = gamepad2.right_stick_y;

        liftDrive.setPower(-up);
        liftDriveTwo.setPower(up2);

        if (currentY != preY) {
            if (currentY) {
                toggleState = !toggleState;
            }
        }

        short gripperMode;
        if (toggleState) {
            gripperMode = 1;
        } else if (gamepad2.left_bumper || gamepad2.right_bumper) {
            if (gamepad2.left_bumper && gamepad2.right_bumper) {
                gripperMode = 1;
            } else if (gamepad2.left_bumper) {
                gripperMode = 2;
            } else {
                gripperMode = 3;
            }
        } else {
            gripperMode = 0;
        }


        switch (gripperMode) {
            case 0:
                blue.setPosition(1);
                pink.setPosition(0);
                orange.setPosition(0);
                green.setPosition(1);
                break;
            case 1:
                blue.setPosition(0.5);
                pink.setPosition(0.5);
                orange.setPosition(0.5);
                green.setPosition(0.5);
                break;
            case 2:
                blue.setPosition(1);
                pink.setPosition(0.5);
                orange.setPosition(0.5);
                green.setPosition(1);
                break;
            case 3:
                blue.setPosition(0.5);
                pink.setPosition(0);
                orange.setPosition(0);
                green.setPosition(0.5);
                break;
        }
        telemetry.addData("Case", gripperMode);

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
