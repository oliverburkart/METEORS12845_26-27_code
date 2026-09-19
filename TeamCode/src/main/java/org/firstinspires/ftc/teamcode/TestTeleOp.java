package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.core.Input;
import org.firstinspires.ftc.teamcode.core.Movement;

@TeleOp(name = "Testing TeleOp", group = "Linear OpMode")
public class TestTeleOp extends LinearOpMode {

    @Override
    public void runOpMode() {
        DcMotor frontleft = hardwareMap.get(DcMotor.class, "frontleft");
        DcMotor frontright = hardwareMap.get(DcMotor.class, "frontright");
        DcMotor backleft = hardwareMap.get(DcMotor.class, "backleft");
        DcMotor backright = hardwareMap.get(DcMotor.class, "backright");

        Movement movement = new Movement(frontleft, frontright, backleft, backright);
        Input input = new Input(gamepad1, gamepad2);

        waitForStart();

        while (opModeIsActive()) {
            movement.setMove(input.moveX(), input.moveY(), input.rotate());
            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}

