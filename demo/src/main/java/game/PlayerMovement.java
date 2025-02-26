package game;

import static org.lwjgl.glfw.GLFW.*;


import aris_engine.input.Input;
import aris_engine.scene.Component;
import aris_engine.utils.ArisTime;

public class PlayerMovement extends Component {
    public float speed = 10f;
    public float rotSpeed = 50f;
    @Override
    public void Start() {
        
        this.active = false;
    }

    @Override
    public void Update() {
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
    }

}
