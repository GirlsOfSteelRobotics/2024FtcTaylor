package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.DrivetrainSubsystem;

@TeleOp
public class TestTurnToAngle extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        DrivetrainSubsystem drivetrain = new DrivetrainSubsystem(hardwareMap);
        waitForStart();

        while (opModeIsActive()) {

            if (gamepad2.a) {
                drivetrain.turnToAngle(180);
            } else if (gamepad2.b) {
                drivetrain.turnToAngle(-90);
            } else if (gamepad2.y) {
                drivetrain.turnToAngle(0);
            } else if (gamepad2.x) {
                drivetrain.turnToAngle(90);
            } else {
                drivetrain. allDriveMotorsStop ();
            }

            telemetry.addData("Yaw Angle", drivetrain.getAngle());
            telemetry.update();
        }
    }
}