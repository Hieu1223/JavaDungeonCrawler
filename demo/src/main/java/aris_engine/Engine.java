package aris_engine;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import aris_engine.input.Input;
import aris_engine.rendering.Renderer;
import aris_engine.rendering.Window;
import aris_engine.utils.ArisTime;

public abstract class Engine {
    protected Window window;
    public static class Params {
        int width = 1280, height = 720;
    }

    public Engine(Params params){
        System.setProperty("dominion.show-banner", "false");
        GLFWErrorCallback.createPrint(System.err).set();
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");
        glfwDefaultWindowHints();// optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
        window = new Window(params.height, params.width);
        GL.createCapabilities();
        glEnable(GL_DEPTH_TEST);
        Renderer.Params rendererParams = new Renderer.Params();
        rendererParams.fov = 60;
        rendererParams.farPlane = 1000;
        rendererParams.nearPlane = 0.1f;
    }

    public void Run(){
        ArisTime.Init();
        Input.Init(window.window);
        Start();
        while (window.eventQueue.isEmpty() || window.eventQueue.peek()!=Window.Event.ShouldClose) {
            ArisTime.ProbeStart();
            Update();
            window.Update();
            ArisTime.ProbeEnd();
        }
        Shutdown();
    }

    public abstract void Start();
    public abstract void Update();
    public void Shutdown(){

    }
}
