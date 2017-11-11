package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * This is a skeletal example of what a full robot class can look like.
 * This specific file can later be implemented into both the TeleOp and Autonomous programs, reducing copying and pasting, as well as the length of the code.
 */

public class Robot { //Does not implement anything as it is a separate class
    //Here you create objects of actuators and sensors on the robot. It is important that these are public if you want other classes to access them as DcMotors, Servos, or anything else
    //You can also use this to hold constant variables for servo positions, motor speeds, etc.
    public DcMotor leftMotor = null; //You have to initialize these as null here, or else you run the risk of gettign an error if the program never reaches init
    public DcMotor rightMotor = null;

    public DcMotor liftStageOne = null;
    public DcMotor liftStageTwo = null;

    public Servo pink = null;
    public Servo blue = null;
    public Servo orange = null;
    public Servo green = null;
    public Servo leftJewelServo = null;
    public Servo rightJewelServo = null;

    public ColorSensor leftColorSensor = null;

    //Some constant variables
    public final double DRIVE_SLOW_SPEED_SCALE = 0.5; //Final means that the variable can only be assigned once, it remains a constant value throughout the time the program is run
    public final double DRIVE_FULL_SPEED_SCALE = 1.0;

    public final double DRIVE_FULL_STOP = 0.0;
    public final double DRIVE_FULL_SPEED = 1.0;

    public final double LIFT_FULL_STOP = 0.0;
    public final double LIFT_FULL_SPEED = 1.0;

    public final double LEFT_PUSH_POSITION = 1.0;
    public final double LEFT_RELEASE_POSITION = 0.5;

    public final double RIGHT_PUSH_POSITION = 1.0;
    public final double RIGHT_RELEASE_POSITION = 0.5;

    public final double PINK_GRIP_POSITION = 0.5;
    public final double PINK_RELEASE_POSITION = 0.0;
    public final double BLUE_GRIP_POSITION = 0.5;
    public final double BLUE_RELEASE_POSITION = 1.0;
    public final double ORANGE_GRIP_POSITION = 0.5;
    public final double ORANGE_RELEASE_POSITION = 0.0;
    public final double GREEN_GRIP_POSITION = 0.5;
    public final double GREEN_RELEASE_POSITION = 1.0;

    public enum GRIPPER_STATES {
        GRIPPER_FULL_OPEN,
        GRIPPER_FULL_GRIP,
        GRIPPER_LEFT_ONLY,
        GRIPPER_RIGHT_ONLY
    }

    //Private objects that are needed for initialization
    private HardwareMap hwMap = null;

    //Contructor
    public Robot() {//This is blank because you are only calling methods which reference other external classes
    }

    //Methods to call
    public void initRobot(HardwareMap ahwMap) { //For initializing all of motors and servos
        hwMap = ahwMap;

        //Here you map and initialize all of you devices. It is always better if you are doing something to one thing, to ensure that it is done to the others.
        //Drive motors
        leftMotor = hwMap.get(DcMotor.class, "LM");
        rightMotor = hwMap.get(DcMotor.class, "RM");

        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);

        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //Lift motors
        liftStageOne = hwMap.get(DcMotor.class, "LD");
        liftStageTwo = hwMap.get(DcMotor.class, "LDT");

        liftStageOne.setDirection(DcMotor.Direction.FORWARD);
        liftStageTwo.setDirection(DcMotor.Direction.FORWARD);

        //Servos
        pink = hwMap.get(Servo.class, "pink");
        blue = hwMap.get(Servo.class, "blue");
        orange = hwMap.get(Servo.class, "orange");
        green = hwMap.get(Servo.class, "green");
        leftJewelServo = hwMap.get(Servo.class, "LJS");
        rightJewelServo = hwMap.get(Servo.class, "RJS");

