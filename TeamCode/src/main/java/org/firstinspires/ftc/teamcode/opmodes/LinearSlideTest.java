package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.LinearSlide;

@TeleOp
public class LinearSlideTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        LinearSlide linearSlide = new LinearSlide(hardwareMap) ;
        waitForStart();

        while (opModeIsActive()) {
            if (gamepad2.a) {
                linearSlide.goToHeight(10);
            } else if (gamepad1.b) {
                linearSlide.goToHeight(28);
            } else if (gamepad1.x) {
                linearSlide.goToHeight(15);
            } else {
                linearSlide. allDriveMotorsStop();
            }
            telemetry.addData("height", linearSlide.getHeight());
            telemetry.update();
        }
    }

//    @Override
//    public void runOpMode()
}
