package org.firstinspires.ftc.teamcode.opmodes;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivetrainSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LinearSlide;

@Autonomous
public class StupidAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        LinearSlide linearSlide = new LinearSlide(hardwareMap) ;
        ClawSubsystem claw = new ClawSubsystem(hardwareMap);
        DrivetrainSubsystem drivetrain = new DrivetrainSubsystem(hardwareMap);
        waitForStart();

        if (opModeIsActive()) {
            // My code is supposed to drive in a square.
            drivetrain.autoDriveStraight(12);
            drivetrain.autoDriveStrafe(12);
            drivetrain.autoDriveStraight(-12);
            drivetrain.autoDriveStrafe(-12);


//            claw.closeClaw();
//            sleep(5000);
//
//            linearSlide.autoGoToHeight (10, telemetry);
//            claw.openClaw();
//            sleep(5000);
//
//           linearSlide.autoGoToHeight (20, telemetry);
//
//
//            telemetry.addData("Done", true);
//            telemetry.update();
        }
    }
}
