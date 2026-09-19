package org.firstinspires.ftc.teamcode.core;

import org.firstinspires.ftc.robotcore.external.Telemetry;




public class MeteorLog {

    private static Telemetry telemetry;
    public static void init(Telemetry telemetry) {
        MeteorLog.telemetry = telemetry;
    }
    public static void print(String tag, String content) {
        telemetry.addData(tag, content);
        // telemetry.update();
    }
    public static void print(String tag, double content) {
        telemetry.addData(tag, String.format("%.2f", content));
        // telemetry.update();
    }
    public static void flush() {
        telemetry.update();
    }

}
