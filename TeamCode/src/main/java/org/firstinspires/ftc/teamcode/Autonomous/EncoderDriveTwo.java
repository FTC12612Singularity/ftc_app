
package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;



@Autonomous(name="EncoderDriveTwo", group="Iterative Opmode")
@Disabled
public class EncoderDriveTwo extends OpMode
{

    DcMotor leftmotor = null;
    DcMotor rightmotor = null;


    /*
     * Code to run ONCE when the driver hits INIT
     */

    public void main() throws InterruptedException {
        leftmotor = hardwareMap.dcMotor.get("LM");
        rightmotor = hardwareMap.dcMotor.get("RM");


        leftmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        rightmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);


        waitForStart();

        leftmotor.getCurrentPosition();
        leftmotor.setTargetPosition(0);
        leftmotor.isBusy();
    }



    private void waitForStart() {
    }

    @Override
    public void init() {

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

    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
