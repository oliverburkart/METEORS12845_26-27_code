package org.firstinspires.ftc.teamcode.core;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.core.AprilTag;
import org.firstinspires.ftc.teamcode.core.MeteorLog;
import org.firstinspires.ftc.teamcode.core.Movement;

public class MeteorOpMode extends LinearOpMode {

    public Movement movement;
    public AprilTag aprilTag;
    public Input input;
    // public Control control;

    public Limelight3A limelight;
    public void setup() {
        MeteorLog.init(telemetry);
        movement = new Movement(
                hardwareMap.get(DcMotor.class, "frontleft"),
                hardwareMap.get(DcMotor.class, "frontright"),
                hardwareMap.get(DcMotor.class, "backleft"),
                hardwareMap.get(DcMotor.class, "backright")
        );
        aprilTag = new AprilTag(hardwareMap.get(Limelight3A.class, "lemonlight"));
        input = new Input(gamepad1, gamepad2);
//       TODO: here would be the spot to put the control class declaration
        limelight = hardwareMap.get(Limelight3A.class, "lemonlight");
        limelight.start();
//        TODO: set this to the proper pipeline otherwise it won't work
        limelight.pipelineSwitch(1);
    }
    @Override
    public void runOpMode() {
        setup();
        waitForStart();
        while (opModeIsActive()) {
            aprilTag.update();
            run();
            // control.printDebug();
            MeteorLog.flush();
        }
    }
    public void run() {

    }
}
