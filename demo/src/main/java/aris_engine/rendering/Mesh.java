package aris_engine.rendering;


import java.util.ArrayList;

import org.lwjgl.assimp.*;
import org.lwjgl.opengl.GL41;


public class Mesh {
    public double[] vertices;
    public int[] indices;
    public int vaoId;
    public Mesh(double[]vertices, int[] indices) {
        //System.out.println("Mesh begin" + GL41.glGetError());
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
        GL41.glEnableVertexAttribArray(2);//normal
        
        int stride = Double.BYTES * 8;
        GL41.glVertexAttribLPointer(0,3,GL41.GL_DOUBLE, stride,0);
        GL41.glVertexAttribLPointer(1,2,GL41.GL_DOUBLE,stride,Double.BYTES*3);
        GL41.glVertexAttribLPointer(2,3,GL41.GL_DOUBLE,stride,Double.BYTES*5);
        GL41.glBindVertexArray(0);
        
        //System.out.println("Mesh end" + GL41.glGetError());
        
    }
    public void Bind(){
        GL41.glBindVertexArray(vaoId);
    }

    public void Unbind(){
        GL41.glBindVertexArray(0);
    }
    public static Mesh fromFile(String path){
        ArrayList<Double> verts = new ArrayList<>();
        ArrayList<Integer> indices = new ArrayList<>();
        AIScene scene = Assimp.aiImportFile(path,
            Assimp.aiProcess_Triangulate|
            Assimp.aiProcess_GenSmoothNormals|
            Assimp.aiProcess_FlipUVs|
            Assimp.aiProcess_CalcTangentSpace|
            Assimp.aiProcess_LimitBoneWeights
        );

        


        double[] vertsArr = new double[verts.size()];
        for(int i = 0 ; i< verts.size(); i++){
            vertsArr[i] = verts.get(i);
        }
        int[] indicesArr = new int[indices.size()];
        for(int i = 0 ; i< indices.size(); i++){
            indicesArr[i] = indices.get(i);
        }
        return new Mesh(vertsArr,indicesArr);
    }
}
