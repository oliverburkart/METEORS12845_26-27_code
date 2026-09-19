package org.firstinspires.ftc.teamcode.core;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Movement {
    DcMotor frontleft;
    DcMotor frontright;
    DcMotor backleft;
    DcMotor backright;

    public Movement(DcMotor frontleft, DcMotor frontright, DcMotor backleft, DcMotor backright) {
        this.frontleft = frontleft;
        this.frontright = frontright;
        this.backleft = backleft;
        this.backright = backright;


        frontleft.setDirection(DcMotorSimple.Direction.REVERSE);
        backleft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontright.setDirection(DcMotorSimple.Direction.FORWARD);
        backright.setDirection(DcMotorSimple.Direction.FORWARD);

        backleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void setMove(double x, double y, double r) {
        double sf = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(r), 1); // sf is scale factor to keep power values between -1 and 1

        double flPower = (y + x + r) / sf;
        double frPower = (y - x - r) / sf;
        double blPower = (y - x + r) / sf;
        double brPower = (y + x - r) / sf;
        frontleft.setPower(flPower);
        frontright.setPower(frPower);
        backleft.setPower(blPower);
        backright.setPower(brPower);
        // MeteorLog.print("motor powers", String.format("%.2f, %.2f, %.2f, %.2f", flPower, frPower, blPower, brPower));
    }

    public void stop() {
        frontleft.setPower(0);
        frontright.setPower(0);
        backleft.setPower(0);
        backright.setPower(0);
    }
}