        pink.setDirection(Servo.Direction.REVERSE);
        blue.setDirection(Servo.Direction.REVERSE);
        orange.setDirection(Servo.Direction.FORWARD);
        green.setDirection(Servo.Direction.FORWARD);
        leftJewelServo.setDirection(Servo.Direction.FORWARD);
        rightJewelServo.setDirection(Servo.Direction.REVERSE);

        //Sensors
        leftColorSensor = hwMap.get(ColorSensor.class, "SC");

        //Set home positions of all actuators
        leftColorSensor.enableLed(true);

        leftMotor.setPower(DRIVE_FULL_STOP);
        rightMotor.setPower(DRIVE_FULL_STOP);

        liftStageOne.setPower(LIFT_FULL_STOP);
        liftStageTwo.setPower(LIFT_FULL_STOP);

        pink.setPosition(PINK_RELEASE_POSITION);
        blue.setPosition(BLUE_RELEASE_POSITION);
        orange.setPosition(ORANGE_RELEASE_POSITION);
        green.setPosition(GREEN_RELEASE_POSITION);
    }

    //Yes, you can have multiple methods that are named the same, as long as they take a different number/type of parameters
    public void driveArcade(double drive, double turn) { //Takes a drive and turn value and converts it to motor values.
        leftMotor.setPower(Range.clip((drive + turn), -DRIVE_FULL_SPEED, DRIVE_FULL_SPEED));
        rightMotor.setPower(Range.clip((drive - turn), -DRIVE_FULL_SPEED, DRIVE_FULL_SPEED));
    }

    public void driveArcade(double drive, double turn, boolean slowState) { //Takes a drive and turn value and converts it to motor values. This also excepts a scaling boolean
        leftMotor.setPower(Range.clip((drive + turn) * (slowState ? DRIVE_SLOW_SPEED_SCALE : DRIVE_FULL_SPEED_SCALE), -DRIVE_FULL_SPEED, DRIVE_FULL_SPEED));
        rightMotor.setPower(Range.clip((drive - turn) * (slowState ? DRIVE_SLOW_SPEED_SCALE : DRIVE_FULL_SPEED_SCALE), -DRIVE_FULL_SPEED, DRIVE_FULL_SPEED));
    }

    public void grip(GRIPPER_STATES state) { //Takes an enumeration as a input and chooses the case based off of that
        switch (state) {
            case GRIPPER_FULL_OPEN:
                pink.setPosition(PINK_RELEASE_POSITION);
                blue.setPosition(BLUE_RELEASE_POSITION);
                orange.setPosition(ORANGE_RELEASE_POSITION);
                green.setPosition(GREEN_RELEASE_POSITION);
                break;
            case GRIPPER_FULL_GRIP:
                pink.setPosition(PINK_GRIP_POSITION);
                blue.setPosition(BLUE_GRIP_POSITION);
                orange.setPosition(ORANGE_GRIP_POSITION);
                green.setPosition(GREEN_GRIP_POSITION);
                break;
            case GRIPPER_LEFT_ONLY:
                pink.setPosition(PINK_GRIP_POSITION);
                blue.setPosition(BLUE_RELEASE_POSITION);
                orange.setPosition(ORANGE_GRIP_POSITION);
                green.setPosition(GREEN_RELEASE_POSITION);
                break;
            case GRIPPER_RIGHT_ONLY:
                pink.setPosition(PINK_RELEASE_POSITION);
                blue.setPosition(BLUE_GRIP_POSITION);
                orange.setPosition(ORANGE_RELEASE_POSITION);
                green.setPosition(GREEN_GRIP_POSITION);
                break;
        }
    }

    //Some methods for pushing data out of this 'black box'
    public int getLiftPosition() {
        //Do some math on getting the encoder positions
        return liftStageOne.getCurrentPosition() + liftStageTwo.getCurrentPosition();
    }

    public boolean leftColorRed() {
        return leftColorSensor.red() > leftColorSensor.blue() && leftColorSensor.red() > leftColorSensor.green();
    }
}