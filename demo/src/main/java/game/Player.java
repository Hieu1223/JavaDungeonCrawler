package game;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;

import aris_engine.core.Transform;
import aris_engine.input.Input;
import aris_engine.utils.ArisTime;

public class Player {
    public Transform transform = new Transform();
    public double speed = 2f;
    public double rotSpeed = 10f;
    public void Start(){
        transform.PosX = 0.5f;
        transform.ScaleX = 0.1f;
        transform.ScaleY = 0.1f;
    }
    public void Update(){
        if(Input.GetKey(GLFW_KEY_W)){
            transform.PosY+= speed * ArisTime.deltaTime();
        }
        if(Input.GetKey(GLFW_KEY_S)){
            //transform.PosY-= speed * ArisTime.deltaTime();
        }
        if(Input.GetKey(GLFW_KEY_A)){
            transform.ZRotation-= rotSpeed * ArisTime.deltaTime();
        }
        if(Input.GetKey(GLFW_KEY_D)){
            transform.ZRotation+= rotSpeed * ArisTime.deltaTime();
        }
    }
}
