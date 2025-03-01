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
    }
    int CreateEmptyVBO(int floatCount,float[] data){
        int vbo = GL41.glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, data, GL_STREAM_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        return vbo;
    }

    void AddInstanceAttribute(int vao, int vbo, int attribute, int dataSize,int instanceDataLength, int floatOffset){
        GL41.glBindVertexArray(vao);
        glEnableVertexAttribArray(attribute);
        GL41.glBindBuffer(GL_ARRAY_BUFFER, vbo);
        GL41.glVertexAttribPointer(attribute, dataSize,GL_FLOAT,false, instanceDataLength*4, floatOffset*4);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        GL41.glVertexAttribDivisor(attribute, 1);
        glBindVertexArray(0);
    }


    public void SetUpParticleData(float[] transformMatrices){
        
        instanceVBO = CreateEmptyVBO(10, transformMatrices);
        AddInstanceAttribute(gameObject.meshFilter.vaoId, instanceVBO, 3, 4, 16, 0);
        AddInstanceAttribute(gameObject.meshFilter.vaoId, instanceVBO, 4, 4, 16, 4);
        AddInstanceAttribute(gameObject.meshFilter.vaoId, instanceVBO, 5, 4, 16, 8);
        AddInstanceAttribute(gameObject.meshFilter.vaoId, instanceVBO, 6, 4, 16, 12);
        particleCount = transformMatrices.length;
    }

    @Override
    public void Update() {
        material.Bind(transform);
        glBindVertexArray(gameObject.meshFilter.vaoId);
        glBindBuffer(GL_ARRAY_BUFFER, instanceVBO); 
        GL41.glDrawElementsInstanced(GL_TRIANGLES,gameObject.meshFilter.indices.length,GL_UNSIGNED_INT,0L,particleCount);
        glBindVertexArray(0);
        material.UnBind();  
        //throw new UnsupportedOperationException();
    }
    
}
