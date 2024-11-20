package org.firstinspires.ftc.teamcode.opmodes;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LinearSlide;

@Autonomous
public class StupidAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        LinearSlide linearSlide = new LinearSlide(hardwareMap) ;
        ClawSubsystem claw = new ClawSubsystem(hardwareMap);
        waitForStart();

        if (opModeIsActive()) {
            claw.closeClaw();
            sleep(5000);

            linearSlide.autoGoToHeight (10, telemetry);
            claw.openClaw();
            sleep(5000);
            
           linearSlide.autoGoToHeight (20, telemetry);
            
            
            telemetry.addData("Done", true);
            telemetry.update();
        }
    }
}
