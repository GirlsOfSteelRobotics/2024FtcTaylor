package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.DrivetrainSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LinearSlide;
import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;

@TeleOp
public class Team3TaylorTelopJava extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        DrivetrainSubsystem drivetrain = new DrivetrainSubsystem(hardwareMap);
        ClawSubsystem claw = new ClawSubsystem(hardwareMap);
        LinearSlide linearSlide = new LinearSlide(hardwareMap);
        waitForStart();

        while (opModeIsActive()) {

            double x = gamepad1.left_stick_x;
            double y = -gamepad1.left_stick_y;
            double rx = gamepad1.right_stick_x;

            drivetrain.driveWithJoystick(x, y, rx);

            if (gamepad2.a) {
                linearSlide.goUp();
            } else if(gamepad2.b) {
                linearSlide.goDown();
            } else {
                linearSlide.stop();
            }

            if (gamepad2.dpad_down) {
                claw.openClaw();
            } else if(gamepad2.dpad_up){
                claw.closeClaw();
            }


        }

    }
}
