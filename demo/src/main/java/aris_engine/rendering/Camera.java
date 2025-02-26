package aris_engine.rendering;

import org.joml.Matrix4f;

import aris_engine.core.Transform;

public class Camera {
    public static class Params{
        public float fov = 60;
        public float aspect;
        public float zNear = 0.1f;
        public float zFar = 1000;
        public Params(){
            aspect =  (float)Window.main.width/Window.main.height;
        }
    }
    public static Camera main;
    public Transform transform;
    public Matrix4f projectionMat;
    public Camera(Params params){
        transform = new Transform();
        projectionMat = new Matrix4f().perspective((float)Math.toRadians(params.fov), params.aspect, params.zNear, params.zFar);      
        if(main == null)
            main = this; 
    }
    public void SetMain(){
        main = this;
    }
}
