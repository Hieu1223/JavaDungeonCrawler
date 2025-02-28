package aris_engine.modules.particle_system;

import static org.lwjgl.opengl.GL41.*;

import org.lwjgl.opengl.GL41;

import aris_engine.rendering.Material;
import aris_engine.rendering.Renderer;

public class ParticleRenderer extends Renderer {
    
    public int particleCount = 0;
    public int instanceVBO = -1;
    public Material material;
    public ParticleRenderer(Material material) {
        super(new Renderer.Params());
        this.material = material;
        //TODO : implement particle renderer
        throw new UnsupportedOperationException();
    }
    public void SetUpParticleData(float[] transformMatrices){
        instanceVBO = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, instanceVBO);   
        glBufferData(instanceVBO,transformMatrices,GL41.GL_STATIC_DRAW);

        //GL41.glBindVertexArray(transform.gameObject.meshFilter.vaoId);
        GL41.glEnableVertexAttribArray(3);
        GL41.glVertexAttribPointer(3, 1, GL_FLOAT, false, Float.BYTES, 0);
        GL41.glVertexAttribDivisor(3, 10);
        //gameObject.meshFilter.Unbind();
        particleCount = transformMatrices.length;throw new UnsupportedOperationException();
    }

    @Override
    public void Update() {
        material.Bind(transform);
        glBindVertexArray(gameObject.meshFilter.vaoId);
        GL41.glDrawElementsInstanced(GL_TRIANGLES, gameObject.meshFilter.indices.length, GL_UNSIGNED_INT,0L,particleCount*1000);
        //System.out.println(GL41.glGetError());
        glBindVertexArray(0);
        material.UnBind();   throw new UnsupportedOperationException();
    }
    
}
