package aris_engine.rendering;

import org.joml.Matrix4f;

import aris_engine.core.Component;

public class Camera extends Component{
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
    public Matrix4f projectionMat;
    public Matrix4f viewMat;
    public float[] projectionMatArr = new float[16];
    public float[] viewMatArr = new float[16];
    Params params;
    public Camera(Params params){
        projectionMat = new Matrix4f().perspective((float)Math.toRadians(params.fov), params.aspect, params.zNear, params.zFar);      
        if(main == null)
            main = this; 
        else
            throw new Error("More than one Camera instance");
        this.params = params;
    }
    public void SetMain(){
        main = this;
    }
    @Override
    public void Start() {
        viewMat = new Matrix4f().invert(transform.transformMat);
        viewMat.get(viewMatArr);
        projectionMat.get(projectionMatArr);
    }
    @Override
    public void Update() {
        viewMat = new Matrix4f(transform.transformMat).invert();
        viewMat.get(viewMatArr);
    }
}
