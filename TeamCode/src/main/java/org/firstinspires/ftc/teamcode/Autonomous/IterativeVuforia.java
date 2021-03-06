package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.Autonomous.Navigation.navigationPID;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Vuforia Iterative")
//@Disabled
public class IterativeVuforia extends OpMode {
    double[][] movementArray = new double[][]{
            {1, 0.25, 6},
            {6, 0, 0},
            {3, 0.25, -45},
            {2, -0.25, -2},

            {3, 0.25, -90},
            {1, 0.25, 3},
            {5, 0, 0}

    };
    // Declare OpMode members.
    private Robot robot = new Robot();
    private ElapsedTime elapsedTime = new ElapsedTime();
    private navigationPID testNavigator;
    private OpenGLMatrix lastLocation = null;
    private VuforiaLocalizer vuforia;
    private VuforiaTrackables relicTrackables = null;
    private VuforiaTrackable relicTemplate = null;
    private int encoderPositonReference;
    private boolean colorIsRed;
    private int mainProgramStep = 0;
    private int knockJewelStep = 0;
    private int driveJewelStep = 0;

    private RelicRecoveryVuMark vuMark;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        robot.initRobot(hardwareMap);
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = "AZ+5oNH/////AAAAGRMVUnzn/0Oavi1kZUsp8g8gZ6yi5lYqQORBM3xOF/ew3xp49rCxvLTWxqWGNMWaP+REPbi0gvFy6MI3gkhpJd0+Eur3dtSMdA9aBSpf0SNLo7zikxmXvsUANlytN3limjhdQPgUe5Iq+5Ec3O8hbtKTsaMmAltpiTFY7AvbYI2Fccf+e5B2TYTKLWGTx5yDVHX2KBRh4X07f9+F7RZvp3cOaBZkp5EYHkx43UuI70N+UMrvy22dCIhMvbK/jYacl8UH3N1mdudje3P6lSGIp91hHlVqefp0IEXGdsKwlrSGMnOW4MFTXCi2RFpo8Q/NxEOgOkos+QesbV7PWLgRMlNAWoLKaA1MPwul+7N+70xv";

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        testNavigator = new navigationPID(movementArray, hardwareMap, "Aimu", robot.leftMotor, robot.rightMotor, (int) (robot.COUNTS_PER_MOTOR_REV * robot.DRIVE_GEAR_REDUCTION), 4);
        testNavigator.tuneGains(0.04, 0.00004, 0);

        telemetry.addData("Status", "Tuning done");

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
        relicTrackables.activate();
    }

    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        switch (mainProgramStep) {
            case 0:
                switch (knockJewelStep) {
                    case 0: //Close block gripper and lower arm
                        if (elapsedTime.seconds() < 1) { //Give 1 second to lowering the servos
                            robot.grip(Robot.GRIPPER_STATES.GRIPPER_FULL_GRIP);
                            robot.rightJewelServo.setPosition(robot.RIGHT_PUSH_POSITION);
                        } else {
                            colorIsRed = robot.leftColorRed();
                            knockJewelStep++;
                        }
                        break;
                    case 1: //Detect and turn based on color
                        if (colorIsRed) {
                            switch (driveJewelStep) {
                                case 0:
                                    if (testNavigator.currentHeading() < -15) {
                                        robot.rightJewelServo.setPosition(robot.RIGHT_RELEASE_POSITION);
                                        driveJewelStep++;
                                    } else {
                                        testNavigator.moveWithAngle(0, -15);
                                    }
                                    break;
                                case 1:
                                    if (testNavigator.currentHeading() > 0) {
                                        knockJewelStep++;
                                    } else {
                                        testNavigator.moveWithAngle(0, 0);
                                    }
                                    break;
                            }
                        } else {
                            switch (driveJewelStep) {
                                case 0:
                                    if (testNavigator.currentHeading() > 15) {
                                        robot.rightJewelServo.setPosition(robot.RIGHT_RELEASE_POSITION);
                                        driveJewelStep++;
                                    } else {
                                        testNavigator.moveWithAngle(0, 15);
                                    }
                                    break;
                                case 1:
                                    if (testNavigator.currentHeading() < 0) {
                                        knockJewelStep++;
                                    } else {
                                        testNavigator.moveWithAngle(0, 0);
                                    }
                                    break;
                            }
                        }
                        break;
                    case 2:
                        mainProgramStep++;
                        break;
                }
                break;
            case 1:
                if (testNavigator.navigationType() == 6) {
                    vuMark = RelicRecoveryVuMark.from(relicTemplate);
                    encoderPositonReference = robot.leftMotor.getCurrentPosition();
                    elapsedTime.reset();
                    mainProgramStep++;
                } else {
                    testNavigator.loopNavigation();
                }
                break;
            case 2:
                switch (vuMark) {
                    case UNKNOWN:
                        if (elapsedTime.seconds() > 4) {
                            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {
                                break;
                            } else {
                                mainProgramStep++;
                            }
                        } else {
                            vuMark = RelicRecoveryVuMark.from(relicTemplate);
                        }
                        telemetry.addData("Case", "Unknown");
                        break;
                    case LEFT:
                        if (robot.leftMotor.getCurrentPosition() > testNavigator.convertInchesToEncoderTicks(30) + encoderPositonReference) {
                            testNavigator.moveNoStop(0);
                            mainProgramStep++;
                            testNavigator.forceNextMovement();
                        } else {
                            testNavigator.moveNoStop(0.3);
                        }
                        telemetry.addData("Case", "Left");
                        break;
                    case RIGHT:
                        if (robot.leftMotor.getCurrentPosition() > testNavigator.convertInchesToEncoderTicks(14) + encoderPositonReference) {
                            testNavigator.moveNoStop(0);
                            mainProgramStep++;
                            testNavigator.forceNextMovement();
                        } else {
                            testNavigator.moveNoStop(0.3);
                        }
                        telemetry.addData("Case", "Right");
                        break;
                    case CENTER:
                        if (robot.leftMotor.getCurrentPosition() > testNavigator.convertInchesToEncoderTicks(22) + encoderPositonReference) {
                            testNavigator.moveNoStop(0);
                            mainProgramStep++;
                            testNavigator.forceNextMovement();
                        } else {
                            testNavigator.moveNoStop(0.3);
                        }
                        telemetry.addData("Case", "Center");
                        break;
                }
                break;
            case 3:
                testNavigator.loopNavigation();
                break;
        }

    }


    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {

    }
}


