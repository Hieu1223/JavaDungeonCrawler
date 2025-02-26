package aris_engine.rendering;

import org.lwjgl.opengl.GL41;


public class Mesh {
    public double[] vertices;
    public int[] indices;
    public int vaoId;
    public Mesh(double[]vertices, int[] indices) {
        this.vertices = vertices;
        this.indices = indices;
        int vertId = GL41.glGenBuffers();
        int inId = GL41.glGenBuffers();
        vaoId = GL41.glGenVertexArrays();
        GL41.glBindVertexArray(vaoId);
        GL41.glBindBuffer(GL41.GL_ARRAY_BUFFER, vertId);
        GL41.glBindBuffer(GL41.GL_ELEMENT_ARRAY_BUFFER, inId);
        GL41.glBufferData(GL41.GL_ARRAY_BUFFER,vertices,GL41.GL_STATIC_DRAW);
        GL41.glBufferData(GL41.GL_ELEMENT_ARRAY_BUFFER, indices, GL41.GL_STATIC_DRAW);
        GL41.glEnableVertexAttribArray(0);//pos
        GL41.glEnableVertexAttribArray(1);//uv
        GL41.glVertexAttribLPointer(0,3,GL41.GL_DOUBLE, Double.BYTES* 5,0);
        GL41.glVertexAttribLPointer(1,2,GL41.GL_DOUBLE,Double.BYTES* 5,Double.BYTES*3);
        //System.out.println(GL41.glGetError());
        GL41.glBindVertexArray(0);
    }
    public void Bind(){
        GL41.glBindVertexArray(vaoId);
    }

    public void Unbind(){
        GL41.glBindVertexArray(0);
    }
}
