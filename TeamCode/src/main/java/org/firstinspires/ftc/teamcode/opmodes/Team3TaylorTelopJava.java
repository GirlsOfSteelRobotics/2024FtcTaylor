package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivetrainSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LinearSlide;


@TeleOp
public class Team3TaylorTelopJava extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        DrivetrainSubsystem drivetrain = new DrivetrainSubsystem(hardwareMap);
        ClawSubsystem claw = new ClawSubsystem(hardwareMap);
        LinearSlide linearSlide = new LinearSlide(hardwareMap);
        waitForStart();

        while (opModeIsActive()) {

            // Driving
            double x = gamepad1.left_stick_x;
            double y = -gamepad1.left_stick_y;
            double rx = gamepad1.right_stick_x;

            drivetrain.driveWithJoystick(x, y, rx);

            // Linear Slide
            if (gamepad1.left_bumper){
                linearSlide.goToHeight(LinearSlide.LOW_BASKET);
            } else if (gamepad1.left_trigger >0.5) {
                linearSlide.goToHeight(LinearSlide.LOW_RUNG);
            } else if (gamepad1.right_bumper) {
                linearSlide.goToHeight(LinearSlide.GROUND_HEIGHT);
            } else if (gamepad2.a) {
                linearSlide.goUp();
            } else if(gamepad2.b) {
                linearSlide.goDown();
            } else {
                linearSlide.stop();
            }

            // Claw
            if (gamepad1.a) {
                claw.openClaw();
            } else if(gamepad2.b){
                claw.closeClaw();
            }

            // Telemetry
            telemetry.addData("Yaw Angle", drivetrain.getAngle());
            telemetry.update();
        }

    }
}
