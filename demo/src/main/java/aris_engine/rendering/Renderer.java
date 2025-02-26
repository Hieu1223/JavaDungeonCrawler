package aris_engine.rendering;

import org.lwjgl.opengl.GL41;

import aris_engine.core.Transform;
import aris_engine.utils.DefaultShaders;

import static org.lwjgl.opengl.GL41.GL_TRIANGLES;
import static org.lwjgl.opengl.GL41.GL_UNSIGNED_INT;


public class Renderer {
    static public class Params{
        public float nearPlane = 0.1f;
        public float farPlane = 1000;
        public float fov = 60;
        public Material material = DefaultShaders.defaultMaterial;
    }
    Params data ;
    public Renderer(Params params) {
        data = params;
    }
    public void Render(Mesh mesh,Transform transform) {
        data.material.Bind(transform);
        mesh.Bind();
        GL41.glDrawElements(GL_TRIANGLES,mesh.indices.length,GL_UNSIGNED_INT,0);
        //System.out.println(GL41.glGetError());
        mesh.Unbind();
        data.material.UnBind();
    }
}
