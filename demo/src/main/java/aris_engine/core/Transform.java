package aris_engine.core;

import java.lang.Math;

public class Transform {
    public float ZRotation;
    public float PosX, PosY;
    public float ScaleX = 1, ScaleY =1;
    public float[] GetRotationMatrix(){
        float cosa = (float)Math.cos(Math.toRadians((float)ZRotation));
        float sina = (float)Math.sin(Math.toRadians((float)ZRotation));
        float[] matrix = {
                cosa, -sina,
                sina, cosa,
        };
        return matrix;
    }
    public float[] GetScaleMatrix(){
        float[] mat = {
                ScaleX,0,
                0,ScaleY
        };
        return mat;
    }
    public float[] GetPositionVector(){
        float[] vec = {PosX, PosY};
        return vec;
    }
}
