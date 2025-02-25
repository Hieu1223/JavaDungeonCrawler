package aris_engine.utils;

public class Projection {
    public static float[] OrthoGraphic(double aspect, double near, double far, double fov){
        double temp = Math.tan(fov/2);
        float[] res = new float[16];
        res[0] = (float)(1/aspect/temp);
        res[5] = (float)(1/temp);
        res[10] = (float)((-near - far)/(far-near));
        res[11] = (float)((2* near * far)/(far-near));
        res[14] = -1;
        return res;
    }
    public static float[] Perspective(double aspect, double near, double far, double fov){
        double temp = Math.tan(fov/2);
        float[] res = new float[16];
        res[0] = (float)(1/aspect/temp);
        res[5] = (float)(1/temp);
        res[10] = (float)((-near - far)/(far-near));
        res[11] = (float)((2* near * far)/(far-near));
        res[14] = -1;
        return res;
    }
}
