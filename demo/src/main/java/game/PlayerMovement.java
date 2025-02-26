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
    }

    @Override
    public void Update() {
        transform.localRot.rotateLocalY(speed * (float)ArisTime.deltaTime());
    }

}
