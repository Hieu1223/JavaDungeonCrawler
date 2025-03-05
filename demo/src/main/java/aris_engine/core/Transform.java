package aris_engine.core;

import java.util.LinkedList;
import java.util.List;

import org.joml.*;

public class Transform {
    public Quaternionf localRot = new Quaternionf().identity();
    public Vector3f localPos = new Vector3f().zero();
    public Vector3f localScale= new Vector3f(1,1,1);
    public Matrix4f transformMat = new Matrix4f().identity();
    public float[] transforMatArr = new float[16];
    public float[] localPosArr = new float[3];
    public Transform parent;
    public GameObject gameObject;
    public List<Transform> children = new LinkedList<Transform>();
    public Transform(){

    }
    public Transform(Vector3f pos,Quaternionf rot, Vector3f scale){
        localPos = pos;
        localRot = rot;
        localScale = scale;
    }
    public void Update(){
        transformMat = new Matrix4f().identity();
        transformMat = transformMat.translate(localPos);
        transformMat =  transformMat.rotate(localRot);
        transformMat = transformMat.scale(localScale);
        if(parent!= null){
            Matrix4f newMat = new Matrix4f();
            parent.transformMat.mul(transformMat, newMat);
            transformMat = newMat;
        }
        if(gameObject.renderer != null)
            transformMat.get(transforMatArr);
        localPosArr[0] = localPos.x;
        localPosArr[1] = localPos.y;
        localPosArr[2] = localPos.z;
    }
    public Vector3f forward(){
        return transformMat.transformDirection(new Vector3f(0,0,-1));
    }
    public Vector3f right(){
        return transformMat.transformDirection(new Vector3f(1,0,0));
    }
    public Vector3f up(){
        return transformMat.transformDirection(new Vector3f(0,1,0));
    }

}
