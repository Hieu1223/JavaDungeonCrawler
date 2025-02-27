package aris_engine.utils;

import org.joml.Quaternionf;

public class QuatUtils {
    public static Quaternionf EulerToQuat(float x,float y, float z){
        double cr = Math.cos(x * 0.5);
        double sr = Math.sin(x * 0.5);
        double cp = Math.cos(y * 0.5);
        double sp = Math.sin(y * 0.5);
        double cy = Math.cos(z * 0.5);
        double sy = Math.sin(z* 0.5);
        return new Quaternionf(
            sr * cp * cy - cr * sp * sy,
            cr * sp * cy + sr * cp * sy,
            cr * cp * sy - sr * sp * cy,
            cr * cp * cy + sr * sp * sy
        );
    }
}
