package game;

import static org.lwjgl.glfw.GLFW.*;

import org.joml.Vector3f;

import aris_engine.core.Transform;
import aris_engine.input.Input;
import aris_engine.utils.ArisTime;

public class Player {
    public Transform transform = new Transform();
    public float speed = 2f;
    public float rotSpeed = 50f;
    public void Start(){
        transform.localPos = new Vector3f(0,0,-10);
    }
    public void Update(){
        if(Input.GetKey(GLFW_KEY_W)){
            transform.localPos.add(0, speed * (float)ArisTime.deltaTime(),0);
        }
        if(Input.GetKey(GLFW_KEY_S)){
            transform.localPos.add(0, -speed * (float)ArisTime.deltaTime(),0);
        }
        if(Input.GetKey(GLFW_KEY_A)){
            transform.localPos.add( -speed * (float)ArisTime.deltaTime(),0,0);
        }
        if(Input.GetKey(GLFW_KEY_D)){
            transform.localPos.add( speed * (float)ArisTime.deltaTime(),0,0);
        }
        transform.localRot.rotateLocalY(-speed * (float)ArisTime.deltaTime());
        double currentTime = ArisTime.GetUptime();
        ///System.out.println(Math.sin(currentTime/20));
        transform.localScale.set(1+ Math.sin(currentTime)/4);
        transform.Update();
    }
}
