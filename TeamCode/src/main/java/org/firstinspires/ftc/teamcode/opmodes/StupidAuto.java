package org.firstinspires.ftc.teamcode.opmodes;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LinearSlide;

@Autonomous
public class StupidAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        LinearSlide linearSlide = new LinearSlide(hardwareMap) ;
        ClawSubsystem claw = new ClawSubsystem(hardwareMap);
        waitForStart();

        while (opModeIsActive()) {
            claw.closeClaw();

            boolean isAtHeight = false;
            while(!isAtHeight) {
                isAtHeight = linearSlide.goToHeight(10);
            }

            claw.closeClaw();
        }
    }
}
