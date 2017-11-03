package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * This is a skeletal example of what a full robot class can look like.
 * This specific file can later be implemented into both the TeleOp and Autonomous programs, reducing copying and pasting, as well as the length of the code.
 */

public class Robot { //Does not implement anything as it is a separate class
    //Here you create objects of actuators and sensors on the robot. It is important that these are public so that other classes can access them as hardware
    //You can also use this to hold constant variables for servo positions, motor speeds, etc.
    public DcMotor leftMotor = null; //You have to initialize these as null here, or else you run the risk of gettign an error if the program never reaches init
    public DcMotor rightMotor = null;

    public DcMotor liftDrive = null;
    public DcMotor liftDriveTwo = null;

    public Servo pink = null;
    public Servo blue = null;
    public Servo orange = null;
    public Servo green = null;

    //Some constant variables
    public final double DRIVE_SLOW_SPEED_SCALE = 0.5; //Final means that the variable can only be assigned once, it remains a constant value throughout the time the program is run
    public final double DRIVE_FULL_SPEED_SCALE = 1.0;

    public final double DRIVE_FULL_STOP = 0.0;
    public final double DRIVE_FULL_SPEED = 1.0;

    public final double LIFT_FULL_STOP = 0.0;
    public final double LIFT_FULL_SPEED = 1.0;

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
    public void init(HardwareMap ahwMap) { //For initializing all of motors and servos
        hwMap = ahwMap;

        //Here you map and initialize all of you devices. It is always better if you are doing something to one thing, to ensure that it is done to the others.
        //Drive motors
        leftMotor = hwMap.get(DcMotor.class, "LM");
        rightMotor = hwMap.get(DcMotor.class, "RM");

        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);

        //Lift motors
        liftDrive = hwMap.get(DcMotor.class, "LD");
        liftDriveTwo = hwMap.get(DcMotor.class, "LDT");

        liftDrive.setDirection(DcMotor.Direction.FORWARD);
        liftDriveTwo.setDirection(DcMotor.Direction.FORWARD);

        //Servos
        pink = hwMap.get(Servo.class, "pink");
        blue = hwMap.get(Servo.class, "blue");
        orange = hwMap.get(Servo.class, "orange");
        green = hwMap.get(Servo.class, "green");

        pink.setDirection(Servo.Direction.REVERSE);
        blue.setDirection(Servo.Direction.REVERSE);
        orange.setDirection(Servo.Direction.FORWARD);
        green.setDirection(Servo.Direction.FORWARD);

        //Set home positions of all actuators
        leftMotor.setPower(DRIVE_FULL_STOP);
        rightMotor.setPower(DRIVE_FULL_STOP);

        liftDrive.setPower(LIFT_FULL_STOP);
        liftDriveTwo.setPower(LIFT_FULL_STOP);

        pink.setPosition(PINK_RELEASE_POSITION);
        blue.setPosition(BLUE_RELEASE_POSITION);
        orange.setPosition(ORANGE_RELEASE_POSITION);
        green.setPosition(GREEN_RELEASE_POSITION);
    }

    public void driveArcade(double drive, double turn) { //Takes a drive and turn value and converts it to motor values. Yes, you can have multiple methods that are named the same, as long as they take a different permutation of parameters
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
}
