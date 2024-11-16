package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class DrivetrainSubsystem {

    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor frontRight;
    private DcMotor frontLeft;
    private IMU imu;

    public DrivetrainSubsystem(HardwareMap hardwareMap) {
        backLeft = hardwareMap.get(DcMotor.class, "backleft");
        backRight = hardwareMap.get(DcMotor.class, "backright");
        frontLeft = hardwareMap.get(DcMotor.class, "frontleft");
        frontRight = hardwareMap.get(DcMotor.class, "frontright");
        imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters myIMUparameters;
        myIMUparameters = new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.UP,
                        RevHubOrientationOnRobot.UsbFacingDirection.LEFT
                )
        );
        imu.initialize(myIMUparameters);



        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    public void driveWithJoystick(double x, double y, double rx) {
        backLeft.setPower (y - x + rx);
        backRight.setPower(y + x - rx);
        frontRight.setPower(y - x - rx);
        frontLeft.setPower(y + x + rx);


    }

    public double getAngle () {
        YawPitchRollAngles robotOrientation;
        robotOrientation = imu.getRobotYawPitchRollAngles();
        double Yaw = robotOrientation.getYaw(AngleUnit.DEGREES);
        return Yaw;
    }
    public void turnToAngle (double angle){
        double error = getAngle() - angle;
        if (error > 0) {
            driveWithJoystick (0,0,0.5);
        }
        if (error < 0) {
            driveWithJoystick (0,0, -0.5);
        }
    }
}

