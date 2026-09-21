// TODO:
// This file is from the 25-26 FTC Season and needs to be updated at some point
// Also is set up for the limelight, which needs to be changed to use the FTC Vision library
// Current date at time of commenting 9/19/26

package org.firstinspires.ftc.teamcode.core;


import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.teamcode.core.MeteorLog;
import org.firstinspires.ftc.teamcode.core.Vector3;

import java.util.List;


public class AprilTag {
    Limelight3A limelight;
    Tag obelisk;
    Tag red;
    Tag blue;

    public AprilTag(Limelight3A limelight) { // Constructor that you call to initialize
        this.limelight = limelight;
        limelight.start();

    }
    public void update() { // Call every loop in your OpMode
        updateTagInformation();
        printDebug();

    }
    public void printDebug() {
        if (red != null) {
            MeteorLog.print("red", red.pos.toString());
        }
        if (blue != null) {
            MeteorLog.print("blue", blue.pos.toString());
            MeteorLog.print("field pos", blue.fieldPos.toString());
        }
        if (obelisk != null) {
            MeteorLog.print("obelisk", obelisk.pos.toString());
        }


    }

    private void updateTagInformation() {
        if (red != null) {
            red.age++;
        }
        if (blue != null) {
            blue.age++;
        }
        if (obelisk != null) {
            obelisk.age++;
        }

        LLResult llresult = limelight.getLatestResult();

        if (llresult != null && llresult.isValid()){
            Pose3D pose = llresult.getBotpose();

            List<LLResultTypes.FiducialResult> fiducials = llresult.getFiducialResults();
            for (LLResultTypes.FiducialResult fiducial : fiducials) {
                int id = fiducial.getFiducialId(); // The ID number of the fiducial
                MeteorLog.print("Fiducial is ", id);
                switch(id) {
                    case 20: {
                        Position pos = fiducial.getTargetPoseCameraSpace().getPosition();
                        Vector3 vPos = fromCameraSpace(pos);
                        Position fieldPos = fiducial.getCameraPoseTargetSpace().getPosition();
                        Vector3 vFieldPos = fromTargetSpace(fieldPos);
                        if (vPos.length() < 0.01) {
                            break;
                        }
                        if (blue == null) {
                            blue = new Tag();
                        }
                        blue.pos = vPos;
                        blue.fieldPos = vFieldPos;
                        blue.age = 0;
                        break;
                    }
                    case 21:
                    case 22:
                    case 23: {
                        Position pos = fiducial.getTargetPoseCameraSpace().getPosition();
                        Vector3 vPos = fromCameraSpace(pos);
                        Position fieldPos = fiducial.getCameraPoseTargetSpace().getPosition();
                        if (vPos.length() < 0.01) {
                            break;
                        }
                        if (obelisk == null) {
                            obelisk = new Tag();
                        }
                        obelisk.pos = vPos;
                        obelisk.age = 0;
                        break;
                    }
                    case 24: {
                        Position pos = fiducial.getTargetPoseCameraSpace().getPosition();
                        Vector3 vPos = fromCameraSpace(pos);
                        Position fieldPos = fiducial.getCameraPoseTargetSpace().getPosition();
                        if (vPos.length() < 0.01) {
                            break;
                        }
                        if (red == null) {
                            red = new Tag();
                        }
                        red.pos = vPos;
                        red.age = 0;
                        break;
                    }
                }
            }

        } else {
            MeteorLog.print("Limelight", "No Targets");
        }


    }
    /*
    Limelight3A coordinate systems
    x right y down z forward
    */

    private Vector3 fromCameraSpace(Position pos) {
        return new Vector3(pos.x, -pos.y, pos.z);
    }
    public double target(String tagName) {
        double rotationPower = 0;
        Tag targetTag = obelisk;
        switch (tagName) {
            case "blue":
                targetTag = blue;
                break;
            case "red":
                targetTag = red;
                break;
        }
        if (targetTag != null && targetTag.age < 2) {
            Vector3 dir2d = new Vector3(targetTag.pos.x, 0, targetTag.pos.y);
            if (dir2d.length() >= 0.01) {
                Vector3 vRight = new Vector3(1, 0, 0);
                MeteorLog.print("dir2d", dir2d.toString());
                double maxTurnSpeed = 1;
                rotationPower = Math.min(maxTurnSpeed, Math.max(-maxTurnSpeed, vRight.dot(dir2d)));
                return rotationPower;
            }

            MeteorLog.print("rotationPower", rotationPower);
            MeteorLog.flush();
        }

        return 0;
    }
    public Vector3 fromTargetSpace(Position pos) {
        double x = -pos.x;
        double z = -pos.z;
        double sin45 = 0.7071;
        double cos45 = 0.7071;
        return new Vector3(-(x * cos45 - z * sin45), 0, x * sin45 + z * cos45);
    }

//    public Vector3 fieldPos(String tagName) {
//
//    }
}
