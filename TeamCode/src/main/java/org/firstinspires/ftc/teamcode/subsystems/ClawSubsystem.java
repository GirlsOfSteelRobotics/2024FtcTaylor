package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawSubsystem {
    Servo claw;

    public ClawSubsystem(HardwareMap hardwareMap) {
        claw = hardwareMap.get(Servo.class, "claw");
    }

    public void openClaw() {
        claw.setPosition(0.28);
    }

    public void closeClaw() {
        claw. setPosition(0.5);
    }
}
