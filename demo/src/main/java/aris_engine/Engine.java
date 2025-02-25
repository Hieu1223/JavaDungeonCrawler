package aris_engine;

import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import aris_engine.input.Input;
import aris_engine.rendering.Renderer;
import aris_engine.rendering.Window;
import aris_engine.utils.ArisTime;

public abstract class Engine {
    protected Window window;
    protected Renderer renderer;
    public static class Params {
        int width = 1280, height = 720;
    }

    public Engine(Params params){
        GLFWErrorCallback.createPrint(System.err).set();
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");
        glfwDefaultWindowHints();// optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
        window = new Window(params.height, params.width);
        GL.createCapabilities();
        Renderer.Params rendererParams = new Renderer.Params();
        rendererParams.scrWidth = params.width;
        rendererParams.scrHeight = params.height;
        rendererParams.fov = 60;
        rendererParams.farPlane = 1000;
        rendererParams.nearPlane = 0.1f;
        renderer = new Renderer(rendererParams);
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
