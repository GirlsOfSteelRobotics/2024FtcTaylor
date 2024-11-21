package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class DrivetrainSubsystem {
    private static final double TURNING_ALLOWABLE_ERROR = 3;
    private static final double TICKS_PER_INCH = (1);

    DcMotor backLeft;
    DcMotor backRight;
    DcMotor frontRight;
    DcMotor frontLeft;
    IMU imu;

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
        double error = getAngle ()- angle;
        if (Math.abs (error) < TURNING_ALLOWABLE_ERROR) {
            allDriveMotorsStop ();
        }
        else if (error > 0) {
            driveWithJoystick (0,0,0.4);
        }
        else if (error < 0) {
            driveWithJoystick (0,0, -0.4);
        }
    }

    public void allDriveMotorsStop () {
        backLeft.setPower (0);
        backRight.setPower(0);
        frontRight.setPower(0);
        frontLeft.setPower(0);

    }
    public void autoDriveStraight (double distanceForward) {
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        boolean drivenToDistance = false;
        while (!drivenToDistance) {
            double error = getDistanceMoved() - distanceForward;
            if (Math.abs (error) < 1) {
                allDriveMotorsStop();
                drivenToDistance = true;
            }
            else if (error > 0) {
                driveWithJoystick(0,0.3,0);
            }
            else if (error <0) {
                driveWithJoystick(0,-0.3,0);
            }
        }
    }

    public void autoDriveStrafe (double distanceForward) {
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        boolean drivenToDistance = false;
        while (!drivenToDistance) {
            double error = getDistanceMoved() - distanceForward;
            // If we are close enough to our goal, stop.
            if (Math.abs(error) < 1) {
                allDriveMotorsStop();
                drivenToDistance = true;
            } else if (error > 0) {
                driveWithJoystick(0.3, 0, 0);
            } else if (error < 0) {
                driveWithJoystick(-0.3, 0, 0);
            }
        }
    }

    public double getDistanceMoved () {
        return frontRight.getCurrentPosition() / TICKS_PER_INCH;
    }

}

