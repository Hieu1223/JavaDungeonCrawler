package game;

import static org.lwjgl.glfw.GLFW.*;

import aris_engine.core.Component;
import aris_engine.input.Input;
import aris_engine.utils.ArisTime;

public class PlayerMovement extends Component {
    public float speed = 5f;
    public float rotSpeed = 10f;
    @Override
    public void Start() {
    }

    @Override
    public void Update() {
        transform.localRot.rotateLocalY(speed * (float)ArisTime.deltaTime());
    }

}
