package game;
import static org.lwjgl.glfw.GLFW.*;

import aris_engine.input.Input;
import aris_engine.scene.Component;
import aris_engine.utils.ArisTime;
public class CameraMovement extends Component {
    public float speed = 1;
    @Override
    public void Start() {

    }

    @Override
    public void Update() {
        if(Input.GetKey(GLFW_KEY_W)){
            transform.localRot.rotateLocalX(-speed * (float)ArisTime.deltaTime());
        }
        if(Input.GetKey(GLFW_KEY_S)){
            transform.localRot.rotateLocalX(speed * (float)ArisTime.deltaTime());
        }
        if(Input.GetKey(GLFW_KEY_A)){
            transform.localRot.rotateLocalY(-speed * (float)ArisTime.deltaTime());
        }
        if(Input.GetKey(GLFW_KEY_D)){
            transform.localRot.rotateLocalY(speed * (float)ArisTime.deltaTime());
        }
    }
    
}
