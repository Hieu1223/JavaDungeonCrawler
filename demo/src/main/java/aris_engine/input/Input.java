package aris_engine.input;
import static org.lwjgl.glfw.GLFW.*;


public class Input {
    static Input instance;
    boolean[] keyMap = new boolean[256];
    public static void Init(long winHandle){
        instance = new Input();
        glfwSetKeyCallback(winHandle, (window, key, scancode, action, mods) -> {
            if(action == GLFW_PRESS){
                instance.keyMap[key] = true;
            }
            if(action == GLFW_RELEASE){
                instance.keyMap[key] = false;
            }
        });
    }
    public static boolean GetKey(int key){
        return instance.keyMap[key];
    }
    public static boolean GetKeyDown(int key){
        return true;
    }
}
