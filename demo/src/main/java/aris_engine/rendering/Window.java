package aris_engine.rendering;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.stackPush;

import java.nio.IntBuffer;
import java.util.LinkedList;
import java.util.Queue;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.system.MemoryStack;


public class Window {
    public static enum Event {
        ShouldClose
    }

    public Queue<Event> eventQueue = new LinkedList<Event>();

    public long window;
    int height = 0, width = 0;
    public Window(int height, int width){
        this.height = height;
        this.width = width;
        window = glfwCreateWindow(width, height, "Game window",0,0);

        try ( MemoryStack stack = stackPush() ) {
			IntBuffer pWidth = stack.mallocInt(1); // int*
			IntBuffer pHeight = stack.mallocInt(1); // int*

			// Get the window size passed to glfwCreateWindow
			glfwGetWindowSize(window, pWidth, pHeight);

			// Get the resolution of the primary monitor
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			// Center the window
			glfwSetWindowPos(
				window,
				(vidmode.width() - pWidth.get(0)) / 2,
				(vidmode.height() - pHeight.get(0)) / 2
			);
		} // the stack frame is popped automatically

        glfwMakeContextCurrent(window);
		// Enable v-sync
		glfwSwapInterval(1);

		// Make the window visible
		glfwShowWindow(window);
    }
    public void Update(){
        if(glfwWindowShouldClose(window))
            eventQueue.add(Event.ShouldClose);
        glfwSwapBuffers(window);
        glfwPollEvents();
    }
}
