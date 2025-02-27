package aris_engine.renderers.renderer0;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL41;

import aris_engine.core.Transform;
import aris_engine.rendering.Material;
import aris_engine.rendering.Mesh;
import aris_engine.rendering.Renderer;
import aris_engine.rendering.Shader;
import aris_engine.rendering.Texture;
import aris_engine.utils.MyPath;

public class Renderer0 extends Renderer {
    static Material material;
    public Renderer0(){
        super(new Renderer.Params());
        if(material == null){
            Texture[] texs = {};
            Shader shader = new Shader(MyPath.folderPath + "\\assets\\shader\\renderer0.frag",MyPath.folderPath + "\\assets\\shader\\renderer0.vert");
            material = new Material(shader, texs);
        }
    } 
    @Override
    public void Render(Mesh mesh, Transform transform) {
        mesh.Bind();
        material.Bind(transform);
        GL41.glDrawElements(GL_TRIANGLES,mesh.indices.length,GL_UNSIGNED_INT,0);
        mesh.Unbind();
        material.UnBind();
    }
}
