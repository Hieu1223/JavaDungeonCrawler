package aris_engine.rendering;

import org.lwjgl.opengl.GL41;

import aris_engine.core.Component;
import aris_engine.utils.DefaultShaders;
import static org.lwjgl.opengl.GL41.*;


public class Renderer extends Component {
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
    @Override
    public void Start() {

    }
    @Override
    public void Update() {
        data.material.Bind(transform);
        gameObject.meshFilter.Bind();
        GL41.glDrawElements(GL_TRIANGLES,gameObject.meshFilter.indices.length,GL_UNSIGNED_INT,0);
        //System.out.println(GL41.glGetError());
        gameObject.meshFilter.Unbind();
        data.material.UnBind();
    }
}
