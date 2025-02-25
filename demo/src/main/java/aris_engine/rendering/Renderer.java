package aris_engine.rendering;

import org.lwjgl.opengl.GL41;

import aris_engine.core.Transform;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;


public class Renderer {
    static public class Params{
        public int scrWidth;
        public int scrHeight;
        public float nearPlane;
        public float farPlane;
        public float fov;
    }
    Params data ;
    public Renderer(Params params) {
        data = params;
    }
    public void Render(Mesh mesh,Transform transform,Material material) {
        mesh.Bind();
        material.Bind(transform);
        GL41.glDrawElements(GL_TRIANGLES,mesh.indices.length,GL_UNSIGNED_INT,0);
        //System.out.println(GL41.glGetError());
        mesh.Unbind();
        material.UnBind();
    }
}
