package aris_engine.modules.particle_system;

import static org.lwjgl.opengl.GL41.*;

import java.nio.FloatBuffer;
import java.util.ArrayList;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL41;

import aris_engine.core.Transform;
import aris_engine.rendering.Material;
import aris_engine.rendering.Mesh;
import aris_engine.rendering.Renderer;

public class ParticleRenderer extends Renderer {
    public ArrayList<float[]> particleTransformMat;
    public int particleCount = 0;
    public Material material;
    public ParticleRenderer(Material material) {
        super(new Renderer.Params());
        this.material = material;
    }

    @Override
    public void Render(Mesh mesh, Transform transform) {
        mesh.Bind();
        material.Bind(transform);
        for(int i = 0 ; i < particleCount;i++){
            material.shader.SetMat4("particleModel[" + i+ "]",particleTransformMat.get(i));
        }
        GL41.glDrawElementsInstanced(GL_TRIANGLES,mesh.indices.length,GL_UNSIGNED_INT,0,particleCount);
    }
    
}
