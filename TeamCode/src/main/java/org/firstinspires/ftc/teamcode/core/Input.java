package org.firstinspires.ftc.teamcode.core;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Input {
    Gamepad gamepad1;
    Gamepad gamepad2;
    double powerMultiplier = 0.5;
    public Input(Gamepad gamepad1, Gamepad gamepad2) {
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
    }
    public double moveX() {
        return deadzone(gamepad1.left_stick_x);
    }
    public double moveY() {
        return deadzone(-gamepad1.left_stick_y);
    }
    public double rotate() {
        return deadzone(gamepad1.right_stick_x);
    }
    public boolean targeting() {
        return gamepad1.left_bumper;
    }
    public boolean slow() {
        return gamepad1.right_bumper;
    }
    private double deadzone(double value) {
        double deadzoneAmount = 0.01;
        return Math.abs(value) < deadzoneAmount ? 0 : value;
    }
}
