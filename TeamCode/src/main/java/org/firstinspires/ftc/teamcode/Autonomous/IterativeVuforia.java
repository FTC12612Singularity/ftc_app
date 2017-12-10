package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "BLUE Auto")
//@Disabled
public class IterativeVuforia extends OpMode {
    // Declare OpMode members.
    private Robot robot = new Robot();
    private ElapsedTime elapsedTime = new ElapsedTime();

    private OpenGLMatrix lastLocation = null;

    private VuforiaLocalizer vuforia;

    private VuforiaTrackables relicTrackables = null;
    private VuforiaTrackable relicTemplate = null;

    private int stepCase = 0;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        robot.initRobot(hardwareMap);
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = "AZ+5oNH/////AAAAGRMVUnzn/0Oavi1kZUsp8g8gZ6yi5lYqQORBM3xOF/ew3xp49rCxvLTWxqWGNMWaP+REPbi0gvFy6MI3gkhpJd0+Eur3dtSMdA9aBSpf0SNLo7zikxmXvsUANlytN3limjhdQPgUe5Iq+5Ec3O8hbtKTsaMmAltpiTFY7AvbYI2Fccf+e5B2TYTKLWGTx5yDVHX2KBRh4X07f9+F7RZvp3cOaBZkp5EYHkx43UuI70N+UMrvy22dCIhMvbK/jYacl8UH3N1mdudje3P6lSGIp91hHlVqefp0IEXGdsKwlrSGMnOW4MFTXCi2RFpo8Q/NxEOgOkos+QesbV7PWLgRMlNAWoLKaA1MPwul+7N+70xv";

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary
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


    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        switch (stepCase) {
            case 0: //Get
                break;
            case 1:
                break;
            case 2:
                break;
            default: //Stop robot
                robot.driveArcade(0,0);
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

