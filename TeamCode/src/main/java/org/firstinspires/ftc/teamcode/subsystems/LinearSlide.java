package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class LinearSlide {
    DcMotor linearSlide;

    public LinearSlide (HardwareMap hardwareMap) {
        linearSlide = hardwareMap.get(DcMotor.class, "linearslide");
        linearSlide.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void goUp() {
        linearSlide.setPower(1);
    }

    public void goDown() {
        linearSlide.setPower(-1);
    }

    public void stop() {
        linearSlide.setPower(0);
    }
}
