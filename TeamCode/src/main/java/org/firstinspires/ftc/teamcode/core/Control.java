// This class is for the motors and servos not directly related to driving
package org.firstinspires.ftc.teamcode.core;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Control {

    private CRServo leftintake;
    private CRServo rightintake;
    private DcMotor topintake;

    public Control(CRServo leftintake, CRServo rightintake, DcMotor topintake) {
        this.leftintake = leftintake;
        this.rightintake = rightintake;
        this.topintake = topintake;
    }

    public void intakeIn(double power) {
        leftintake.setPower(-power);
        rightintake.setPower(power);
        topintake.setPower(power);
    }

    public void intakeOut(double power) {
        leftintake.setPower(power);
        rightintake.setPower(-power);
        topintake.setPower(-power);
    }

    public void stop() {
        leftintake.setPower(0);
        rightintake.setPower(0);
        topintake.setPower(0);
    }
}
