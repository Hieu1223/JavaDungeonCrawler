package aris_engine.modules.particle_system;

import static org.lwjgl.opengl.GL41.*;
import java.util.ArrayList;
import org.lwjgl.opengl.GL41;

import aris_engine.core.Transform;
import aris_engine.rendering.Material;
import aris_engine.rendering.Mesh;
import aris_engine.rendering.Renderer;

public class ParticleRenderer extends Renderer {
    
    public int particleCount = 0;
    public int instanceVBO = -1;
    public int instanceVAO;

    public Material material;
    public ParticleRenderer(Material material) {
        super(new Renderer.Params());
        this.material = material;
    }
    public void SetParticleMatrices(float[][] data){
        instanceVBO = glGenBuffers();
        instanceVAO = glGenVertexArrays();
        glBindVertexArray(instanceVAO);
        glBindBuffer(GL_ARRAY_BUFFER, instanceVBO);   
        glBufferData(instanceVBO, , GL_BUFFER_USAGE);
    }
    @Override
    public void Render(Mesh mesh, Transform transform) {
        if(particleCount == 0)
            return;
        mesh.Bind();
        material.Bind(transform);
        for(int i = 0 ; i < particleCount;i++){
            material.shader.SetMat4("particleModel[" + i+ "]",particleTransformMat.get(i));
        }
        GL41.glDrawElementsInstanced(GL_TRIANGLES,mesh.indices.length,GL_UNSIGNED_INT,0,particleCount);
        //System.out.println(GL41.glGetError());
    }
    
}
