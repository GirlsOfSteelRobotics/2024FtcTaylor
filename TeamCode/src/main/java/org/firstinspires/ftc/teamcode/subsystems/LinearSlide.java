package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class LinearSlide {
   private static final double TICKS_PER_INCH = (-2843 - -183 ) / 27.5;

    private DcMotor linearSlide;

    public LinearSlide (HardwareMap hardwareMap) {
        linearSlide = hardwareMap.get(DcMotor.class, "linearslide");
        linearSlide.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void goUp() {
        linearSlide.setPower(-1);
    }

    public void goDown() {
        linearSlide.setPower(1);
    }

    public void stop() {
        linearSlide.setPower(0);
    }

    public double getHeight () {
        double height = linearSlide.getCurrentPosition() / TICKS_PER_INCH;
        return height ;
    }


    public boolean goToHeight (double height){
        int heightInTicks = (int) (height * TICKS_PER_INCH);
        linearSlide.setTargetPosition(heightInTicks);
        double error = getHeight() - height ;
        if (Math.abs (error) < 1 ) {
            allDriveMotorsStop () ;
            return true ;
        }
        else if (error > 0) {
            goDown();
            return false;
        }
        else if (error < 0) {
            goUp();
            return false ;
        }
        return false;


    }
    public void allDriveMotorsStop () {
        linearSlide.setPower(0);
    }
    
    public void autoGoToHeight (double height, Telemetry telemetry) {
         boolean isAtHeight = false;
         while(!isAtHeight) {
            isAtHeight = goToHeight( height);
            
            telemetry.addData("height", getHeight());
            telemetry.update();
         }
    }

}
