package aris_engine;

import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import aris_engine.core.Transform;
import aris_engine.rendering.Material;
import aris_engine.rendering.Renderer;
import aris_engine.rendering.Shader;
import aris_engine.rendering.Texture;
import aris_engine.rendering.Window;
import aris_engine.utils.DefaultShaders;
import aris_engine.utils.Primitives;

public class Engine {
    Window window;
    Renderer renderer;
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
        Start();
        while (window.eventQueue.isEmpty() || window.eventQueue.peek()!=Window.Event.ShouldClose) {
            Update();
        }
        Shutdown();
    }

    public void Start(){
        glClearColor(0.7f,0.3f, 0, 1);
        testTransform = new Transform();
        temp = new Shader(DefaultShaders.defaultFrag, DefaultShaders.defaultVert);
        Texture[] texs = {};
        testMaterial = new Material(temp,texs);
    }
    Shader temp ;
    Transform testTransform;
    Material testMaterial;
    public void Update(){
        glClear(GL_COLOR_BUFFER_BIT);
        renderer.Render(Primitives.square,testTransform ,testMaterial);
        //System.out.println("after" + GL41.glGetError());
        window.Update();
    }
    
    public void Shutdown(){

    }

    public static Engine defaultInit(){
        Params params = new Params();
        return new Engine(params);
    }
}
