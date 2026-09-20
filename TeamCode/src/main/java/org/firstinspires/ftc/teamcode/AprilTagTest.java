package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagSingleDetection;

import java.util.List;

@Autonomous(name = "AprilTagTest")
public class AprilTagTest extends LinearOpMode {

    @Override
    public void runOpMode() {
//        this is the init part of it
        AprilTagProcessor aprilTag = new AprilTagProcessor.Builder().build();
        VisionPortal visionPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "logitech"))
                .addProcessor(aprilTag)
                .build();

        waitForStart();

        if (opModeIsActive()) {
            // Pre-run
            while (opModeIsActive()) {
                // OpMode loop
//                checks where the tag is and prints it
                // aprilTag.getDetections() returns generic AprilTagDetection objects
                List<AprilTagDetection> currentDetections = aprilTag.getDetections();

                for (AprilTagDetection genericDetection : currentDetections) {

                    // Check if the detected object is a single standalone tag
                    if (genericDetection instanceof AprilTagSingleDetection) {

                        // Cast it to an AprilTagSingleDetection so Java can see the id and metadata
                        AprilTagSingleDetection detection = (AprilTagSingleDetection) genericDetection;

                        // Now these variables will resolve perfectly!
                        int tagId = detection.id;

                        if (detection.metadata != null) {
                            String tagName = detection.metadata.name;
                            double bearing = detection.ftcPose.bearing;

                            telemetry.addData("Found Tag", "ID %d (%s)", tagId, tagName);
                        }
                    }
                }
                telemetry.update();



            }
        }
    }
}