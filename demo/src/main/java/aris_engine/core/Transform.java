package aris_engine.core;

import java.util.LinkedList;
import java.util.List;

import org.joml.*;

import aris_engine.scene.GameObject;

public class Transform {
    public Quaternionf localRot = new Quaternionf().identity();
    public Vector3f localPos = new Vector3f().zero();
    public Vector3f localScale= new Vector3f(1,1,1);
    public Matrix4f transformMat = new Matrix4f().identity();
    public Transform parent;
    public GameObject gameObject;
    public List<Transform> children = new LinkedList<Transform>();
    public void Update(){
        transformMat = parent == null? new Matrix4f().identity() : new Matrix4f(parent.transformMat);
        transformMat = transformMat.translate(localPos);
        transformMat =  transformMat.rotate(localRot);
        transformMat = transformMat.scale(localScale);
    }
}
