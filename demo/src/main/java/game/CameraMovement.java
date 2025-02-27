package game;
import static org.lwjgl.glfw.GLFW.*;
import org.joml.Vector3f;

import aris_engine.core.Component;
import aris_engine.input.Input;
import aris_engine.utils.ArisTime;
import aris_engine.utils.QuatUtils;
public class CameraMovement extends Component {
    public float speed = 1;
    public float moveSpeed = 5;
    @Override
    public void Start() {

    }

    Vector3f eulerRot = new Vector3f(-0.57f, 0.461f, 0f);
    @Override
    public void Update() {
        if(Input.GetKey(GLFW_KEY_UP)){
            eulerRot.x+= speed * (float)ArisTime.deltaTime();
        }
        if(Input.GetKey(GLFW_KEY_DOWN)){
            eulerRot.x-= speed * (float)ArisTime.deltaTime();
        }
        if(Input.GetKey(GLFW_KEY_LEFT)){
            eulerRot.y+= speed * (float)ArisTime.deltaTime();
        }
        if(Input.GetKey(GLFW_KEY_RIGHT)){
            eulerRot.y-= speed * (float)ArisTime.deltaTime();
        }
        transform.localRot = QuatUtils.EulerToQuat(eulerRot.x, eulerRot.y, eulerRot.z);


        if(Input.GetKey(GLFW_KEY_W)){
            transform.localPos.add(transform.forward().mul(moveSpeed * (float)ArisTime.deltaTime()));
        }
        if(Input.GetKey(GLFW_KEY_S)){
            transform.localPos.add(transform.forward().mul(-moveSpeed * (float)ArisTime.deltaTime()));
        }
        if(Input.GetKey(GLFW_KEY_A)){
            transform.localPos.add(transform.right().mul(-moveSpeed * (float)ArisTime.deltaTime()));
        }
        if(Input.GetKey(GLFW_KEY_D)){
            transform.localPos.add(transform.right().mul(moveSpeed * (float)ArisTime.deltaTime()));
        }
    }
    
}
