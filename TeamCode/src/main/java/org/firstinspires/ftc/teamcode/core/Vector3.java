package org.firstinspires.ftc.teamcode.core;

public class Vector3 {
    double x, y, z;
    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;

    }
    public double length() {

        return Math.sqrt(x * x + y * y + z * z);
    }
    public void normalize() {
        double length = this.length();
        if (length > 1e-6) {
            this.x = x / length;
            this.y = y / length;
            this.z = z / length;
        }

    }
    public Vector3 normalized() {
        Vector3 vector3 = new Vector3(x, y, z);
        vector3.normalize();
        return vector3;
    }

    public String toString() {
        return String.format("(%.2f, %.2f, %.2f)", x, y, z);
    }
    public double dot(Vector3 other) {
        return (x * other.x) + (y * other.y) + (z * other.z);
    }
}
