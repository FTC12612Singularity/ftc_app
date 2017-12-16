package org.firstinspires.ftc.teamcode.Autonomous.Navigation;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.Autonomous.Robot;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "RedArrayTest")
//@Disabled
public class Red_Navigation_Test extends OpMode {
    navigationPID testNavigator;
    private Robot robot = new Robot();
    private ElapsedTime elapsedTime = new ElapsedTime();

    private OpenGLMatrix lastLocation = null;

    private VuforiaLocalizer vuforia;

    private VuforiaTrackables relicTrackables = null;
    private VuforiaTrackable relicTemplate = null;

    private int stepCase = 0;



    double[][] movementArray = new double[][]{
            {1, 0.3, 21  },
            {4, -0.3, 90},
            {1, 0.25, 10},
            {3, 0.25, 0},
            {1, 0.25, 3},
            {5, 0, 0}

    };

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
        robot.initRobot(hardwareMap);
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
    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    }
    @Override
    public void loop() {
        robot.grip(Robot.GRIPPER_STATES.GRIPPER_FULL_GRIP);
        double[][] movementArray = new double[][]{
                {1, 0.3, 21  },
                {4, -0.3, 90},
                {1, 0.25, 10},
                {3, 0.25, 0},
                {1, 0.25, 3},
                {5, 0, 0}

        };
    }

    @Override
    public void stop() {


    }
}